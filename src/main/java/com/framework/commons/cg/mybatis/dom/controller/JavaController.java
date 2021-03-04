package com.framework.commons.cg.mybatis.dom.controller;

import com.framework.commons.cg.mybatis.dom.model.JavaModel;
import com.framework.commons.utils.DateTimeUtils;

/**
 * @Description: TODO(freemarker 创建controller依赖实体)
 * @Param:
 * @return:
 * @Author: zcx
 * @Date: 2020/7/6 9:06
 */
public class JavaController {
    private String packageName;
    private String className;
    private JavaModel javaModel;
    private String serviceName;
    private String serviceClassName;
    //存放小写mapper类名
    private String lowerServiceClassName;
    private String date;

    public JavaController() {
        date = DateTimeUtils.format(DateTimeUtils.getDate());
    }


    public String getDate() {
        return date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
        this.lowerServiceClassName = lowerFirst(serviceClassName);
    }

    public String getLowerServiceClassName() {
        return lowerServiceClassName;
    }

    public void setLowerServiceClassName(String lowerServiceClassName) {
        this.lowerServiceClassName = lowerServiceClassName;
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