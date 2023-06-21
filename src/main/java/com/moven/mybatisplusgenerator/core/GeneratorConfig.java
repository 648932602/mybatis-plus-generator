package com.moven.mybatisplusgenerator.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.fill.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorConfig {

    private static final String URL_TEMP = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false";
    private static final String JAVA_DIR = "/src/main/java";
    private static final String RESOURCE_DIR = "/src/main/resources";
    private static final String DEFAULT_LOGIC_DELETE_FIELD = "deleted";
    private static final List<IFill> DEFAULT_AUTO_FILL_FIELDS = Arrays.asList(
            new Column("created_at", FieldFill.INSERT),
            new Column("updated_at", FieldFill.INSERT_UPDATE),
            new Column("created_user", FieldFill.INSERT),
            new Column("updated_user", FieldFill.INSERT_UPDATE));

    private String author;
    private String projectPath;
    private String basePackage;

    private String mysqlHost;
    private String mysqlPort;
    private String mysqlDatabase;
    private String username;
    private String password;

    private List<IFill> autoFillFields;
    private String logicDeleteField;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getMysqlHost() {
        return mysqlHost;
    }

    public void setMysqlHost(String mysqlHost) {
        this.mysqlHost = mysqlHost;
    }

    public String getMysqlPort() {
        return mysqlPort;
    }

    public void setMysqlPort(String mysqlPort) {
        this.mysqlPort = mysqlPort;
    }

    public String getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(String mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<IFill> getAutoFillFields() {
        return autoFillFields == null ? DEFAULT_AUTO_FILL_FIELDS : autoFillFields;
    }

    public String getLogicDeleteField() {
        return logicDeleteField == null ? DEFAULT_LOGIC_DELETE_FIELD : logicDeleteField;
    }

    public void setLogicDeleteField(String logicDeleteField) {
        this.logicDeleteField = logicDeleteField;
    }

    /**
     * @Description: 返回URL
     * @Date: 2023-06-16 17:36:01
     */
    public String getUrl() {
        return String.format(URL_TEMP, this.mysqlHost, this.mysqlPort, this.mysqlDatabase);
    }

    /**
     * @Description: 返回Java目录
     * @Date: 2023-06-16 18:38:28
     */
    public String getJavaDir() {
        return projectPath + JAVA_DIR;
    }

    /**
     * @Description: 返回Resources目录
     * @Date: 2023-06-16 18:38:39
     */
    public String getResourcesDir() {
        return projectPath + RESOURCE_DIR;
    }

    /**
     * @Description: 添加自动填充字段
     * @param tableFill
     * @return
     * @Date: 2023-06-21 09:53:34
     */
    public void addAutoFillField(IFill tableFill) {
        if (null == autoFillFields) {
            autoFillFields = new ArrayList<>();
        }
        autoFillFields.add(tableFill);
    }
}
