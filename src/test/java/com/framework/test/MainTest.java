package com.framework.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws ParseException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 1000000; i++) {
            list.add(i + " ");
        }
        System.out.println(new Date().getTime());
//        Iterator<String> it = list.iterator();
//        while (it.hasNext()) {
//            it.next();
//            //if ("1000000 ".equals(it.next())) {
////                //list.remove(str);
//            //    System.out.println(1);
////            }
//            // }
//        }

        for (String str:list){
            //if("1000000 ".equals(str)){
                //list.remove(str);
             //   System.out.println(1);
            //}
        }
        System.out.println(new Date().getTime());


    }

    public static String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
    }

    public static long sj(String oldTime, String newTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        long NTime = df.parse(newTime).getTime();
        //从对象中拿到时间
        long OTime = df.parse(oldTime).getTime();
        long diff = (NTime - OTime);
        return diff;
    }
}
