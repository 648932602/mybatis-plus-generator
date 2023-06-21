package com.moven.mybatisplusgenerator.instance;

import com.moven.mybatisplusgenerator.core.CodeGenerator;
import com.moven.mybatisplusgenerator.core.GeneratorConfig;
import com.moven.mybatisplusgenerator.core.TableHolder;
import com.moven.mybatisplusgenerator.model.MoColumn;
import com.moven.mybatisplusgenerator.model.MoTable;

public class DemoGenerator {
    public static void main(String[] args) {
        // 项目根路径，绝对路径
        String projectPath = "/Users/username/projectname";

        GeneratorConfig config = GeneratorConfig.builder()
                // 作者姓名
                .author("username")
                // 项目路径
                .projectPath(projectPath)
                // java类package属性公共前缀
                .basePackage("com.moven.xxx.xxx.entity")
                // 数据库相关配置
                .mysqlHost("localhost")
                .mysqlPort("3306")
                .mysqlDatabase("xxx")
                .username("root")
                .password("root")
                .build();

        // 要生成的表名，可同时生成多个
        MoTable table1 = new MoTable("table1");
        // 可以自定义列类型映射
        table1.addColumn(new MoColumn("user_type", "UserTypeEnum", "demo.enums.UserTypeEnum"));
        TableHolder.addTable(table1);
        TableHolder.addTable("table2");

        CodeGenerator generator = new CodeGenerator();
        generator.generator(config);
    }
}
