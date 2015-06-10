package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.AdminLogout;
import com.nationsky.seccom.uc.model.AdminLogoutCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminLogoutMapper {
    int countByExample(AdminLogoutCriteria example);

    int deleteByExample(AdminLogoutCriteria example);

    int insert(AdminLogout record);

    int insertSelective(AdminLogout record);

    List<AdminLogout> selectByExample(AdminLogoutCriteria example);

    int updateByExampleSelective(@Param("record") AdminLogout record, @Param("example") AdminLogoutCriteria example);

    int updateByExample(@Param("record") AdminLogout record, @Param("example") AdminLogoutCriteria example);
}