package com.zxc.test;


import com.zxc.toolsproject.commons.cg.database.config.JdbcConfig;
import com.zxc.toolsproject.commons.cg.database.config.TableConfig;
import com.zxc.toolsproject.commons.cg.mybatis.api.MyBatisGenerator;
import com.zxc.toolsproject.commons.cg.mybatis.config.*;
import com.zxc.toolsproject.commons.utils.JdbcUtils;

import java.util.ResourceBundle;

public class MyBatisRun {
    //生成文件存储目录
    private static final String targetProject = "d:\\MBG";
    private static final String catalog = null;
    private static final String schema = null;
    //项目包路径
    private static final String topPackage = "com.zxc.toolsproject.";
    //表名
    private static final String tableName = "SYS_USER_ORGAN";
    private static final String modelPackage = "api.sys.user.model";
    private static final String mapperPackage = "api.sys.user.mapper";
    private static final String controllerPackage = "api.sys.user.controller";
    private static final String servicePackage = "api.sys.user.service";

    //生成格式   topPackage + appPackage

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application-dev");
        String url = bundle.getString("spring.datasource.url");
        String username = bundle.getString("spring.datasource.username");
        String password = "root";

        JdbcConfig jdbcConfig = new JdbcConfig();
        jdbcConfig.setUrl(url);
        jdbcConfig.setUsername(username);
        jdbcConfig.setPassword(password);

        TableConfig tableConfig = new TableConfig();
        tableConfig.setCatalog(catalog);
        tableConfig.setSchema(schema);
        tableConfig.setTableName(tableName);
        //装载model路径
        JavaModelConfig javaModelConfig = new JavaModelConfig(topPackage+modelPackage);
        //装载 mapper路径
        JavaMapperConfig javaMapperConfig = new JavaMapperConfig(topPackage+mapperPackage);
        //装载 xml路径
        XmlMapperConfig xmlMapperConfig = new XmlMapperConfig(topPackage+mapperPackage + "." + JdbcUtils.getDbType(url));
        //为service 配置路径
        JavaServiceConfig javaServiceConfig = new JavaServiceConfig(topPackage+servicePackage);
        //为controller 配置路径
        JavaControllerConfig javaControllerConfig = new JavaControllerConfig(topPackage+controllerPackage);

        MyBatisConfig myBatisConfig = new MyBatisConfig();
        myBatisConfig.setTargetProject(targetProject);
        myBatisConfig.setJdbcConfig(jdbcConfig);
        myBatisConfig.setTableConfig(tableConfig);
        myBatisConfig.setJavaModelConfig(javaModelConfig);
        myBatisConfig.setJavaMapperConfig(javaMapperConfig);
        myBatisConfig.setXmlMapperConfig(xmlMapperConfig);
        myBatisConfig.setJavaServiceConfig(javaServiceConfig);
        myBatisConfig.setJavaControllerConfig(javaControllerConfig);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(myBatisConfig);
        myBatisGenerator.generate();
    }
}