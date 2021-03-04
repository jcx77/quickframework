package com.framework.commons.cg.mybatis.config;


import com.framework.commons.cg.database.config.JdbcConfig;
import com.framework.commons.cg.database.config.TableConfig;

public class MyBatisConfig {
    private String targetProject;
    private JdbcConfig jdbcConfig;
    private TableConfig tableConfig;
    private JavaModelConfig javaModelConfig;
    private JavaMapperConfig javaMapperConfig;
    private XmlMapperConfig xmlMapperConfig;
    private JavaServiceConfig javaServiceConfig;
    private JavaControllerConfig javaControllerConfig;

    public JavaControllerConfig getJavaControllerConfig() {
        return javaControllerConfig;
    }

    public void setJavaControllerConfig(JavaControllerConfig javaControllerConfig) {
        this.javaControllerConfig = javaControllerConfig;
    }

    public JavaServiceConfig getJavaServiceConfig() {
        return javaServiceConfig;
    }

    public void setJavaServiceConfig(JavaServiceConfig javaServiceConfig) {
        this.javaServiceConfig = javaServiceConfig;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public JdbcConfig getJdbcConfig() {
        return jdbcConfig;
    }

    public void setJdbcConfig(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public JavaModelConfig getJavaModelConfig() {
        return javaModelConfig;
    }

    public void setJavaModelConfig(JavaModelConfig javaModelConfig) {
        this.javaModelConfig = javaModelConfig;
    }

    public JavaMapperConfig getJavaMapperConfig() {
        return javaMapperConfig;
    }

    public void setJavaMapperConfig(JavaMapperConfig javaMapperConfig) {
        this.javaMapperConfig = javaMapperConfig;
    }

    public XmlMapperConfig getXmlMapperConfig() {
        return xmlMapperConfig;
    }

    public void setXmlMapperConfig(XmlMapperConfig xmlMapperConfig) {
        this.xmlMapperConfig = xmlMapperConfig;
    }
}