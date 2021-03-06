package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.CompanyInfo;
import com.nationsky.seccom.uc.model.CompanyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int countByExample(CompanyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int deleteByExample(CompanyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int deleteByPrimaryKey(String companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int insert(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int insertSelective(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    List<CompanyInfo> selectByExample(CompanyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    CompanyInfo selectByPrimaryKey(String companyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByExampleSelective(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByExample(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByPrimaryKeySelective(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_company
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByPrimaryKey(CompanyInfo record);
}