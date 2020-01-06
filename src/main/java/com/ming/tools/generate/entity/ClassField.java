package com.ming.tools.generate.entity;

/**
 * Created by Administrator on 2019/9/12 0012.
 */
public class ClassField {

    private String fieldName;

    private String fieldValue;

    private String fieldType;

    private String fieldColumn;

    private String jdbcType;

    public ClassField() {}

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldColumn() {
        return fieldColumn;
    }

    public void setFieldColumn(String fieldColumn) {
        this.fieldColumn = fieldColumn;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    @Override
    public String toString() {
        return "ClassField{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldColumn='" + fieldColumn + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                '}';
    }
}
