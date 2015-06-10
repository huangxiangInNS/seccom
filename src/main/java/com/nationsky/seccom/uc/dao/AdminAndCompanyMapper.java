package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.AdminAndCompany;
import com.nationsky.seccom.uc.model.AdminAndCompanyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminAndCompanyMapper {
    int countByExample(AdminAndCompanyCriteria example);

    int deleteByExample(AdminAndCompanyCriteria example);

    int insert(AdminAndCompany record);

    int insertSelective(AdminAndCompany record);

    List<AdminAndCompany> selectByExample(AdminAndCompanyCriteria example);

    int updateByExampleSelective(@Param("record") AdminAndCompany record, @Param("example") AdminAndCompanyCriteria example);

    int updateByExample(@Param("record") AdminAndCompany record, @Param("example") AdminAndCompanyCriteria example);
}