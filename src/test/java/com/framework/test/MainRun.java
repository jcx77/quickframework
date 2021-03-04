package com.framework.test;

import com.framework.commons.shiro.tools.ShiroTools;

public class MainRun {
    public static void main(String[] args) {
        System.out.println(ShiroTools.md5("admin", "admin"));
    }
}
