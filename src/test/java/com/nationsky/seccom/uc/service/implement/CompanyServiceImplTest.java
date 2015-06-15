package com.nationsky.seccom.uc.service.implement;

import static org.junit.Assert.*;

import com.nationsky.seccom.uc.domain.CompanyRequestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nationsky.seccom.uc.service.ICompanyService;

public class CompanyServiceImplTest
{

	private ICompanyService companyService = null;
	private ClassPathXmlApplicationContext context = null;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("spring_config.xml");
		companyService = (ICompanyService)context.getBean("companyService");
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

	@Test
	public void testAddCompany()
	{
		CompanyRequestData companyRequestData = new CompanyRequestData();
		companyRequestData.setAncestorCompanyId("");
		companyRequestData.setCompanyAddress("航星科技园2号楼");
		companyRequestData.setCompanyAlias("国信");
		companyRequestData.setCompanyName("北京国信灵通网路科技有限责任公司");
		assertNotNull(companyService.addCompany(companyRequestData));
	}

	@Test
	public void testDeleteCompanyInfo()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCompanyInfo()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompanyInfoById()
	{
		fail("Not yet implemented");
	}

}
