package com.nationsky.seccom.uc.service.implement;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.google.gson.Gson;
import com.nationsky.seccom.uc.dao.*;
import com.nationsky.seccom.uc.domain.DeptResponseData;
import com.nationsky.seccom.uc.model.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.service.IDeptService;
import com.nationsky.seccom.uc.util.ServiceUtil;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptBasicInfoMapper deptBasicInfoMapper; // 自动注入部门基本信息相关mapper。
	
	@Autowired
	private UserDeptRelationMapper userDeptRelationshipMapper; // 自动注入用户部门关系mapper。
	
	@Autowired
	private DeptRelationMapper deptRelationMapper; // 自动注入部门关系mapper。

	@Autowired
	private UserBasicInfoMapper userBasicInfoMapper; // 自动注入员工信息mapper.

	@Autowired
	private DeptExtensionMapper deptExtensionMapper; // 自动注入部门扩展信息mapper.

	private String addDeptBasicInfo(DeptBasicInfo departmentBasicInfo) {

		if (departmentBasicInfo == null)
		{
			return null;
		}
		else
		{
			
			//生成部门id
			String deptId = ServiceUtil.getUUID();
			departmentBasicInfo.setDeptId(deptId);
			
			// 设置部门状态为默认值
			departmentBasicInfo.setStatus("1");
			
			//插入部门基本信息
			int updateCount = deptBasicInfoMapper.insertSelective(departmentBasicInfo);
			
			//判断插入结果
			if (updateCount == 0)
			{
				return null;
			}

			return deptId;
		}
	}

	private boolean deleteDeptBasicInfoByDeptId(String deptId) {
		if (deptId == null)
		{
			return false;
		}
		else 
		{
			int updateCount = deptBasicInfoMapper.deleteByPrimaryKey(deptId);

			return updateCount != 0;
		}
	}

	public Date updateDeptBasicInfo(DeptBasicInfo departmentBasicInfo) {

		if (departmentBasicInfo == null)
		{
			return null;
		}
		else 
		{
			Date currentDate = ServiceUtil.getCurrentTime();
			departmentBasicInfo.setUpdateTime(currentDate);
			int updateCount = deptBasicInfoMapper.updateByPrimaryKeySelective(departmentBasicInfo);
			
			if (updateCount == 1)
			{
				return currentDate;
				
			}
			else
			{
				return null;
			}
		}
	}

	public DeptResponseData getDepartmentBasicInfo(String deptId)
    {
		if (deptId == null)
		{
			return null;
		}
		else
		{
			DeptBasicInfo deptBasicInfo = deptBasicInfoMapper.selectByPrimaryKey(deptId);
			if (deptBasicInfo == null)
			{
				throw new RuntimeException("无法获取部门基本信息！");
			}
			String deptLeaderId = deptBasicInfo.getDeptLeaderId();
			UserBasicInfo deptLeaderInfo = userBasicInfoMapper.selectByPrimaryKey(deptLeaderId);

            DeptResponseData deptResponseData;
			if (deptBasicInfo != null)
			{
                String deptLeaderName;
				deptLeaderName = deptLeaderInfo.getRealName();
                deptResponseData = new DeptResponseData();
                try {
                    PropertyUtils.copyProperties(deptResponseData, deptBasicInfo);
                    deptResponseData.setDeptLeaderName(deptLeaderName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
			}
			else {
				throw new RuntimeException("无法获取部门领导信息！");
			}
			return deptResponseData;
		}
	}
	
	public List<UserBasicInfo> getAllUsersOfDeptExcludingSubDeptByDeptId(String deptId)
	{
		/*检查参数*/
		if (deptId == null)
		{
			return null;
		}

		/*创建查询条件*/
		UserDeptRelationExample userDeptRelationshipExample =
					new UserDeptRelationExample();
		userDeptRelationshipExample.createCriteria().andDeptIdEqualTo(deptId);
		
		/*开始查询*/
		List<UserDeptRelation> userRelationships =
					userDeptRelationshipMapper.selectByExample(userDeptRelationshipExample);
		if (userRelationships == null)
		{
			// 该部门下没有员工
			return null;
		}
		else
		{
			/*从查询得到的员工与部门的关系中提取出员工id*/
			List<UserBasicInfo> users = new ArrayList<UserBasicInfo>();
			for(UserDeptRelation userDeptRelationship : userRelationships)
			{
                String userId = userDeptRelationship.getUserId();
                UserBasicInfo userBasicInfo =
                        userBasicInfoMapper.selectByPrimaryKey(userId);

                users.add(userBasicInfo);
			}
			return users;
		}
	}
	
	
	public List<DeptResponseData> getDesendentDepts(String deptId, int length)
	{
		if (length < 0)
		{
			return null;
		}
		else 
		{
			DeptRelationExample deptRelationExample = new DeptRelationExample(); // 创建查询条件
			/* 如果路径长度为零则表示获得全部的子部门。*/
			if (length == 0)
			{
				// 只添加一个条件
				deptRelationExample.createCriteria().andAncestorDeptIdEqualTo(deptId);

				/*得到符合条件的全部关系*/
				List<DeptRelation> deptRelations = deptRelationMapper.selectByExample(deptRelationExample);
				if (deptRelations == null || deptRelations.size() == 0)
				{
					return null;
				}
				else
				{
					List<DeptResponseData> depts = new ArrayList<DeptResponseData>();

					for (DeptRelation deptRelation : deptRelations)
					{
                        String descendantDeptId = deptRelation.getDescendantDeptId();
                        DeptResponseData deptResponseData = getDepartmentBasicInfo(descendantDeptId);
                        depts.add(deptResponseData);
					}
					return depts;
				}
			}
			/*路径长度大于零*/
			else
			{
				// 返回路径深度小于等于给定数值的部门，条件参数中输入1表示返回的部门中不包括指定的部门本身。
				deptRelationExample.createCriteria().andAncestorDeptIdEqualTo(deptId).andPathLengthBetween(1, length);

				/*得到符合条件的全部关系*/
				List<DeptRelation> deptRelations = deptRelationMapper.selectByExample(deptRelationExample);
				if (deptRelations == null || deptRelations.size() == 0)
				{
					return null;
				}
				else 
				{
                    List<DeptResponseData> depts = new ArrayList<DeptResponseData>();

					/*提取关系中的deptId*/
					for (DeptRelation deptRelation : deptRelations)
					{
                        String descendantDeptId = deptRelation.getDescendantDeptId();
                        DeptResponseData deptResponseData = getDepartmentBasicInfo(descendantDeptId);
                        depts.add(deptResponseData);
					}
					return depts;
				}
			}
		}
	}

    @Override
    public int countList(DeptBasicInfoExample deptBasicInfoExample) {
        return deptBasicInfoMapper.countByExample(deptBasicInfoExample);
    }

    @Override
    public List<DeptResponseData> findList(DeptBasicInfoExample deptBasicInfoExample) {
        List<DeptBasicInfo> deptBasicInfoList =
                deptBasicInfoMapper.selectByExample(deptBasicInfoExample);
        List<DeptResponseData> deptInfos;
        if (deptBasicInfoList != null)
        {
            deptInfos = new ArrayList<DeptResponseData>();
            DeptResponseData deptResponseData;
            for (DeptBasicInfo deptBasicInfo : deptBasicInfoList)
            {
                deptResponseData = new DeptResponseData();
                try {
                    PropertyUtils.copyProperties(deptResponseData, deptBasicInfo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                String deptLeaderId = deptBasicInfo.getDeptLeaderId();
                UserBasicInfo deptLeaderInfo = userBasicInfoMapper.selectByPrimaryKey(deptLeaderId);
                if (deptLeaderInfo != null)
                {
                    deptResponseData.setDeptLeaderName(deptLeaderInfo.getRealName());
                    deptInfos.add(deptResponseData);
                }
                else
                {
                    throw new RuntimeException("找不到指定的部门领导信息！");
                }

            }
            return deptInfos;
        }
        else
        {
            return null;
        }
    }

    @Override
    public DeptResponseData getDepartmentBasicInfo(DeptBasicInfoExample deptBasicInfoExample) {
        return this.findList(deptBasicInfoExample).get(0);
    }

    @Override
    public String getPrimaryDesendentDeptAndUsers(String deptId) {
        List<DeptResponseData> depts = this.getDesendentDepts(deptId, 1);
        List<UserBasicInfo> users = this.getAllUsersOfDeptExcludingSubDeptByDeptId(deptId);
        Map<String, Object> deptsAndUsers = new HashMap<String, Object>();
        deptsAndUsers.put("depts", depts);
        deptsAndUsers.put("users", users);
        Gson gson = new Gson();
        return gson.toJson(deptsAndUsers);
    }

    @Override
    public boolean addDeptExtension(String deptId, String extensionId, String extensionValue) {
        DeptExtension deptExtension = new DeptExtension();
        deptExtension.setDeptId(deptId);
        deptExtension.setExtensionId(extensionId);
        deptExtension.setExtensionValue(extensionValue);

        String deptExtensionId = ServiceUtil.getUUID();
        deptExtension.setId(deptExtensionId);

        Date createDate = ServiceUtil.getCurrentTime();
        deptExtension.setCreateTime(createDate);

        return deptExtensionMapper.insert(deptExtension) > 0;
    }

    @Override
    public int countDeptExtensionList(DeptExtensionExample deptExtensionExample) {
        return deptExtensionMapper.countByExample(deptExtensionExample);
    }

    @Override
    public boolean deleteDeptExtension(DeptExtensionExample deptExtensionExample) {
        return deptExtensionMapper.deleteByExample(deptExtensionExample) > 0;
    }

    @Transactional
	private boolean addDescendantDept(String deptId, String ancestorDeptId)
	{
		int updateCount;
		/*插入部门关系*/
		DeptRelation deptRelation = new DeptRelation();
		
		if (ancestorDeptId != null && !ancestorDeptId.isEmpty())
		{
			/*检查要添加的子部门是否已经存在*/
			DeptRelationExample checkingExistingDeptExample = new DeptRelationExample();
			checkingExistingDeptExample.createCriteria().andDescendantDeptIdEqualTo(deptId);
			List<DeptRelation> deptRelations = deptRelationMapper.selectByExample(checkingExistingDeptExample);
			if (!deptRelations.isEmpty())
			{
				throw new RuntimeException("子部门已经存在！");
			}
			
			deptRelations.clear();
			
			/*查找上级部门已经存在的关系*/
			DeptRelationExample deptExample = new DeptRelationExample();
			deptExample.createCriteria().andDescendantDeptIdEqualTo(ancestorDeptId);
			deptRelations = deptRelationMapper.selectByExample(deptExample);
			if (deptRelations.isEmpty())
			{
				throw new RuntimeException("无法找到指定的上级部门！");
			}
			else
			{
				for (DeptRelation eachdeptRelation : deptRelations)
				{
					eachdeptRelation.setDescendantDeptId(deptId);
					int pathLength = eachdeptRelation.getPathLength();
					eachdeptRelation.setPathLength(++pathLength);
					updateCount = deptRelationMapper.insert(eachdeptRelation);
					if (updateCount == 0)
					{
						throw new RuntimeException("插入部门关系失败！");
					}
				}
			}
		}
		// 父级部门为空则生成一个记录，该记录所表示的部门关系的上级部门的id为特定字符"ROOT",
        // 路径的深度为1.
		else
		{
			deptRelation.setAncestorDeptId("ROOT");
			deptRelation.setDescendantDeptId(deptId);
			deptRelation.setPathLength(1);
			updateCount = deptRelationMapper.insert(deptRelation);
			if (updateCount != 1)
			{
				throw new RuntimeException("生成根部门失败！");
			}
		}

		/*插入一条指向自己的部门关系*/
		deptRelation.setAncestorDeptId(deptId);
		deptRelation.setDescendantDeptId(deptId);
		deptRelation.setPathLength(0);
		updateCount = deptRelationMapper.insert(deptRelation);
		if (updateCount == 0)
		{
			throw new RuntimeException("插入公司关系时失败！");
		}
		
		return true;
	}

	@Transactional
	public String addDept(DeptRequestData deptRequestData)
	{
		DeptBasicInfo  deptBasicInfo = new DeptBasicInfo();
		
		/*从请求体数据中获得部门基本信息*/
		deptBasicInfo.setCompanyId(deptRequestData.getCompanyId());
		deptBasicInfo.setDeptInfo(deptRequestData.getDeptInfo());
		deptBasicInfo.setDeptName(deptRequestData.getDeptName());
		deptBasicInfo.setDeptOrder(deptRequestData.getDeptOrder());
		deptBasicInfo.setDeptPhone(deptRequestData.getDeptPhone());

		/*插入创建时间和更新时间*/
		deptBasicInfo.setCreateTime(ServiceUtil.getCurrentTime());
		deptBasicInfo.setUpdateTime(ServiceUtil.getCurrentTime());
		
		String ancestorDeptId = deptRequestData.getAncestorDeptId();
		if (ancestorDeptId == null  || ancestorDeptId.isEmpty())
		{
			deptBasicInfo.setIsRootDept("1");
		}
		else
		{
			deptBasicInfo.setIsRootDept("0");
		}
		
		String deptId = addDeptBasicInfo(deptBasicInfo);
		if (deptId == null)
		{
			throw new RuntimeException("插入部门基本信息失败！");
		}
		else
		{
			boolean deptAdditionResult = addDescendantDept(deptId, ancestorDeptId);
			if (!deptAdditionResult)
			{
				throw new RuntimeException("添加部门关系失败！");
			}
			else
			{
				return deptId;
			}
		}
	}
	
	
	private boolean deleteDeptRelation(String deptId)
	{
		return false;
	}

	public boolean deleteDept(String deptId)
	{
		boolean rmResult = false;
		rmResult = deleteDeptBasicInfoByDeptId(deptId);
		
		if (!rmResult)
		{
			return false;
		}
		else
		{
			rmResult = deleteDeptRelation(deptId);
			return rmResult;
		}
	}
}
