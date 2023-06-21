package com.moven.mybatisplusgenerator.core;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;
import com.moven.mybatisplusgenerator.model.MoTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableHolder {

    private static final Map<String, MoTable> tableMap = Maps.newHashMap();

    /**
     * @Description: 添加表
     * @param table
     * @return
     * @Date: 2023-06-21 20:03:25
     */
    public static void addTable(MoTable table) {
        tableMap.put(table.getTableName(), table);
    }

    /**
     * @Description: 添加表
     * @param tableName
     * @return
     * @Date: 2023-06-21 21:34:21
     */
    public static void addTable(String tableName) {
        MoTable table = new MoTable(tableName);
        tableMap.put(table.getTableName(), table);
    }

    /**
     * @Description: 获取表
     * @param tableName
     * @return
     * @Date: 2023-06-21 20:03:36
     */
    public static MoTable getTable(String tableName) {
        return tableMap.get(tableName);
    }

    /**
     * @Description: 获取全部表名
     * @return
     * @Date: 2023-06-21 20:23:56
     */
    public static Collection<String> getTableNames() {
        return tableMap.keySet();
    }

}
