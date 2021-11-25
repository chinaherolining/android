package com.example.myapplication.kotlin
class FirstKotlin(val name:String){
    fun greet(){
        print("Hello World!my name is $name")
    }
//    fun sum(a:Int,b:Int):Int{
//        return a+b;
//    }
    public fun sum(a:Int,b:Int)=a+b

    fun printSum(a:Int,b:Int):Unit{
        print(a+b)
    }

}
fun vars(vararg v:Int){
    for(vt in v){
        print(vt);
    }
}
fun main(args:Array<String>) {
    FirstKotlin("李宁").greet();
    println(FirstKotlin("李宁").sum(13,17));
    FirstKotlin("张三").printSum(33,12);
    vars(12,23,11,45);
    val sumLambda:(Int,Int)->Int={x,y->x+y};
    println(sumLambda(3,4));
    //val a:Int = 1
    /*val b = 2
    val c:Int
    c = 4;
    println("a="+a+" b="+b+" c="+c)*/
    var d = 5
    d +=1
    println("d="+d)

    var a =1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is","was")},but now  is $a"
    println(s2)
    var age:String? =""
    val ages = age!!.toInt()
    val ages1 = age?.toInt()
    val ages2 = age?.toInt()?:-1
    println("age="+age+" ages="+ages+" ages1="+ages1+" ages2="+ages2)
}