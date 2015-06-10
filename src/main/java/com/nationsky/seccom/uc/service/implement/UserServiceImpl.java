package com.nationsky.seccom.uc.service.implement;

import java.util.Date;
import java.util.List;

import com.nationsky.seccom.uc.domain.LoginInfoRequestData;
import com.nationsky.seccom.uc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nationsky.seccom.uc.dao.UserBasicInfoMapper;
import com.nationsky.seccom.uc.dao.UserDeptRelationMapper;
import com.nationsky.seccom.uc.dao.UserIdLoginAccountRelationMapper;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.UserRequestData;
import com.nationsky.seccom.uc.service.IUserService;
import com.nationsky.seccom.uc.util.ServiceUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserBasicInfoMapper userMapper; // 自动注入userMapper对象。
	@Autowired
	private UserDeptRelationMapper userDeptRelationMapper; // 自动注入用户与部门关系mapper。
	
	@Autowired
	private UserIdLoginAccountRelationMapper userIdLoginAccountRelationMapper;

	private String addUserBasicInfo(UserBasicInfo userBasicInfo) {
		String userId = null;

		// 验证上传的用户信息
		boolean validationResult = validateUploadUserInfo(userBasicInfo);
		if (validationResult == false)
		{
			userId = null;
		}
		else
		{
			/*设置用户的userId*/
			userId = generateUniqueUserId();
			userBasicInfo.setUserId(userId);

			/*设置用户账户创建时间和用户信息更新时间*/
			Date currentDate = ServiceUtil.getCurrentTime();
			userBasicInfo.setCreateTime(currentDate);
			userBasicInfo.setUpdateTime(currentDate);
			
			/*设置用户信息默认值*/
			userBasicInfo.setStatus("1");
			userBasicInfo.setUserSource("0");
			
			/*数据库中插入记录*/
			int updateColumn = userMapper.insert(userBasicInfo);
			if (updateColumn == 0)
			{
				userId = null;
			}
		}

		return userId;
	}

	@Transactional
	public boolean deleteUserBasicInfoByUserId(String userId) {
		int updateColumn = userMapper.deleteByPrimaryKey(userId);
		boolean result = false;

		if (updateColumn == 1)
		{
			UserDeptRelationExample userDeptRelationExample = new UserDeptRelationExample();
			userDeptRelationExample.createCriteria().andUserIdEqualTo(userId);
			int updateCount = 0;
			updateCount = userDeptRelationMapper.deleteByExample(userDeptRelationExample);
			
			if (updateCount == 1)
			{
				result = true;
			}
			else
			{
				throw new RuntimeException("删除员工部门关系失败！");
			}
		}
		else
		{
			result = false;
		}
		return result;
	}

	/**
	 * 
	 */
	public Date updateUserBasicInfo(Request<UserRequestData> request) 
	{
		UserRequestData userRequestData = request.getRequestData();
		UserBasicInfo userBasicInfo = new UserBasicInfo();

		/*从请求体中获得用户基础信息*/
		userBasicInfo.setUserId(userRequestData.getUserId());// 更新用户至少需要携带用户的id。
		userBasicInfo.setIsMarried(userRequestData.getIsMarried());
		userBasicInfo.setNickName(userRequestData.getNickName());
		userBasicInfo.setRealName(userRequestData.getRealName());
		userBasicInfo.setUserAddress(userRequestData.getUserAddress());
		userBasicInfo.setUserBirthDay(userRequestData.getBirthDay());
		userBasicInfo.setUserEmail(userRequestData.getUserEmail());
		userBasicInfo.setUserIdCard(userRequestData.getIDCard());
		userBasicInfo.setUserMobile(userRequestData.getUserMobile());
		userBasicInfo.setUserTelephone(userRequestData.getUserTelephone());
		userBasicInfo.setUserSex(userRequestData.getSex());
		Date currentDate = ServiceUtil.getCurrentTime();
		userBasicInfo.setUpdateTime(currentDate);
		
		if (userBasicInfo.getUserId() == null)
		{
			return null;
		}
		if (userMapper.updateByPrimaryKeySelective(userBasicInfo) == 1)
		{
			return currentDate;
		}
		else
		{
			return null;
		}
		
	}


	public UserBasicInfo getUserBasicInfoByUserId(String userId) {
		if (userId == null)
		{
			return null;
		}
		else {
			UserBasicInfo userBasicInfo = userMapper.selectByPrimaryKey(userId);
			
			if (userBasicInfo == null)
			{
				return null;
			}
			else
			{
				return userBasicInfo;
			}
		}
	}

	@Transactional
	public String addUser(Request<UserRequestData> request)
	{
		/*从请求体中获得用户基本信息*/
		UserRequestData userRequestData = request.getRequestData();
		UserBasicInfo userBasicInfo = new UserBasicInfo();
		userBasicInfo.setIsMarried(userRequestData.getIsMarried());
		userBasicInfo.setNickName(userRequestData.getNickName());
		userBasicInfo.setRealName(userRequestData.getRealName());
		userBasicInfo.setUserAddress(userRequestData.getUserAddress());
		userBasicInfo.setUserBirthDay(userRequestData.getBirthDay());
		userBasicInfo.setUserEmail(userRequestData.getUserEmail());
		userBasicInfo.setUserIdCard(userRequestData.getIDCard());
		userBasicInfo.setUserMobile(userRequestData.getUserMobile());
		userBasicInfo.setUserTelephone(userRequestData.getUserTelephone());
		userBasicInfo.setUserSex(userRequestData.getSex());

		/*用户基本信息插入数据库*/
		String userId = addUserBasicInfo(userBasicInfo);
		if (userId == null)
		{
			throw new RuntimeException("添加用户基本信息失败！");
		}
		
		/*添加用户与公司部门关系*/
		String companyId = userRequestData.getCompanyId();
		String deparmentId = userRequestData.getDeptId();
		String jobId = userRequestData.getJobId();
		if (addUserDeptRelation(userId, companyId, deparmentId, jobId) == false)
		{
			throw new RuntimeException("添加用户部门关系失败！");
		}
		return userId;
	}

	private boolean addUserDeptRelation(String userId, String companyId, String deparmentId, String jobId)
	{
		UserDeptRelation userDeptRelation = new UserDeptRelation();
		userDeptRelation.setCompanyId(companyId);
		userDeptRelation.setDeptId(deparmentId);
		userDeptRelation.setJobId(jobId);
		//其它值暂时写入默认值
		userDeptRelation.setPositionOrder(1);
		userDeptRelation.setStatus("1");
		userDeptRelation.setUserId(userId);
		
		int updateCount = userDeptRelationMapper.insertSelective(userDeptRelation);
		if (updateCount == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private String generateUniqueUserId()
	{
		// 可以选择别的id产生方式。
		return ServiceUtil.getRandomString(20);
	}


	private boolean validateUploadUserInfo(UserBasicInfo user)
	{
		boolean validationResult = true;
		
		if (user == null)
		{
			validationResult = false;
		}
		else if (user.getRealName() == null || user.getRealName().isEmpty())
		{
			validationResult = false;
		}

		return validationResult;
	}

	@Override
	public String createLoginInfo(LoginInfoRequestData loginInfoRequestData)
	{
		if (loginInfoRequestData == null)
		{
			return null;
		}
		else
		{
			/*从用户请求中获取信息*/
            String userId = loginInfoRequestData.getUserId();
			String companyId = loginInfoRequestData.getCompanyId();
			String loginName = loginInfoRequestData.getLoginName();
			String password = loginInfoRequestData.getPassword();
			
			/*生成用户多登录账户关系*/
			UserIdLoginAccountRelation userIdLoginAccountRelation =
					new UserIdLoginAccountRelation();
			userIdLoginAccountRelation.setCompanyId(companyId);
			userIdLoginAccountRelation.setLoginName(loginName);
			userIdLoginAccountRelation.setPassword(password);
			userIdLoginAccountRelation.setCreateTime(ServiceUtil.getCurrentTime());
			String loginId = ServiceUtil.getRandomString(20);

			userIdLoginAccountRelation.setUserId(userId);
			userIdLoginAccountRelation.setLoginId(loginId);

			/*插入数据库*/
			int updateCount = 0;
			updateCount =
					userIdLoginAccountRelationMapper.insert(userIdLoginAccountRelation);
			
			if (updateCount == 1)
			{
				return userId;// 返回用户id
			}
			else
			{
				throw new RuntimeException("登录信息创建失败！");
			}
		}
	}


    @Override
    public String checkLoginInfo(LoginInfoRequestData loginInfoRequestData) {
        String companyId = loginInfoRequestData.getCompanyId();
        String loginName = loginInfoRequestData.getLoginName();
        String password = loginInfoRequestData.getPassword();

        UserIdLoginAccountRelationExample example = new UserIdLoginAccountRelationExample();
        example.createCriteria().andCompanyIdEqualTo(companyId).andLoginNameEqualTo(loginName)
                .andPasswordEqualTo(password);

        List<UserIdLoginAccountRelation> relations = userIdLoginAccountRelationMapper.selectByExample(example);

        if (relations == null)
        {
            return null;
        }
        else
        {
            if (relations.size() != 1)
            {
                return null;
            }
            else
            {
                return relations.get(0).getUserId();
            }
        }
    }
}
