package team.sgj.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ListItemExample() {
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

        public Criteria andLiIdIsNull() {
            addCriterion("li_id is null");
            return (Criteria) this;
        }

        public Criteria andLiIdIsNotNull() {
            addCriterion("li_id is not null");
            return (Criteria) this;
        }

        public Criteria andLiIdEqualTo(Integer value) {
            addCriterion("li_id =", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdNotEqualTo(Integer value) {
            addCriterion("li_id <>", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdGreaterThan(Integer value) {
            addCriterion("li_id >", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("li_id >=", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdLessThan(Integer value) {
            addCriterion("li_id <", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdLessThanOrEqualTo(Integer value) {
            addCriterion("li_id <=", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdIn(List<Integer> values) {
            addCriterion("li_id in", values, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdNotIn(List<Integer> values) {
            addCriterion("li_id not in", values, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdBetween(Integer value1, Integer value2) {
            addCriterion("li_id between", value1, value2, "liId");
            return (Criteria) this;
        }

        public Criteria andLiIdNotBetween(Integer value1, Integer value2) {
            addCriterion("li_id not between", value1, value2, "liId");
            return (Criteria) this;
        }

        public Criteria andLiUidIsNull() {
            addCriterion("li_uid is null");
            return (Criteria) this;
        }

        public Criteria andLiUidIsNotNull() {
            addCriterion("li_uid is not null");
            return (Criteria) this;
        }

        public Criteria andLiUidEqualTo(Integer value) {
            addCriterion("li_uid =", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidNotEqualTo(Integer value) {
            addCriterion("li_uid <>", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidGreaterThan(Integer value) {
            addCriterion("li_uid >", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("li_uid >=", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidLessThan(Integer value) {
            addCriterion("li_uid <", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidLessThanOrEqualTo(Integer value) {
            addCriterion("li_uid <=", value, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidIn(List<Integer> values) {
            addCriterion("li_uid in", values, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidNotIn(List<Integer> values) {
            addCriterion("li_uid not in", values, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidBetween(Integer value1, Integer value2) {
            addCriterion("li_uid between", value1, value2, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiUidNotBetween(Integer value1, Integer value2) {
            addCriterion("li_uid not between", value1, value2, "liUid");
            return (Criteria) this;
        }

        public Criteria andLiNameIsNull() {
            addCriterion("li_name is null");
            return (Criteria) this;
        }

        public Criteria andLiNameIsNotNull() {
            addCriterion("li_name is not null");
            return (Criteria) this;
        }

        public Criteria andLiNameEqualTo(String value) {
            addCriterion("li_name =", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameNotEqualTo(String value) {
            addCriterion("li_name <>", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameGreaterThan(String value) {
            addCriterion("li_name >", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameGreaterThanOrEqualTo(String value) {
            addCriterion("li_name >=", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameLessThan(String value) {
            addCriterion("li_name <", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameLessThanOrEqualTo(String value) {
            addCriterion("li_name <=", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameLike(String value) {
            addCriterion("li_name like", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameNotLike(String value) {
            addCriterion("li_name not like", value, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameIn(List<String> values) {
            addCriterion("li_name in", values, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameNotIn(List<String> values) {
            addCriterion("li_name not in", values, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameBetween(String value1, String value2) {
            addCriterion("li_name between", value1, value2, "liName");
            return (Criteria) this;
        }

        public Criteria andLiNameNotBetween(String value1, String value2) {
            addCriterion("li_name not between", value1, value2, "liName");
            return (Criteria) this;
        }

        public Criteria andLiFinishIsNull() {
            addCriterion("li_finish is null");
            return (Criteria) this;
        }

        public Criteria andLiFinishIsNotNull() {
            addCriterion("li_finish is not null");
            return (Criteria) this;
        }

        public Criteria andLiFinishEqualTo(Boolean value) {
            addCriterion("li_finish =", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishNotEqualTo(Boolean value) {
            addCriterion("li_finish <>", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishGreaterThan(Boolean value) {
            addCriterion("li_finish >", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishGreaterThanOrEqualTo(Boolean value) {
            addCriterion("li_finish >=", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishLessThan(Boolean value) {
            addCriterion("li_finish <", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishLessThanOrEqualTo(Boolean value) {
            addCriterion("li_finish <=", value, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishIn(List<Boolean> values) {
            addCriterion("li_finish in", values, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishNotIn(List<Boolean> values) {
            addCriterion("li_finish not in", values, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishBetween(Boolean value1, Boolean value2) {
            addCriterion("li_finish between", value1, value2, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiFinishNotBetween(Boolean value1, Boolean value2) {
            addCriterion("li_finish not between", value1, value2, "liFinish");
            return (Criteria) this;
        }

        public Criteria andLiTypeIsNull() {
            addCriterion("li_type is null");
            return (Criteria) this;
        }

        public Criteria andLiTypeIsNotNull() {
            addCriterion("li_type is not null");
            return (Criteria) this;
        }

        public Criteria andLiTypeEqualTo(String value) {
            addCriterion("li_type =", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeNotEqualTo(String value) {
            addCriterion("li_type <>", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeGreaterThan(String value) {
            addCriterion("li_type >", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeGreaterThanOrEqualTo(String value) {
            addCriterion("li_type >=", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeLessThan(String value) {
            addCriterion("li_type <", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeLessThanOrEqualTo(String value) {
            addCriterion("li_type <=", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeLike(String value) {
            addCriterion("li_type like", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeNotLike(String value) {
            addCriterion("li_type not like", value, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeIn(List<String> values) {
            addCriterion("li_type in", values, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeNotIn(List<String> values) {
            addCriterion("li_type not in", values, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeBetween(String value1, String value2) {
            addCriterion("li_type between", value1, value2, "liType");
            return (Criteria) this;
        }

        public Criteria andLiTypeNotBetween(String value1, String value2) {
            addCriterion("li_type not between", value1, value2, "liType");
            return (Criteria) this;
        }

        public Criteria andLiInfoIsNull() {
            addCriterion("li_info is null");
            return (Criteria) this;
        }

        public Criteria andLiInfoIsNotNull() {
            addCriterion("li_info is not null");
            return (Criteria) this;
        }

        public Criteria andLiInfoEqualTo(String value) {
            addCriterion("li_info =", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoNotEqualTo(String value) {
            addCriterion("li_info <>", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoGreaterThan(String value) {
            addCriterion("li_info >", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoGreaterThanOrEqualTo(String value) {
            addCriterion("li_info >=", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoLessThan(String value) {
            addCriterion("li_info <", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoLessThanOrEqualTo(String value) {
            addCriterion("li_info <=", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoLike(String value) {
            addCriterion("li_info like", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoNotLike(String value) {
            addCriterion("li_info not like", value, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoIn(List<String> values) {
            addCriterion("li_info in", values, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoNotIn(List<String> values) {
            addCriterion("li_info not in", values, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoBetween(String value1, String value2) {
            addCriterion("li_info between", value1, value2, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiInfoNotBetween(String value1, String value2) {
            addCriterion("li_info not between", value1, value2, "liInfo");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeIsNull() {
            addCriterion("li_add_time is null");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeIsNotNull() {
            addCriterion("li_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeEqualTo(Date value) {
            addCriterion("li_add_time =", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeNotEqualTo(Date value) {
            addCriterion("li_add_time <>", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeGreaterThan(Date value) {
            addCriterion("li_add_time >", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("li_add_time >=", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeLessThan(Date value) {
            addCriterion("li_add_time <", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("li_add_time <=", value, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeIn(List<Date> values) {
            addCriterion("li_add_time in", values, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeNotIn(List<Date> values) {
            addCriterion("li_add_time not in", values, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeBetween(Date value1, Date value2) {
            addCriterion("li_add_time between", value1, value2, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("li_add_time not between", value1, value2, "liAddTime");
            return (Criteria) this;
        }

        public Criteria andLiImportantIsNull() {
            addCriterion("li_important is null");
            return (Criteria) this;
        }

        public Criteria andLiImportantIsNotNull() {
            addCriterion("li_important is not null");
            return (Criteria) this;
        }

        public Criteria andLiImportantEqualTo(Integer value) {
            addCriterion("li_important =", value, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantNotEqualTo(Integer value) {
            addCriterion("li_important <>", value, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantGreaterThan(Integer value) {
            addCriterion("li_important >", value, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantGreaterThanOrEqualTo(Integer value) {
            addCriterion("li_important >=", value, "liId");
            return (Criteria) this;
        }

        public Criteria andLiImportantLessThan(Integer value) {
            addCriterion("li_important <", value, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantLessThanOrEqualTo(Integer value) {
            addCriterion("li_important <=", value, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantIn(List<Integer> values) {
            addCriterion("li_id in", values, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantNotIn(List<Integer> values) {
            addCriterion("li_important not in", values, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantBetween(Integer value1, Integer value2) {
            addCriterion("li_important between", value1, value2, "liImportant");
            return (Criteria) this;
        }

        public Criteria andLiImportantNotBetween(Integer value1, Integer value2) {
            addCriterion("li_important not between", value1, value2, "liImportant");
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