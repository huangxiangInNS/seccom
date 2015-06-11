package com.nationsky.seccom.uc.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nationsky.seccom.uc.dao.DeptBasicInfoMapper;
import com.nationsky.seccom.uc.dao.DeptRelationMapper;
import com.nationsky.seccom.uc.dao.UserDeptRelationMapper;
import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.model.DeptBasicInfo;
import com.nationsky.seccom.uc.model.DeptBasicInfoExample;
import com.nationsky.seccom.uc.model.DeptRelation;
import com.nationsky.seccom.uc.model.DeptRelationExample;
import com.nationsky.seccom.uc.model.UserDeptRelation;
import com.nationsky.seccom.uc.model.UserDeptRelationExample;
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

	private String addDeptBasicInfo(DeptBasicInfo departmentBasicInfo) {

		if (departmentBasicInfo == null)
		{
			return null;
		}
		else
		{
			
			//生成部门id
			String deptId = ServiceUtil.getRandomString(8);
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

	public DeptBasicInfo getDepartmentBasicInfo(String deptId) {
		if (deptId == null)
		{
			return null;
		}
		else
		{
			return deptBasicInfoMapper.selectByPrimaryKey(deptId);
		}
	}
	
	public List<String> getAllUserIdOfDeptExcludiingSubDeptByDeptId(String deptId) throws IllegalArgumentException
	{
		/*检查参数*/
		if (deptId == null)
		{
			throw new IllegalArgumentException();
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
			List<String> userIdList = new ArrayList<String>();
			for(UserDeptRelation userDeptRelationship : userRelationships)
			{
				userIdList.add(userDeptRelationship.getUserId());
			}
			return userIdList;
		}
	}
	
	
	public List<String> getSubDepts(String deptId, int length) throws IllegalArgumentException
	{
		if (length < 0)
		{
			throw new IllegalArgumentException();
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
					List<String> deptIds = new ArrayList<String>();

					/*提取关系中的deptId*/
					for (DeptRelation deptRelation : deptRelations)
					{
						deptIds.add(deptRelation.getDescendantDeptId());
					}
					return deptIds;
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
					List<String> deptIds = new ArrayList<String>();

					/*提取关系中的deptId*/
					for (DeptRelation deptRelation : deptRelations)
					{
						deptIds.add(deptRelation.getDescendantDeptId());
					}
					return deptIds;
				}
			}
		}
	}
	
	@Transactional
	public boolean addDescendantDept(String deptId, String ancestorDeptId)
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
		// 父级部门为空则生成一个root部门
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
