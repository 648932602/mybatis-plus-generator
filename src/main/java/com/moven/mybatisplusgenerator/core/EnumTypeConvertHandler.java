package com.moven.mybatisplusgenerator.core;

import java.lang.reflect.Field;

import org.jetbrains.annotations.NotNull;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 字段类型转换器
 * @Author: Tony Mo@flsdex.com
 * @Date: 2023-06-21 21:28:20
 */
@Slf4j
@Builder
public class EnumTypeConvertHandler implements ITypeConvertHandler {

    @Override
    public @NotNull IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, MetaInfo metaInfo) {
        IColumnType columnType;

        Field tableNameField, columnNameField;
        String tableName, columnName;
        try {
            tableNameField = MetaInfo.class.getDeclaredField("tableName");
            tableNameField.setAccessible(true);
            tableName = (String) tableNameField.get(metaInfo);

            columnNameField = MetaInfo.class.getDeclaredField("columnName");
            columnNameField.setAccessible(true);
            columnName = (String) columnNameField.get(metaInfo);

            columnType = TableHolder.getTable(tableName).getColumn(columnName);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            columnType = null;
            log.error("EnumTypeConvertHandler convert error: {}", e.getMessage());
        }

        return columnType != null ? columnType : typeRegistry.getColumnType(metaInfo);
    }

}