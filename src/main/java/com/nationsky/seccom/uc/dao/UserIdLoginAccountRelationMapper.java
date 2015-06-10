package com.nationsky.seccom.uc.dao;

import com.nationsky.seccom.uc.model.UserIdLoginAccountRelation;
import com.nationsky.seccom.uc.model.UserIdLoginAccountRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserIdLoginAccountRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int countByExample(UserIdLoginAccountRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int deleteByExample(UserIdLoginAccountRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int deleteByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int insert(UserIdLoginAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int insertSelective(UserIdLoginAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    List<UserIdLoginAccountRelation> selectByExample(UserIdLoginAccountRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    UserIdLoginAccountRelation selectByPrimaryKey(String loginId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserIdLoginAccountRelation record, @Param("example") UserIdLoginAccountRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByExample(@Param("record") UserIdLoginAccountRelation record, @Param("example") UserIdLoginAccountRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByPrimaryKeySelective(UserIdLoginAccountRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_login
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    int updateByPrimaryKey(UserIdLoginAccountRelation record);
}