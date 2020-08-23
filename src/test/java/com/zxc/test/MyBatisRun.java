package com.zxc.test;



import com.zxc.toolsproject.commons.cg.database.config.JdbcConfig;
import com.zxc.toolsproject.commons.cg.database.config.TableConfig;
import com.zxc.toolsproject.commons.cg.mybatis.api.MyBatisGenerator;
import com.zxc.toolsproject.commons.cg.mybatis.config.*;
import com.zxc.toolsproject.commons.utils.JdbcUtils;

import java.util.ResourceBundle;

public class MyBatisRun {
    private static final String targetProject = "d:\\MBG";
    private static final String catalog = null;
    private static final String schema = null;
    private static final String tableName = "SYS_DICT_ITEM";
    private static final String modelPackage = "org.zxc.tools.api.sys.model";
    private static final String mapperPackage = "org.zxc.tools.api.sys.mapper";
    private static final String controllerPackage = "org.zxc.tools.api.sys.controller";
    private static final String servicePackage = "org.zxc.tools.api.sys.service";


    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        String url = bundle.getString("spring.datasource.url");
        String username = bundle.getString("spring.datasource.username");
        String password = "123456";

        JdbcConfig jdbcConfig = new JdbcConfig();
        jdbcConfig.setUrl(url);
        jdbcConfig.setUsername(username);
		jdbcConfig.setPassword(password);

		TableConfig tableConfig = new TableConfig();
		tableConfig.setCatalog(catalog);
		tableConfig.setSchema(schema);
		tableConfig.setTableName(tableName);

		JavaModelConfig javaModelConfig = new JavaModelConfig();
		javaModelConfig.setTargetPackage(modelPackage);

		JavaMapperConfig javaMapperConfig = new JavaMapperConfig();
		javaMapperConfig.setTargetPackage(mapperPackage);

		XmlMapperConfig xmlMapperConfig = new XmlMapperConfig();
		xmlMapperConfig.setTargetPackage(mapperPackage + "." + JdbcUtils.getDbType(url));

		//为service 配置路径
		JavaServiceConfig javaServiceConfig = new JavaServiceConfig();
		javaServiceConfig.setTargetPackage(servicePackage);

		//为controller 配置路径
		JavaControllerConfig javaControllerConfig = new JavaControllerConfig();
		javaControllerConfig.setTargetPackage(controllerPackage);

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