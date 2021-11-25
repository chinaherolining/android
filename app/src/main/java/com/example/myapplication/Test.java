package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Test {
    public static void main(String[] args){


        System.out.println("Hello World!");
        getCombinationDate("2021-05-20 ");
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        List<String> list = new ArrayList<String>();
        list.add("str1");
        list.add("test");
//        list.add(100);
        for(int i = 0;i < list.size();i++){
            String name = (String)list.get(i);
            System.out.println("name:"+name);
        }
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("输入结果：类型相同");
        }
        Box<Integer> boxInteger = new Box<Integer>(123456);
        Box<String>  boxString  = new Box<String>("key_value");

        System.out.println("泛型测试：key is"+boxInteger.getKey());
        System.out.println("泛型测试：key is"+boxString.getKey());
        Box  kk = new Box(1234);
        Box  k2 = new Box("string");
        System.out.println(kk.getKey());
        System.out.println(k2.getKey());

        FruitGenerator1 fruitGenerator1 = new FruitGenerator1();
        System.out.println(fruitGenerator1.next());

        String  XSSJ = format(new Date(), "yyyy-MM-dd HH:mm:00");
        System.out.println("xssj"+XSSJ);

        Box<Integer> gInteger = new Box<Integer>(123);
        Box<Number> gNumber = new Box<Number>(456);

        showKeyValue1(gInteger);

        StaticFans.StaticMethod("静态测试");
        StaticFans.<String>StaticMethod("静态测试<String>");
        StaticFans  staticFans = new StaticFans();
        staticFans.OtherMethod(1234);
        staticFans.<Integer>OtherMethod(1234);

    }
    public static void showKeyValue1(Box<?> obj){
        System.out.println("泛型测试:key value is " + obj.getKey());
    }


    public static String format(Date date, String pattern) {
        String dateString = "";
        if (date != null && pattern != null) {
            try {
                dateString = new SimpleDateFormat(pattern).format(date);
            } catch (IllegalArgumentException ex) {
            }
        }
        return dateString;
    }
    public static void getCombinationDate(String date) {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR); // 获取年份
        int month = ca.get(Calendar.MONTH); // 获取月份
        int day = ca.get(Calendar.DATE); // 获取日
        int minute = ca.get(Calendar.MINUTE); // 分
        int hour = ca.get(Calendar.HOUR_OF_DAY); // 小时
        int second = ca.get(Calendar.SECOND); // 秒

        String dateS = date +" "+ hour +":"+ minute +":"+ second;
        System.out.println(dateS);
    }
}
class StaticFans{
//    静态函数
    public static <T> void StaticMethod(T a){
        System.out.println("StaticMethod: "+a.toString());
    }
    public <T> void OtherMethod(T a){
        System.out.println("OtherMethod: "+a.toString());
    }
}
interface Generator<T>{
    public T next();
}
class FruitGenerator<T> implements Generator<T>{
    @Override
    public T next() {
        return null;
    }
}
class FruitGenerator1 implements Generator<String>{
    private String[] fruits = new String[]{"apple","orange","pear"};
    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }
}
class Box<T>{
    private T key;
    public Box(T key){
        this.key = key;
    }
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}