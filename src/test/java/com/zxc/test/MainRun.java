package com.zxc.test;

import com.zxc.toolsproject.commons.shiro.tools.ShiroTools;

public class MainRun {
    public static void main(String[] args) {
        System.out.println(ShiroTools.md5("admin", "admin"));
    }
}
