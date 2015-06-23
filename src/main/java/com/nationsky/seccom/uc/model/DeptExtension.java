package com.nationsky.seccom.uc.model;

import java.util.Date;

public class DeptExtension {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_extension.id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_extension.dept_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    private String deptId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_extension.extension_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    private String extensionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_extension.extension_value
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    private String extensionValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_extension.create_time
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_extension.id
     *
     * @return the value of sc_uc_dept_extension.id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_extension.id
     *
     * @param id the value for sc_uc_dept_extension.id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_extension.dept_id
     *
     * @return the value of sc_uc_dept_extension.dept_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_extension.dept_id
     *
     * @param deptId the value for sc_uc_dept_extension.dept_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_extension.extension_id
     *
     * @return the value of sc_uc_dept_extension.extension_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public String getExtensionId() {
        return extensionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_extension.extension_id
     *
     * @param extensionId the value for sc_uc_dept_extension.extension_id
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public void setExtensionId(String extensionId) {
        this.extensionId = extensionId == null ? null : extensionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_extension.extension_value
     *
     * @return the value of sc_uc_dept_extension.extension_value
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public String getExtensionValue() {
        return extensionValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_extension.extension_value
     *
     * @param extensionValue the value for sc_uc_dept_extension.extension_value
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public void setExtensionValue(String extensionValue) {
        this.extensionValue = extensionValue == null ? null : extensionValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_extension.create_time
     *
     * @return the value of sc_uc_dept_extension.create_time
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_extension.create_time
     *
     * @param createTime the value for sc_uc_dept_extension.create_time
     *
     * @mbggenerated Tue Jun 23 15:30:57 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}