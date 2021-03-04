package com.framework.commons.cg.mybatis.dom.service;



import com.framework.commons.cg.mybatis.dom.model.JavaModel;
import com.framework.commons.utils.DateTimeUtils;

/**
* @Description: TODO(freemarker 创建service依赖实体)
* @Param: 
* @return: 
* @Author: zcx
* @Date: 2020/7/6 9:20
*/
public class JavaService {
    private String packageName;
    private String className;
    private JavaModel javaModel;
    private String mapperName;
    private String mapperClassName;
    //存放小写mapper类名
    private String lowerMapperClassName;
    private String date;

    public JavaService() {
        date = DateTimeUtils.format(DateTimeUtils.getDate());
    }


    public String getDate() {
        return date;
    }

    public String getMapperClassName() {
        return mapperClassName;
    }

    public String getLowerMapperClassName() {
        return lowerMapperClassName;
    }

    public void setLowerMapperClassName(String lowerMapperClassName) {
        this.lowerMapperClassName = lowerMapperClassName;
    }

    public void setMapperClassName(String mapperClassName) {
        this.mapperClassName = mapperClassName;
        this.lowerMapperClassName = lowerFirst(mapperClassName);
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public JavaModel getJavaModel() {
        return javaModel;
    }

    public void setJavaModel(JavaModel javaModel) {
        this.javaModel = javaModel;
    }

    /**
     * @Description: TODO(将方法名首字母转换为小写)
     * @Param: [className]
     * @return: java.lang.String
     * @Author: zcx
     * @Date: 2020/7/6 8:42
     */
    private String lowerFirst(String className) {
        char[] chars = className.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}