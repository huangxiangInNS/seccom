package com.nationsky.seccom.uc.service.implement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nationsky.seccom.uc.domain.DeptRequestData;
import com.nationsky.seccom.uc.model.DeptBasicInfo;
import com.nationsky.seccom.uc.service.IDeptService;

public class DeptServiceImplTest extends DeptServiceImpl {
	private IDeptService deptService = null;
	private ClassPathXmlApplicationContext context = null;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("spring_config.xml");
		deptService = (IDeptService)context.getBean("deptService");
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

	@Test
	public void testAddDeptBasicInfo() {
		DeptBasicInfo deptBasicInfo = new DeptBasicInfo();
		deptBasicInfo.setCompanyId("00010001");
		deptBasicInfo.setDeptName("meap2");
		deptBasicInfo.setDeptOrder(3);
		deptBasicInfo.setDeptOrder(1);
		deptBasicInfo.setDeptLeaderId("1234");
		
	}

	@Test
	public void testDeleteDeptBasicInfoByDeptId() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDeptBasicInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDepartmentBasicInfo() {
		String deptId = "000100010002";
		assertNotNull(deptService.getDepartmentBasicInfo(deptId));
		
		deptId = "hhhh";
		assertNull(deptService.getDepartmentBasicInfo(deptId));
	}

	@Test
	public void testGetAllUserIdOfDeptExcludiingSubDeptByDeptId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubDepts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDept()
	{
		DeptRequestData deptRequestData = new DeptRequestData();
		deptRequestData.setAncestorDeptId("12223");
		deptRequestData.setCompanyId("22222");
		deptRequestData.setDeptName("Seccom");
		deptRequestData.setDeptOrder(2);
		assertNotNull(deptService.addDept(deptRequestData));
	}
}
