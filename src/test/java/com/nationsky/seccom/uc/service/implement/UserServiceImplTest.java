package com.nationsky.seccom.uc.service.implement;

import static org.junit.Assert.*;

import com.nationsky.seccom.uc.service.IAccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.nationsky.seccom.uc.domain.Request;
import com.nationsky.seccom.uc.domain.UserRequestData;
import com.nationsky.seccom.uc.service.IUserService;

public class UserServiceImplTest
{
	private IUserService userService = null;
	private IAccountService accountService = null;
	private ClassPathXmlApplicationContext context = null;
	

	@Before
	public void setUp() throws Exception
	{
		context = new ClassPathXmlApplicationContext("spring_config.xml");
		userService = (IUserService)context.getBean("userService");
	}

	@After
	public void tearDown() throws Exception
	{
		context.close();
	}

	@Test
	public void testDeleteUserBasicInfoByUserId()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserBasicInfo()
	{
		UserRequestData userRequestData = new UserRequestData();

		/*userid存在，此时返回不为空*/
		userRequestData.setUserId("us5od1xq4ltx4jkfzx2i");
		userRequestData.setUserEmail("15534@qq.com");
		assertNotNull(userService.updateUserBasicInfo(userRequestData));

		/*userid不存在，此时返回空*/
		userRequestData.setUserId("us5od1xq4ltx4jkfzx2");
		userRequestData.setUserEmail("15534@qq.com");
		assertNull(userService.updateUserBasicInfo(userRequestData));
	}

	@Test
	public void testGetUserBasicInfoByUserId()
	{
		// userid存在，此时返回不为空
		assertNotNull(userService.getUserBasicInfoByUserId("us5od1xq4ltx4jkfzx2i"));
		
		// userid不存在，此时返回为空
		assertNull(userService.getUserBasicInfoByUserId("us5od1xq4ltx4jkfzx2"));
	}

	@Test
	public void testAddUser()
	{
		Request<UserRequestData> request = new Request<UserRequestData>();
		UserRequestData userRequestData = new UserRequestData();
		request.setRequestData(userRequestData);
		
		/*测试关键新建健全*/
		request.getRequestData().setRealName("huangxiang");
		assertNotNull(userService.addUser(request));
		
		/*测试关键信息缺失*/
		request.getRequestData().setRealName("");
		assertNull(userService.addUser(request));
	}
	
	@Test
	public void testAddLoginAccount()
	{
		UserRequestData userRequestData = new UserRequestData();
		userRequestData.setCompanyId("2222232323");

		
//		userRequestData.setLoginName(null);
//		userRequestData.setPassword("222222");
//		userRequestData.setJobName("2222232323");
//		assertNull(userService.createLoginInfo(userRequestData, "xxxxxx"));
	}

	@Test
    public void testSetNewPassword()
    {
        String userId = "VZFZKLnSE8sNj6FCL6tV";
        String loginName = "huangxiang";
        String newPassword = "666666";

        accountService.setNewPassword(userId, loginName, newPassword);
    }

    @Test
    public void testSetSign()
    {
        String userId = "VZFZKLnSE8sNj6FCL6tVx";
        String loginName = "huangxiang";
        String newSign = "测试更新个性签名";
        accountService.setSign(userId, loginName, newSign);
    }

}
