package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.AdminLogin;
import com.nationsky.seccom.uc.model.AdminLoginCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminLoginMapper {
    int countByExample(AdminLoginCriteria example);

    int deleteByExample(AdminLoginCriteria example);

    int insert(AdminLogin record);

    int insertSelective(AdminLogin record);

    List<AdminLogin> selectByExample(AdminLoginCriteria example);

    int updateByExampleSelective(@Param("record") AdminLogin record, @Param("example") AdminLoginCriteria example);

    int updateByExample(@Param("record") AdminLogin record, @Param("example") AdminLoginCriteria example);
}