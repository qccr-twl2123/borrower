package com.trj.jk.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserPhonebookInfoCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public UserPhonebookInfoCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
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
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdIsNull() {
            addCriterion("loan_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdIsNotNull() {
            addCriterion("loan_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdEqualTo(Integer value) {
            addCriterion("loan_apply_id =", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdNotEqualTo(Integer value) {
            addCriterion("loan_apply_id <>", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdGreaterThan(Integer value) {
            addCriterion("loan_apply_id >", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_apply_id >=", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdLessThan(Integer value) {
            addCriterion("loan_apply_id <", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdLessThanOrEqualTo(Integer value) {
            addCriterion("loan_apply_id <=", value, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdIn(List<Integer> values) {
            addCriterion("loan_apply_id in", values, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdNotIn(List<Integer> values) {
            addCriterion("loan_apply_id not in", values, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdBetween(Integer value1, Integer value2) {
            addCriterion("loan_apply_id between", value1, value2, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andLoanApplyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_apply_id not between", value1, value2, "loanApplyId");
            return (Criteria) this;
        }

        public Criteria andContactsCountIsNull() {
            addCriterion("contacts_count is null");
            return (Criteria) this;
        }

        public Criteria andContactsCountIsNotNull() {
            addCriterion("contacts_count is not null");
            return (Criteria) this;
        }

        public Criteria andContactsCountEqualTo(Integer value) {
            addCriterion("contacts_count =", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountNotEqualTo(Integer value) {
            addCriterion("contacts_count <>", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountGreaterThan(Integer value) {
            addCriterion("contacts_count >", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("contacts_count >=", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountLessThan(Integer value) {
            addCriterion("contacts_count <", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountLessThanOrEqualTo(Integer value) {
            addCriterion("contacts_count <=", value, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountIn(List<Integer> values) {
            addCriterion("contacts_count in", values, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountNotIn(List<Integer> values) {
            addCriterion("contacts_count not in", values, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountBetween(Integer value1, Integer value2) {
            addCriterion("contacts_count between", value1, value2, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andContactsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("contacts_count not between", value1, value2, "contactsCount");
            return (Criteria) this;
        }

        public Criteria andAppSystemIsNull() {
            addCriterion("app_system is null");
            return (Criteria) this;
        }

        public Criteria andAppSystemIsNotNull() {
            addCriterion("app_system is not null");
            return (Criteria) this;
        }

        public Criteria andAppSystemEqualTo(String value) {
            addCriterion("app_system =", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotEqualTo(String value) {
            addCriterion("app_system <>", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemGreaterThan(String value) {
            addCriterion("app_system >", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemGreaterThanOrEqualTo(String value) {
            addCriterion("app_system >=", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLessThan(String value) {
            addCriterion("app_system <", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLessThanOrEqualTo(String value) {
            addCriterion("app_system <=", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemLike(String value) {
            addCriterion("app_system like", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotLike(String value) {
            addCriterion("app_system not like", value, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemIn(List<String> values) {
            addCriterion("app_system in", values, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotIn(List<String> values) {
            addCriterion("app_system not in", values, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemBetween(String value1, String value2) {
            addCriterion("app_system between", value1, value2, "appSystem");
            return (Criteria) this;
        }

        public Criteria andAppSystemNotBetween(String value1, String value2) {
            addCriterion("app_system not between", value1, value2, "appSystem");
            return (Criteria) this;
        }

        public Criteria andCollectNodeIsNull() {
            addCriterion("collect_node is null");
            return (Criteria) this;
        }

        public Criteria andCollectNodeIsNotNull() {
            addCriterion("collect_node is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNodeEqualTo(Byte value) {
            addCriterion("collect_node =", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeNotEqualTo(Byte value) {
            addCriterion("collect_node <>", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeGreaterThan(Byte value) {
            addCriterion("collect_node >", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeGreaterThanOrEqualTo(Byte value) {
            addCriterion("collect_node >=", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeLessThan(Byte value) {
            addCriterion("collect_node <", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeLessThanOrEqualTo(Byte value) {
            addCriterion("collect_node <=", value, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeIn(List<Byte> values) {
            addCriterion("collect_node in", values, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeNotIn(List<Byte> values) {
            addCriterion("collect_node not in", values, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeBetween(Byte value1, Byte value2) {
            addCriterion("collect_node between", value1, value2, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCollectNodeNotBetween(Byte value1, Byte value2) {
            addCriterion("collect_node not between", value1, value2, "collectNode");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(Integer value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(Integer value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(Integer value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(Integer value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(Integer value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(Integer value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<Integer> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<Integer> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(Integer value1, Integer value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(Integer value1, Integer value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated do_not_delete_during_merge Thu Jul 13 10:46:53 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_user_phonebook_info
     *
     * @mbg.generated Thu Jul 13 10:46:53 CST 2017
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