package com.nationsky.seccom.uc.service.implement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.service.ICompanyService;

public class CompanyInfoServiceImplTest {
	private ICompanyService companyService = null;
	private ClassPathXmlApplicationContext context = null;
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("spring_config.xml");
		companyService = (ICompanyService)context.getBean("companyInfoService");
		
	}

	@Test
	public void testAddCompanyInfo() {
		CompanyRequestData companyRequestData = new CompanyRequestData();
		companyRequestData.setAncestorCompanyId("");
		companyRequestData.setCompanyName("nationsky");
		
		assertNotNull(companyService.addCompany(companyRequestData));
	}

	@Test
	public void testDeleCompanyInfo() {
		assertFalse(companyService.deleteCompany(""));
		assertFalse(companyService.deleteCompany("00010002"));
		assertTrue(companyService.deleteCompany("00010001"));
	}

	@Test
	public void testUdpateCompanyInfo() {
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyId("00010001");
		companyInfo.setCompanyName("nationsky22");
		
		assertNull(companyService.udpateCompanyInfo(companyInfo));
		
		companyInfo.setCompanyId("00010002");
		companyInfo.setCompanyName("nationsky2");
		
		assertNull(companyService.udpateCompanyInfo(companyInfo));
	}

	@Test
	public void testGetCompanyInfo() {
		
		assertNotNull(companyService.getCompanyInfoById("00010001"));
		assertNull(companyService.getCompanyInfoById("00010002"));
	}
	
	@After
	public void closeContext()
	{
		context.close();
	}

}
