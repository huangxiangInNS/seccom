package com.nationsky.seccom.uc.service;

import java.util.Date;
import java.util.List;

import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.domain.CompanyResponseData;
import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.model.DeptBasicInfo;

public interface ICompanyService {
	String addCompany(CompanyRequestData requestData );
	boolean deleteCompany(String companyId);
	Date updateCompanyInfo(CompanyInfo companyInfo);
	CompanyResponseData getCompanyInfoById(String companyId);
    DeptBasicInfo getRootDept(String companyId);
}
