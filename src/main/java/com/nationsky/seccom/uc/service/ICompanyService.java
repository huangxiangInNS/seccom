package com.nationsky.seccom.uc.service;

import com.nationsky.seccom.uc.domain.CompanyRequestData;
import com.nationsky.seccom.uc.domain.CompanyResponseData;
import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.model.CompanyInfoExample;
import com.nationsky.seccom.uc.model.DeptBasicInfo;

import java.util.Date;
import java.util.List;

/**
 * 公司信息
 */
public interface ICompanyService {

    /**
     * 添加公司
     *
     * @param requestData
     * @return
     */
    String addCompany(CompanyRequestData requestData);

    /**
     * 根据公司ID删除公司
     *
     * @param companyId
     * @return
     */
    boolean deleteCompany(String companyId);


    /**
     * 更新公司信息
     *
     * @param companyInfo
     * @return
     */
    Date updateCompanyInfo(CompanyInfo companyInfo);


    /**
     * 根据ID查询公司详细信息
     *
     * @param companyId
     * @return
     */
    CompanyResponseData getCompanyInfoById(String companyId);


    /**
     * 根据查询条件查询列表
     *
     * @param example
     * @return
     */
    List<CompanyInfo> findList(CompanyInfoExample example);


    /**
     * 根据查询条件查询记录数
     *
     * @param example
     * @return
     */
    int countList(CompanyInfoExample example);

    /**
     * @param companyId
     * @return
     */
    DeptBasicInfo getRootDept(String companyId);
}
