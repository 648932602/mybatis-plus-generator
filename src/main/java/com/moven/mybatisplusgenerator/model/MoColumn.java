package com.moven.mybatisplusgenerator.model;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoColumn implements IColumnType {
    private String columnName;
    private String columnType;
    private String columnPkg;

    @Override
    public String getType() {
        return columnType;
    }

    @Override
    public String getPkg() {
        return columnPkg;
    }
}
