package com.zxc.toolsproject.commons.vo.utils;

import java.io.Serializable;

/**
 *
 * @project 电子档案格式转换结果
 * @file FileConvertRetVo.java
 * @description 
 * @author   zxj
 */
/**
 *
 * @project 电子档案项目
 * @file FileConvertRetVo.java 创建时间:2020年5月5日上午9:37:34
 * @description 
 * @author   zxj
 */
public class FileConvertRetVo  implements Serializable{
  

    /**
     * @description 转换是否成功
     * @value Value:sfzhcg  
     */
    private String zhsfcg="0";   
   
    /**
     * @description 转换后文件路径
     * @value Value:zhhwjm 
     */
    private String zhhwjm;
  
    /**
     * @description 转换后扩展名
     * @value Value:zzhkzm  
     */
    private String zzhkzm;
    /**
     * @description 档案文件内容
     * @value Value:dawjnr
     */
    private String dawjnr;
    
    /**
     * @description 文件名称
     * @value Value:zhhwjmc  
     */
    private String zhhwjmc;
    
    /**
     * @description 文件大小
     * @value Value:dx  
     */
    private double dx;
    
    public String getZhhwjm() {
        return zhhwjm;
    }
    public void setZhhwjm(String zhhwjm) {
        this.zhhwjm = zhhwjm;
    }

    public String getZzhkzm() {
        return zzhkzm;
    }
    public void setZzhkzm(String zzhkzm) {
        this.zzhkzm = zzhkzm;
    }
    public String getDawjnr() {
        return dawjnr;
    }
    public void setDawjnr(String dawjnr) {
        this.dawjnr = dawjnr;
    }
    public String getZhsfcg() {
        return zhsfcg;
    }
    public void setZhsfcg(String zhsfcg) {
        this.zhsfcg = zhsfcg;
    }
    public String getZhhwjmc() {
        return zhhwjmc;
    }
    public void setZhhwjmc(String zhhwjmc) {
        this.zhhwjmc = zhhwjmc;
    }
    public double getDx() {
        return dx;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
}
