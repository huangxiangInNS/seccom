package com.nationsky.seccom.uc.service;

import java.util.Date;

import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.domain.CompanyResponseData;
import com.nationsky.seccom.uc.model.CompanyInfo;

public interface ICompanyService {
	public String addCompany(CompanyRequestData requestData );
	public boolean deleteCompany(String companyId);
	public Date udpateCompanyInfo(CompanyInfo companyInfo);
	public CompanyResponseData getCompanyInfoById(String companyId);
}
