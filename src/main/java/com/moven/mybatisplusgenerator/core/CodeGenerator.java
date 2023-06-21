package com.moven.mybatisplusgenerator.core;

import java.util.Collections;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class CodeGenerator {

    public void generator(GeneratorConfig config) {
        // Mapper.xml 存放目录
        String xmlPg = config.getResourcesDir() + "/" + config.getBasePackage().replace(".", "/") + "/mapper";

        DataSourceConfig.Builder datasourceBuilder = new DataSourceConfig.Builder(
                config.getUrl(), config.getUsername(), config.getPassword());
        datasourceBuilder.typeConvertHandler(EnumTypeConvertHandler.builder().build());
        // 1、配置数据源
        FastAutoGenerator.create(datasourceBuilder)
                // 2、全局配置
                .globalConfig(builder -> {
                    builder.author(config.getAuthor()) // 设置作者名
                            .outputDir(config.getJavaDir()) // 设置输出路径：项目的 java 目录下
                            // .fileOverride() // 覆盖之前的文件
                            .commentDate("yyyy-MM-dd HH:mm:ss") // 注释日期
                            .dateType(DateType.ONLY_DATE) // 定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            // .enableSwagger() //开启 swagger 模式
                            .disableOpenDir(); // 禁止打开输出目录，默认打开
                })
                // 3、包配置
                .packageConfig(builder -> {
                    builder.parent(config.getBasePackage()) // 设置父包名
                            // .moduleName("mp1") //设置模块包名
                            .entity("entity") // pojo 实体类包名
                            .mapper("mapper") // Mapper 包名
                            .xml("mapper") // Mapper XML 包名
                            // .service("service") // Service 包名
                            // .serviceImpl("service.impl") // ServiceImpl 包名
                            // .controller("controller") //Controller 包名
                            // .other("utils") // 自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPg)); // 配置 mapper.xml 路径信息：项目的
                                                                                        // resources 目录下
                })
                // 4、策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(TableHolder.getTableNames()
                            .toArray(new String[TableHolder.getTableNames().size()])) // 设置需要生成的数据表名
                            // .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            // 4.1、Mapper策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖之前的文件
                            .superClass(BaseMapper.class) // 设置父类
                            .formatMapperFileName("%sMapper") // 格式化 mapper 文件名称
                            // .enableMapperAnnotation() //开启 @Mapper 注解
                            .formatXmlFileName("%sMapper") // 格式化 Xml 文件名称
                            .enableBaseResultMap()
                            .enableBaseColumnList()

                            // 4.2、service 策略配置
                            .serviceBuilder()
                            // .superServiceClass("")
                            .enableFileOverride() // 覆盖之前的文件
                            .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
                            .formatServiceFileName("%sService") // 格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称，%s进行匹配表名，如
                            // UserServiceImpl

                            // 4.3、实体类策略配置
                            .entityBuilder()
                            .formatFileName("%sDO")
                            .enableFileOverride() // 覆盖之前的文件
                            // .enableLombok() // 开启 Lombok
                            .disableSerialVersionUID() // 不实现 Serializable 接口，不生产 SerialVersionUID
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .enableColumnConstant()
                            .logicDeleteColumnName(config.getLogicDeleteField()) // 逻辑删除字段名
                            .logicDeletePropertyName(config.getLogicDeleteField()) // 逻辑删除属性名
                            .addTableFills(config.getAutoFillFields()) // 添加自动填充字段
                            .enableTableFieldAnnotation(); // 开启生成实体时生成字段注解
                })
                // .templateConfig(new Consumer<TemplateConfig.Builder>() {
                // @Override
                // public void accept(TemplateConfig.Builder builder) {
                // // 实体类使用我们自定义模板
                // builder.service("templates/Repository.java");
                // }
                // })
                // 5、模板
                .templateEngine(new VelocityTemplateEngine())
                // 设置不生成controller
                .templateConfig(templateConfig -> templateConfig.controller(""))
                .templateConfig(templateConfig -> templateConfig.service(""))
                .templateConfig(templateConfig -> templateConfig.serviceImpl(""))
                // 6、执行
                .execute();

    }

}
