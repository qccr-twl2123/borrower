package com.trj.jk.web.domain;

import java.util.ArrayList;
import java.util.List;

public class ThirdpartyRequestResponseLogCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public ThirdpartyRequestResponseLogCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
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
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
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

        public Criteria andRequestTypeIsNull() {
            addCriterion("request_type is null");
            return (Criteria) this;
        }

        public Criteria andRequestTypeIsNotNull() {
            addCriterion("request_type is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTypeEqualTo(Byte value) {
            addCriterion("request_type =", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotEqualTo(Byte value) {
            addCriterion("request_type <>", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeGreaterThan(Byte value) {
            addCriterion("request_type >", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("request_type >=", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeLessThan(Byte value) {
            addCriterion("request_type <", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeLessThanOrEqualTo(Byte value) {
            addCriterion("request_type <=", value, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeIn(List<Byte> values) {
            addCriterion("request_type in", values, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotIn(List<Byte> values) {
            addCriterion("request_type not in", values, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeBetween(Byte value1, Byte value2) {
            addCriterion("request_type between", value1, value2, "requestType");
            return (Criteria) this;
        }

        public Criteria andRequestTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("request_type not between", value1, value2, "requestType");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameIsNull() {
            addCriterion("thirdparty_name is null");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameIsNotNull() {
            addCriterion("thirdparty_name is not null");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameEqualTo(String value) {
            addCriterion("thirdparty_name =", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameNotEqualTo(String value) {
            addCriterion("thirdparty_name <>", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameGreaterThan(String value) {
            addCriterion("thirdparty_name >", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameGreaterThanOrEqualTo(String value) {
            addCriterion("thirdparty_name >=", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameLessThan(String value) {
            addCriterion("thirdparty_name <", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameLessThanOrEqualTo(String value) {
            addCriterion("thirdparty_name <=", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameLike(String value) {
            addCriterion("thirdparty_name like", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameNotLike(String value) {
            addCriterion("thirdparty_name not like", value, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameIn(List<String> values) {
            addCriterion("thirdparty_name in", values, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameNotIn(List<String> values) {
            addCriterion("thirdparty_name not in", values, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameBetween(String value1, String value2) {
            addCriterion("thirdparty_name between", value1, value2, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andThirdpartyNameNotBetween(String value1, String value2) {
            addCriterion("thirdparty_name not between", value1, value2, "thirdpartyName");
            return (Criteria) this;
        }

        public Criteria andRequestSourceIsNull() {
            addCriterion("request_source is null");
            return (Criteria) this;
        }

        public Criteria andRequestSourceIsNotNull() {
            addCriterion("request_source is not null");
            return (Criteria) this;
        }

        public Criteria andRequestSourceEqualTo(Boolean value) {
            addCriterion("request_source =", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceNotEqualTo(Boolean value) {
            addCriterion("request_source <>", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceGreaterThan(Boolean value) {
            addCriterion("request_source >", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceGreaterThanOrEqualTo(Boolean value) {
            addCriterion("request_source >=", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceLessThan(Boolean value) {
            addCriterion("request_source <", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceLessThanOrEqualTo(Boolean value) {
            addCriterion("request_source <=", value, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceIn(List<Boolean> values) {
            addCriterion("request_source in", values, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceNotIn(List<Boolean> values) {
            addCriterion("request_source not in", values, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceBetween(Boolean value1, Boolean value2) {
            addCriterion("request_source between", value1, value2, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestSourceNotBetween(Boolean value1, Boolean value2) {
            addCriterion("request_source not between", value1, value2, "requestSource");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIsNull() {
            addCriterion("request_info is null");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIsNotNull() {
            addCriterion("request_info is not null");
            return (Criteria) this;
        }

        public Criteria andRequestInfoEqualTo(String value) {
            addCriterion("request_info =", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotEqualTo(String value) {
            addCriterion("request_info <>", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoGreaterThan(String value) {
            addCriterion("request_info >", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoGreaterThanOrEqualTo(String value) {
            addCriterion("request_info >=", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLessThan(String value) {
            addCriterion("request_info <", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLessThanOrEqualTo(String value) {
            addCriterion("request_info <=", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLike(String value) {
            addCriterion("request_info like", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotLike(String value) {
            addCriterion("request_info not like", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIn(List<String> values) {
            addCriterion("request_info in", values, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotIn(List<String> values) {
            addCriterion("request_info not in", values, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoBetween(String value1, String value2) {
            addCriterion("request_info between", value1, value2, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotBetween(String value1, String value2) {
            addCriterion("request_info not between", value1, value2, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoIsNull() {
            addCriterion("response_info is null");
            return (Criteria) this;
        }

        public Criteria andResponseInfoIsNotNull() {
            addCriterion("response_info is not null");
            return (Criteria) this;
        }

        public Criteria andResponseInfoEqualTo(String value) {
            addCriterion("response_info =", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoNotEqualTo(String value) {
            addCriterion("response_info <>", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoGreaterThan(String value) {
            addCriterion("response_info >", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoGreaterThanOrEqualTo(String value) {
            addCriterion("response_info >=", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoLessThan(String value) {
            addCriterion("response_info <", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoLessThanOrEqualTo(String value) {
            addCriterion("response_info <=", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoLike(String value) {
            addCriterion("response_info like", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoNotLike(String value) {
            addCriterion("response_info not like", value, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoIn(List<String> values) {
            addCriterion("response_info in", values, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoNotIn(List<String> values) {
            addCriterion("response_info not in", values, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoBetween(String value1, String value2) {
            addCriterion("response_info between", value1, value2, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andResponseInfoNotBetween(String value1, String value2) {
            addCriterion("response_info not between", value1, value2, "responseInfo");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNull() {
            addCriterion("ref_id is null");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNotNull() {
            addCriterion("ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefIdEqualTo(String value) {
            addCriterion("ref_id =", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotEqualTo(String value) {
            addCriterion("ref_id <>", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThan(String value) {
            addCriterion("ref_id >", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThanOrEqualTo(String value) {
            addCriterion("ref_id >=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThan(String value) {
            addCriterion("ref_id <", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThanOrEqualTo(String value) {
            addCriterion("ref_id <=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLike(String value) {
            addCriterion("ref_id like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotLike(String value) {
            addCriterion("ref_id not like", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdIn(List<String> values) {
            addCriterion("ref_id in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotIn(List<String> values) {
            addCriterion("ref_id not in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdBetween(String value1, String value2) {
            addCriterion("ref_id between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotBetween(String value1, String value2) {
            addCriterion("ref_id not between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andMtimeIsNull() {
            addCriterion("mtime is null");
            return (Criteria) this;
        }

        public Criteria andMtimeIsNotNull() {
            addCriterion("mtime is not null");
            return (Criteria) this;
        }

        public Criteria andMtimeEqualTo(Integer value) {
            addCriterion("mtime =", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotEqualTo(Integer value) {
            addCriterion("mtime <>", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeGreaterThan(Integer value) {
            addCriterion("mtime >", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mtime >=", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeLessThan(Integer value) {
            addCriterion("mtime <", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeLessThanOrEqualTo(Integer value) {
            addCriterion("mtime <=", value, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeIn(List<Integer> values) {
            addCriterion("mtime in", values, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotIn(List<Integer> values) {
            addCriterion("mtime not in", values, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeBetween(Integer value1, Integer value2) {
            addCriterion("mtime between", value1, value2, "mtime");
            return (Criteria) this;
        }

        public Criteria andMtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("mtime not between", value1, value2, "mtime");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNull() {
            addCriterion("ctime is null");
            return (Criteria) this;
        }

        public Criteria andCtimeIsNotNull() {
            addCriterion("ctime is not null");
            return (Criteria) this;
        }

        public Criteria andCtimeEqualTo(Integer value) {
            addCriterion("ctime =", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotEqualTo(Integer value) {
            addCriterion("ctime <>", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThan(Integer value) {
            addCriterion("ctime >", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ctime >=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThan(Integer value) {
            addCriterion("ctime <", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeLessThanOrEqualTo(Integer value) {
            addCriterion("ctime <=", value, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeIn(List<Integer> values) {
            addCriterion("ctime in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotIn(List<Integer> values) {
            addCriterion("ctime not in", values, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeBetween(Integer value1, Integer value2) {
            addCriterion("ctime between", value1, value2, "ctime");
            return (Criteria) this;
        }

        public Criteria andCtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("ctime not between", value1, value2, "ctime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated do_not_delete_during_merge Tue Jun 13 16:01:17 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table jk_thirdparty_request_response_log
     *
     * @mbg.generated Tue Jun 13 16:01:17 CST 2017
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