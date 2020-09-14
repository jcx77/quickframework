package com.zxc.toolsproject.commons.cg.mybatis.api;


import com.zxc.toolsproject.commons.cg.database.api.DatabaseGenerator;
import com.zxc.toolsproject.commons.cg.database.config.DatabaseConfig;
import com.zxc.toolsproject.commons.cg.database.dom.Column;
import com.zxc.toolsproject.commons.cg.database.dom.Table;
import com.zxc.toolsproject.commons.cg.mybatis.config.MyBatisConfig;
import com.zxc.toolsproject.commons.cg.mybatis.converter.DBConverter;
import com.zxc.toolsproject.commons.cg.mybatis.converter.DBConverterFactory;
import com.zxc.toolsproject.commons.cg.mybatis.dom.controller.JavaController;
import com.zxc.toolsproject.commons.cg.mybatis.dom.mapper.JavaMapper;
import com.zxc.toolsproject.commons.cg.mybatis.dom.model.Field;
import com.zxc.toolsproject.commons.cg.mybatis.dom.model.JavaModel;


import com.zxc.toolsproject.commons.cg.mybatis.dom.service.JavaService;
import com.zxc.toolsproject.commons.utils.JdbcUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisGenerator {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MyBatisConfig myBatisConfig;
	private List<Table> tables;
	private List<JavaModel> javaModels;
	private List<JavaMapper> javaMappers;
	private List<JavaService> javaServices;
	private List<JavaController> javaControllers;


	public MyBatisGenerator(MyBatisConfig myBatisConfig) {
		this.myBatisConfig = myBatisConfig;
	}

	private void initTables() {
		DatabaseConfig databaseConfig = new DatabaseConfig();
		databaseConfig.setJdbcConfig(myBatisConfig.getJdbcConfig());
		databaseConfig.setTableConfig(myBatisConfig.getTableConfig());
		DatabaseGenerator databaseGenerator = new DatabaseGenerator(databaseConfig);
		tables = databaseGenerator.generate();
	}
	//
	private void initJavaModels() {
		try {
			javaModels = new ArrayList<>();
			for (Table table : tables) {
				javaModels.add(createModel(table));
			}
			logger.info("初始化JavaModels成功");
		} catch (Exception e) {
			logger.error("初始化JavaModels失败", e);
		}
	}

	private void initJavaMappers() {
		try {
			javaMappers = new ArrayList<JavaMapper>();
			for (JavaModel javaModel : javaModels) {
				JavaMapper javaMapper = new JavaMapper();
				javaMapper.setPackageName(myBatisConfig.getJavaMapperConfig().getTargetPackage());
				javaMapper.setClassName(javaModel.getClassName() + "Mapper");
				javaMapper.setJavaModel(javaModel);
				javaMappers.add(javaMapper);
			}
			logger.info("初始化JavaMappers成功");
		} catch (Exception e) {
			logger.error("初始化JavaMappers失败", e);
		}
	}
	
	/**
	* @Description: TODO(初始化javaService实体 )
	* @Param: []
	* @return: void
	* @Author: zcx
	* @Date: 2020/7/6 9:21
	*/
	private void initJavaService() {
		try {
			javaServices = new ArrayList<>();
			for (JavaModel javaModel : javaModels) {
				JavaService javaService = new JavaService();
				javaService.setPackageName(myBatisConfig.getJavaServiceConfig().getServicePackage());
				javaService.setClassName(javaModel.getClassName() + "Service");
				javaService.setJavaModel(javaModel);
				javaService.setMapperName(myBatisConfig.getJavaMapperConfig().getTargetPackage());
				javaService.setMapperClassName(javaModel.getClassName() + "Mapper");
				javaServices.add(javaService);
			}
			logger.info("初始化JavaService成功");
		} catch (Exception e) {
			logger.error("初始化JavaService失败", e);
		}
	}

	private void initJavaController() {
		try {
			javaControllers = new ArrayList<>();
			for (JavaModel javaModel : javaModels) {
				JavaController javaController = new JavaController();
				javaController.setPackageName(myBatisConfig.getJavaControllerConfig().getControllerPackage());
				javaController.setClassName(javaModel.getClassName() + "Controller");
				javaController.setJavaModel(javaModel);
				javaController.setServiceName(myBatisConfig.getJavaServiceConfig().getServicePackage());
				javaController.setServiceClassName(javaModel.getClassName() + "Service");
				javaControllers.add(javaController);
			}
			logger.info("初始化JavaController成功");
		} catch (Exception e) {
			logger.error("初始化JavaController失败", e);
		}
	}

	private void generateJavaModels() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(JavaModel.class, "");
			cfg.setDefaultEncoding("UTF-8");
			for (JavaModel javaModel : javaModels) {
				Template temp = cfg.getTemplate("JavaModel.ftl", cfg.getDefaultEncoding());
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("data", javaModel);
				String path = myBatisConfig.getTargetProject() + "\\" + javaModel.getPackageName().replace(".", "\\") + "\\" + javaModel.getClassName() + ".java";
				Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(path)));
				temp.process(root, out);
				out.close();
			}
			logger.info("创建JavaModels成功");
		} catch (Exception e) {
			logger.error("创建JavaModels失败", e);
		}
	}

	private void generateJavaMappers() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(JavaMapper.class, "");
			cfg.setDefaultEncoding("UTF-8");
			for (JavaMapper javaMapper : javaMappers) {
				Template temp = cfg.getTemplate("JavaMapper.ftl", cfg.getDefaultEncoding());
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("data", javaMapper);
				String path = myBatisConfig.getTargetProject() + "\\" + javaMapper.getPackageName().replace(".", "\\") + "\\" + javaMapper.getClassName() + ".java";
				Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(path)));
				temp.process(root, out);
				out.close();
			}
			logger.info("创建JavaMappers成功");
		} catch (Exception e) {
			logger.error("创建JavaMappers失败", e);
		}
	}

	private void generateXmlMappers() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(JavaMapper.class, "");
			cfg.setDefaultEncoding("UTF-8");
			for (JavaMapper javaMapper : javaMappers) {
				Template temp = cfg.getTemplate("XmlMapper.ftl", cfg.getDefaultEncoding());
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("data", javaMapper);
				String path = myBatisConfig.getTargetProject() + "\\" + myBatisConfig.getXmlMapperConfig().getTargetPackage().replace(".", "\\") + "\\" + javaMapper.getClassName() + ".xml";
				Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(path)));
				temp.process(root, out);
				out.close();
			}
			logger.info("创建XmlMappers成功");
		} catch (Exception e) {
			logger.error("创建XmlMappers失败", e);
		}
	}

	private void generateJavaService() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(JavaService.class, "");
			cfg.setDefaultEncoding("UTF-8");
			for (JavaService javaService : javaServices) {
				Template temp = cfg.getTemplate("JavaService.ftl", cfg.getDefaultEncoding());
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("data", javaService);
				String path = myBatisConfig.getTargetProject() + "\\" + javaService.getPackageName().replace(".", "\\") + "\\" + javaService.getClassName() + ".java";
				Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(path)));
				temp.process(root, out);
				out.close();
			}
			logger.info("创建JavaService成功");
		} catch (Exception e) {
			logger.error("创建JavaService失败", e);
		}
	}

	private void generateJavaController() {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(JavaController.class, "");
			cfg.setDefaultEncoding("UTF-8");
			for (JavaController javaController : javaControllers) {
				Template temp = cfg.getTemplate("JavaController.ftl", cfg.getDefaultEncoding());
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("data", javaController);
				String path = myBatisConfig.getTargetProject() + "\\" + javaController.getPackageName().replace(".", "\\") + "\\" + javaController.getClassName() + ".java";
				Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(path)));
				temp.process(root, out);
				out.close();
			}
			logger.info("创建JavaController成功");
		} catch (Exception e) {
			logger.error("创建JavaController失败", e);
		}
	}

	private JavaModel createModel(Table table) {
		DBConverter dbConverter = DBConverterFactory.create(JdbcUtils.getDbType(myBatisConfig.getJdbcConfig().getUrl()));
		JavaModel javaModel = new JavaModel();
		javaModel.setPackageName(myBatisConfig.getJavaModelConfig().getTargetPackage());
		javaModel.setTableName(table.getName());
		javaModel.setClassName(tableNameToModelClassName(table.getName()));
		for (Column column : table.getColumns()) {
			Field field = new Field();
			field.setName(columnNameToFieldName(column.getName()));
			field.setColumn(column);
			Class<?> type = dbConverter.dbTypeToJavaType(column.getType(), column.getSize(), column.getPrecision());
			field.setType(type);
			String className = type.getName();
			if (!className.startsWith("java.lang.")) {
				javaModel.addImports(className);
			}
			javaModel.addFields(field);
		}
		return javaModel;
	}

	private String tableNameToModelClassName(String tableName) {
		StringBuilder modelClassName = new StringBuilder();
		String[] ss = tableName.toLowerCase().split("_");
		for (String s : ss) {
			modelClassName.append(s.substring(0, 1).toUpperCase());
			modelClassName.append(s.substring(1));
		}
		return modelClassName.toString();
	}

	private String columnNameToFieldName(String columnName) {
		StringBuilder fieldName = new StringBuilder();
		String[] ss = columnName.toLowerCase().split("_");
		fieldName.append(ss[0]);
		for (int i = 1; i < ss.length; i++) {
			fieldName.append(ss[i].substring(0, 1).toUpperCase());
			fieldName.append(ss[i].substring(1));
		}
		return fieldName.toString();
	}

	public void generate() {
		initTables();
		initJavaModels();
		generateJavaModels();
		initJavaMappers();
		generateJavaMappers();
		generateXmlMappers();
		initJavaService();
		generateJavaService();
		initJavaController();
		generateJavaController();
	}
}