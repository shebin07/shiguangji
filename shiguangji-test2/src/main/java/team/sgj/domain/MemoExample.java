package team.sgj.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andMIdIsNull() {
            addCriterion("m_id is null");
            return (Criteria) this;
        }

        public Criteria andMIdIsNotNull() {
            addCriterion("m_id is not null");
            return (Criteria) this;
        }

        public Criteria andMIdEqualTo(Integer value) {
            addCriterion("m_id =", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotEqualTo(Integer value) {
            addCriterion("m_id <>", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThan(Integer value) {
            addCriterion("m_id >", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("m_id >=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThan(Integer value) {
            addCriterion("m_id <", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThanOrEqualTo(Integer value) {
            addCriterion("m_id <=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdIn(List<Integer> values) {
            addCriterion("m_id in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotIn(List<Integer> values) {
            addCriterion("m_id not in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdBetween(Integer value1, Integer value2) {
            addCriterion("m_id between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotBetween(Integer value1, Integer value2) {
            addCriterion("m_id not between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMUidIsNull() {
            addCriterion("m_uid is null");
            return (Criteria) this;
        }

        public Criteria andMUidIsNotNull() {
            addCriterion("m_uid is not null");
            return (Criteria) this;
        }

        public Criteria andMUidEqualTo(Integer value) {
            addCriterion("m_uid =", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidNotEqualTo(Integer value) {
            addCriterion("m_uid <>", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidGreaterThan(Integer value) {
            addCriterion("m_uid >", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("m_uid >=", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidLessThan(Integer value) {
            addCriterion("m_uid <", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidLessThanOrEqualTo(Integer value) {
            addCriterion("m_uid <=", value, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidIn(List<Integer> values) {
            addCriterion("m_uid in", values, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidNotIn(List<Integer> values) {
            addCriterion("m_uid not in", values, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidBetween(Integer value1, Integer value2) {
            addCriterion("m_uid between", value1, value2, "mUid");
            return (Criteria) this;
        }

        public Criteria andMUidNotBetween(Integer value1, Integer value2) {
            addCriterion("m_uid not between", value1, value2, "mUid");
            return (Criteria) this;
        }

        public Criteria andMInfoIsNull() {
            addCriterion("m_info is null");
            return (Criteria) this;
        }

        public Criteria andMInfoIsNotNull() {
            addCriterion("m_info is not null");
            return (Criteria) this;
        }

        public Criteria andMInfoEqualTo(String value) {
            addCriterion("m_info =", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoNotEqualTo(String value) {
            addCriterion("m_info <>", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoGreaterThan(String value) {
            addCriterion("m_info >", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoGreaterThanOrEqualTo(String value) {
            addCriterion("m_info >=", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoLessThan(String value) {
            addCriterion("m_info <", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoLessThanOrEqualTo(String value) {
            addCriterion("m_info <=", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoLike(String value) {
            addCriterion("m_info like", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoNotLike(String value) {
            addCriterion("m_info not like", value, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoIn(List<String> values) {
            addCriterion("m_info in", values, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoNotIn(List<String> values) {
            addCriterion("m_info not in", values, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoBetween(String value1, String value2) {
            addCriterion("m_info between", value1, value2, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMInfoNotBetween(String value1, String value2) {
            addCriterion("m_info not between", value1, value2, "mInfo");
            return (Criteria) this;
        }

        public Criteria andMAddTimeIsNull() {
            addCriterion("m_add_time is null");
            return (Criteria) this;
        }

        public Criteria andMAddTimeIsNotNull() {
            addCriterion("m_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andMAddTimeEqualTo(Date value) {
            addCriterion("m_add_time =", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeNotEqualTo(Date value) {
            addCriterion("m_add_time <>", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeGreaterThan(Date value) {
            addCriterion("m_add_time >", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("m_add_time >=", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeLessThan(Date value) {
            addCriterion("m_add_time <", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("m_add_time <=", value, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeIn(List<Date> values) {
            addCriterion("m_add_time in", values, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeNotIn(List<Date> values) {
            addCriterion("m_add_time not in", values, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeBetween(Date value1, Date value2) {
            addCriterion("m_add_time between", value1, value2, "mAddTime");
            return (Criteria) this;
        }

        public Criteria andMAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("m_add_time not between", value1, value2, "mAddTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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