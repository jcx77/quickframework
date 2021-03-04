package com.framework.test;


import com.framework.commons.cg.database.config.JdbcConfig;
import com.framework.commons.cg.database.config.TableConfig;
import com.framework.commons.cg.mybatis.api.MyBatisGenerator;
import com.framework.commons.cg.mybatis.config.JavaMapperConfig;
import com.framework.commons.cg.mybatis.config.JavaModelConfig;
import com.framework.commons.cg.mybatis.config.MyBatisConfig;
import com.framework.commons.cg.mybatis.config.XmlMapperConfig;
import com.framework.commons.utils.JdbcUtils;

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


        MyBatisConfig myBatisConfig = new MyBatisConfig();
        myBatisConfig.setTargetProject(targetProject);
        myBatisConfig.setJdbcConfig(jdbcConfig);
        myBatisConfig.setTableConfig(tableConfig);
        myBatisConfig.setJavaModelConfig(javaModelConfig);
        myBatisConfig.setJavaMapperConfig(javaMapperConfig);
        myBatisConfig.setXmlMapperConfig(xmlMapperConfig);


        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(myBatisConfig);
        myBatisGenerator.generate();
    }
}