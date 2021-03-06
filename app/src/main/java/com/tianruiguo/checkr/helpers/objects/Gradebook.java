package com.tianruiguo.checkr.helpers.objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tianrui on 2/3/15.
 */
public class Gradebook {
    private final String GRADEBOOK_NUMBER = "gradebookNumber";
    private final String CODE = "code";
    private final String PERIOD = "period";
    private final String MARK = "mark";
    private final String CLASS_NAME = "className";
    private final String MISSING_ASSIGNMENTS = "missingAssignments";
    private final String UPDATED = "updated";
    private final String TREND_DIRECTION = "trendDirection";
    private final String PERCENT_GRADE = "percentGrade";
    private final String COMMENT = "comment";
    private final String IS_USING_CHECK_MARKS = "isUsingCheckMarks";

    private boolean isUsingCheckMarks;

    private Date updated;

    private int gradebookNumber;
    private int missingAssignments;
    private int period;
    private int percentGrade;

    private String code;
    private String mark;
    private String className;
    private String trendDirection;
    private String comment;

    public Gradebook() {
    }

    public Gradebook(JSONObject jsonObject) throws JSONException {
        gradebookNumber = jsonObject.getInt(GRADEBOOK_NUMBER);
        code = jsonObject.getString(CODE);
        period = jsonObject.getInt(PERIOD);
        mark = jsonObject.getString(MARK);
        className = jsonObject.getString(CLASS_NAME);
        missingAssignments = jsonObject.getInt(MISSING_ASSIGNMENTS);

        String date = jsonObject.getString(UPDATED);
        updated = parseDate(date);

        trendDirection = jsonObject.getString(TREND_DIRECTION);
        percentGrade = jsonObject.getInt(PERCENT_GRADE);
        comment = jsonObject.getString(COMMENT);
        isUsingCheckMarks = jsonObject.getBoolean(IS_USING_CHECK_MARKS);

    }

    // dates from server are in the form of "\/Date(1422560175663)\/"
    private Date parseDate(String date) {
        int start = date.indexOf("(") + 1;
        int end = date.indexOf(")");
        if (end != -1 && start != 0) {
            return new Date(Long.parseLong(date.substring(start, end)));
        } else {
            return null;
        }
    }

    public boolean isUsingCheckMarks() {
        return isUsingCheckMarks;
    }

    public void setUsingCheckMarks(boolean isUsingCheckMarks) {
        this.isUsingCheckMarks = isUsingCheckMarks;
    }

    public Date getUpdated() {
        return (getUpdatedEpoch() > 0) ? updated : null;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public long getUpdatedEpoch() {
        return (updated != null) ? updated.getTime() : 0;
    }

    public int getGradebookNumber() {
        return gradebookNumber;
    }

    public void setGradebookNumber(int gradebookNumber) {
        this.gradebookNumber = gradebookNumber;
    }

    public int getMissingAssignments() {
        return missingAssignments;
    }

    public void setMissingAssignments(int missingAssignments) {
        this.missingAssignments = missingAssignments;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPercentGrade() {
        return percentGrade;
    }

    public void setPercentGrade(int percentGrade) {
        this.percentGrade = percentGrade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrendDirection() {
        return trendDirection;
    }

    public void setTrendDirection(String trendDirection) {
        this.trendDirection = trendDirection;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String printSimpleDate() {

        SimpleDateFormat fmt = new SimpleDateFormat("E',' MMM dd 'at' hh:mm a");
        return getUpdated() != null ? fmt.format(getUpdated()) : "Never";
    }
}
