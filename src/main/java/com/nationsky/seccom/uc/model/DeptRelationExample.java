package com.nationsky.seccom.uc.model;

import java.util.ArrayList;
import java.util.List;

public class DeptRelationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public DeptRelationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAncestorDeptIdIsNull() {
            addCriterion("ancestor_dept_id is null");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdIsNotNull() {
            addCriterion("ancestor_dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdEqualTo(String value) {
            addCriterion("ancestor_dept_id =", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdNotEqualTo(String value) {
            addCriterion("ancestor_dept_id <>", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdGreaterThan(String value) {
            addCriterion("ancestor_dept_id >", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("ancestor_dept_id >=", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdLessThan(String value) {
            addCriterion("ancestor_dept_id <", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdLessThanOrEqualTo(String value) {
            addCriterion("ancestor_dept_id <=", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdLike(String value) {
            addCriterion("ancestor_dept_id like", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdNotLike(String value) {
            addCriterion("ancestor_dept_id not like", value, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdIn(List<String> values) {
            addCriterion("ancestor_dept_id in", values, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdNotIn(List<String> values) {
            addCriterion("ancestor_dept_id not in", values, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdBetween(String value1, String value2) {
            addCriterion("ancestor_dept_id between", value1, value2, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andAncestorDeptIdNotBetween(String value1, String value2) {
            addCriterion("ancestor_dept_id not between", value1, value2, "ancestorDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdIsNull() {
            addCriterion("descendant_dept_id is null");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdIsNotNull() {
            addCriterion("descendant_dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdEqualTo(String value) {
            addCriterion("descendant_dept_id =", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdNotEqualTo(String value) {
            addCriterion("descendant_dept_id <>", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdGreaterThan(String value) {
            addCriterion("descendant_dept_id >", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("descendant_dept_id >=", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdLessThan(String value) {
            addCriterion("descendant_dept_id <", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdLessThanOrEqualTo(String value) {
            addCriterion("descendant_dept_id <=", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdLike(String value) {
            addCriterion("descendant_dept_id like", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdNotLike(String value) {
            addCriterion("descendant_dept_id not like", value, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdIn(List<String> values) {
            addCriterion("descendant_dept_id in", values, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdNotIn(List<String> values) {
            addCriterion("descendant_dept_id not in", values, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdBetween(String value1, String value2) {
            addCriterion("descendant_dept_id between", value1, value2, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andDescendantDeptIdNotBetween(String value1, String value2) {
            addCriterion("descendant_dept_id not between", value1, value2, "descendantDeptId");
            return (Criteria) this;
        }

        public Criteria andPathLengthIsNull() {
            addCriterion("path_length is null");
            return (Criteria) this;
        }

        public Criteria andPathLengthIsNotNull() {
            addCriterion("path_length is not null");
            return (Criteria) this;
        }

        public Criteria andPathLengthEqualTo(Integer value) {
            addCriterion("path_length =", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthNotEqualTo(Integer value) {
            addCriterion("path_length <>", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthGreaterThan(Integer value) {
            addCriterion("path_length >", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("path_length >=", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthLessThan(Integer value) {
            addCriterion("path_length <", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthLessThanOrEqualTo(Integer value) {
            addCriterion("path_length <=", value, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthIn(List<Integer> values) {
            addCriterion("path_length in", values, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthNotIn(List<Integer> values) {
            addCriterion("path_length not in", values, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthBetween(Integer value1, Integer value2) {
            addCriterion("path_length between", value1, value2, "pathLength");
            return (Criteria) this;
        }

        public Criteria andPathLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("path_length not between", value1, value2, "pathLength");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 09 17:48:11 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_uc_dept_relation
     *
     * @mbggenerated Tue Jun 09 17:48:11 CST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}