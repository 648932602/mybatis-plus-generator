package com.moven.mybatisplusgenerator.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class MoTable {

    private String tableName;
    private Map<String, MoColumn> columnMap;

    public MoTable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * @Description: 添加字段
     * @param column
     * @return
     * @Date: 2023-06-21 20:01:00
     */
    public void addColumn(MoColumn column) {
        if (null == columnMap) {
            columnMap = Maps.newHashMap();
        }
        columnMap.put(column.getColumnName(), column);
    }

    /**
     * @Description: 获取字段
     * @param columnName
     * @return
     * @Date: 2023-06-21 20:12:58
     */
    public MoColumn getColumn(String columnName) {
        return columnMap == null ? null : columnMap.get(columnName);
    }

}
