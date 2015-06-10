package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.Admin;
import com.nationsky.seccom.uc.model.AdminCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminCriteria example);

    int deleteByExample(AdminCriteria example);

    int deleteByPrimaryKey(String adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminCriteria example);

    Admin selectByPrimaryKey(String adminId);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminCriteria example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminCriteria example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}