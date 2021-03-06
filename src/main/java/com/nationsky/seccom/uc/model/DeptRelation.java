package com.nationsky.seccom.uc.model;

public class DeptRelation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_relation.ancestor_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    private String ancestorDeptId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_relation.descendant_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    private String descendantDeptId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sc_uc_dept_relation.path_length
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    private Integer pathLength;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_relation.ancestor_dept_id
     *
     * @return the value of sc_uc_dept_relation.ancestor_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public String getAncestorDeptId() {
        return ancestorDeptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_relation.ancestor_dept_id
     *
     * @param ancestorDeptId the value for sc_uc_dept_relation.ancestor_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void setAncestorDeptId(String ancestorDeptId) {
        this.ancestorDeptId = ancestorDeptId == null ? null : ancestorDeptId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_relation.descendant_dept_id
     *
     * @return the value of sc_uc_dept_relation.descendant_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public String getDescendantDeptId() {
        return descendantDeptId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_relation.descendant_dept_id
     *
     * @param descendantDeptId the value for sc_uc_dept_relation.descendant_dept_id
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void setDescendantDeptId(String descendantDeptId) {
        this.descendantDeptId = descendantDeptId == null ? null : descendantDeptId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sc_uc_dept_relation.path_length
     *
     * @return the value of sc_uc_dept_relation.path_length
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public Integer getPathLength() {
        return pathLength;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sc_uc_dept_relation.path_length
     *
     * @param pathLength the value for sc_uc_dept_relation.path_length
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void setPathLength(Integer pathLength) {
        this.pathLength = pathLength;
    }
}