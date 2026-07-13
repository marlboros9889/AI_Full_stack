package com.marinboy.db;

/** Oracle 테이블과 컬럼 구조 조회에 함께 사용하는 DTO입니다. */
public class DbSchemaDto {
    private String tableName;
    private String columnName;
    private String dataType;
    private String nullable;
    private Integer columnOrder;

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String getColumnName() { return columnName; }
    public void setColumnName(String columnName) { this.columnName = columnName; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public String getNullable() { return nullable; }
    public void setNullable(String nullable) { this.nullable = nullable; }
    public Integer getColumnOrder() { return columnOrder; }
    public void setColumnOrder(Integer columnOrder) { this.columnOrder = columnOrder; }
}
