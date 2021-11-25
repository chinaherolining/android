package com.example.myapplication.kotlin

import java.lang.IllegalArgumentException

fun main(args: Array<String>){
    println("Hello World!!")
    println(sum(7,9))
    println(sum1(100,9))
    println(sum2(102,9))
    println(lambda(103,9))
    varss(1,2,4,6,78,65,99)
    variable()
    stringTemple()
    nullSetting()
    println(parseInt("a"))
    println(getStringLength(666))
    forMethod()
    baseDataType()
    // Input name
    println("Enter Your Name: ")
//    val name = readLine()
//    val c = name?.toCharArray()
//    println(c.toString())
//    for (i in c.size)
    println(decimalDigitValue('8'))
    arrayTest()
    stringTest()
    ifTest()
    whenTest(10)
    println(hasPrefix("sdsfprefixssskdjdj"))
    whileTest()
    var persion:Person  = Person()
    persion.lastName = "wang wei"
    println("lastName:${persion.lastName}")
    persion.no = 9
    println("no:${persion.no}")
    persion.no = 20
    println("no:${persion.no}")

    val runoob = Runoob("菜鸟教程","urlslsl",40)
    println(runoob.age)
    println(runoob.city)
    println(runoob.url)
    println(runoob.printTest())

    var realize = Realize()
    realize.f()
    realize.kk()
    Other.Nested().init() //调用格式为：外部类.嵌套类().嵌套类方法/属性

    var s =  Student("Runoob", 18, "S12345", 89)
    s.study()

    val c =  C()
    c.f();

    val t =  Child()
    t.foo();
    t.bar();
    print(t.name)

    var user = User("Runoob")
    user.print()

    val l = mutableListOf<Int>(1,2,3)
    l.swap(0,1);
    println(l.toString())

    printFoo(F())

    var p = null
    println(p.toString())

    println("no:${MyClass.no}")
    MyClass.foo()

    val jack = U(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println(jack)
    println(olderJack)

    val boxInt:Box<Int> = Box<Int>(1)
    val boxString:Box<String> = Box<String>("Runoob")
    println(boxInt.value)
    println(boxString.value)

    var color:Color =Color.BLUE

    println(Color.values())
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)

    printAllValues<Color>()

    val site = object {
        var name:String = "前冲课哦"
        var url:String = "www.runoob.com"
    }
    println(site.name)
    println(site.url)

    var q:Q = Q()
    q.publicFoo()

    var sites = Site()
//    sites.DeskTop.url
    println(Site.DeskTop.url)
    Site.DeskTop.showName()

    val b = BasesImpl(10)
    Deriveds(b).print();
    Deriveds(b).kkk();

}

fun sum(a:Int,b:Int):Int{//Int 参数，返回值 Int
    return a+b;
}

fun sum1(a:Int,b:Int)=a+b

public fun sum2(a:Int,b:Int)=a+b

//lambda表达式
fun lambda(a:Int,b:Int):Int{
    val sumlambda:(Int,Int)->Int={x,y->x+y}
    val j:Int = sumlambda(a,b)
    return j
}

fun varss(vararg v:Int){
    for(vt in v){
        print(vt);
    }
}

fun variable(){
    val a:Int = 1
    val b:Int
    val c =2
    b = 3
    var x = 5
    x += 1
    println("a="+a+"b="+b+"c="+c+"x="+x)
}
fun stringTemple(){
    var a:Int = 1
    val s1 = "a is $a"
    println(s1)

    a = 2
    val s2 = "${s1.replace("is","was")},but now is $a"
    println(s2)
    val s3 = "${sum(8,9)},but now is $a"
    println(s3)

}

fun nullSetting(){
    //类型后面加?表示可为空
    var age:String ?="23"
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt()?:-1
    println("age="+age)
    println("ages="+ages)
    println("ages1="+ages1)
    println("ages2="+ages2)
}

fun parseInt(str:String): Int {
    //类型后面加?表示可为空
    var age:String ?="23"
    //抛出空指针异常
    age =null
    val ages = age?.toInt()?:-1
    return ages
}
fun getStringLength(obj:Any):Int?{
    if(obj is String)
    {
        return obj.length
    }
    return null;
}
fun forMethod(){
    for (i in 1..4) print(i)
    println()
    for (i in 4..1) print(i)
    println()
    for (i in 1..4 step 2) print(i)
    println()
    for (i in 4 downTo 1 step 2) print(i)
    println()
    for (i in 1 until 10) print(i)
}
fun baseDataType():String{
    val a: Int = 1000
    println(a===1000)
    val boxedA:Int?=a
    val anotherA:Int?=a
    println(boxedA===anotherA)
    println(boxedA==anotherA)
    val b:Byte = 1
    val i:Int = b.toInt()
    val l = 1L+3
    println(l)
    return ""
}
fun check(c:Char){
    if(c=='1'){
        println("欧沃我")
    }
}
fun decimalDigitValue(c:Char):Int{
    if(c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt()-'0'.toInt()//显式转换为数字
}
fun arrayTest(){
    val a = arrayOf(1,2,3)
    val b = Array(3,{i -> i*2 })
    println(a[1])
    println(b[2])
    val items = listOf<String>("apple","banana","kiwi")
    for(item in items){
        println(item)
    }
    for(index in items.indices){
        println("item at $index is ${items[index]}")
    }
}
fun stringTest(){
    val text =""" flslls
        |ksjdkfj
        |ksjdlkfj
        |owjti
    """.trimMargin()
    println(text);
    val  i =10
    var s="s is $i"
    println(s)
    s ="kdkdk${s.length}sll${parseInt("89")}ss${'$'}text"
    println(s)
}
fun ifTest(){
    val x = 0
    if(x > 0){
        println("x 大于 0")
    }else if (x == 0){
        println("x 等于 0")
    }else {
        println("x 小于 0")
    }
    val a =1
    val b =2
    val c =if (a > b) a else b
    println("c="+c+" c的值是$c")

    val i = 8
    if(i in 1..9){
        println("x在区间内")
    }
}

fun whenTest(x:Int){
    when(x){
        1-> println("x == 1")
        2-> println("x == 2")
        else->{
            println("x既不是1也不是2")
        }
    }
    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }
    when(x){
        in 1..10->{println("x is in the range")
        println("x is in the range2")}
        !in 10..20-> {println("x is outside the range3")
        println("x is outside the range4")}
        else->println("none of the above")
    }
    when{
        x>10->println("x is odd")
        x<10->println("x is even")
        else-> print("x is funny")
    }
    val items = setOf<String>("apple","banana","kiwi")
    when{
        "orange" in items->println("juicy")
        "apple" in items->println("apple is fine too")
    }
}

fun hasPrefix(x:Any) = when(x){
    is String -> x.startsWith("prefix")
    else->false
}

fun whileTest(){
    println("----while 使用-----")
    var x = -5
    while(x>0){
        print(x--)
    }
    println("----do...while 使用-----")
    var y=-5
    do {
        print(y--)
    }while (y>0)

    for(i in 1..10){
        if(i==3)continue
        print(i)
        if(i>5)break
    }
    println("----@ 使用-----")
    loog@for(j in 1..10) {
        for (i in 1..10) {
            if (i == 3) continue@loog
            println(i)
            if (i == 5) break
        }
    }
}
class Runoob constructor(name:String,urls:String){
    lateinit var name:String
             var age:Int = 0
    lateinit var url:String
     var city:String = name
    constructor(name:String,urls:String,age:Int):this(name,urls){
        this.age = age
    }
    init {
        url = urls
        println("初始化网站的名称：$name")
    }
    fun foo(){
        print("Foo")
    }
    fun printTest(){
        print("我是类的函数")
    }
}
class Person{
    var lastName:String ="Zhang"
        get() =field.toUpperCase()
        set

    var no:Int = 100
        get() = field
        set(value) {
            if(value < 10){
                field = value
            }else{
                field = -1
            }
        }
    var height:Float = 145.4f
        private set
}

open class Base{
    open fun f(){
        print("基础类中的方法")
    }
}
abstract class Derived:Base(){
    fun kk(){
        print("抽象类中的方法")
    }
    override abstract fun f() //复写父类的的方法
}
class Realize:Derived(){
    override fun f() {
        print("实现类中的方法")
    }

}

class Other{
    val numOuther = 1
    class Nested{
        fun init(){
            println("执行了init方法")
        }
    }
    inner class InnerClass{
        val name = "InnerClass"
        fun init(){
            println("我是内部类")
        }
    }
}

open class Persons(name:String){
    constructor(name:String,age:Int):this(name){
        println("------基类次级构造函数--------")
    }
    open fun study(){
        println("我毕业了")
    }

}
class Student: Persons {
    constructor(name:String,age:Int,no:String,score:Int):super(name,age){
            println("--------继承类次级构造函数-------------")
            println("学生名:${name}")
            println("年龄:${age}")
            println("学生号:${no}")
            println("成绩:${score}")
    }
    override fun study(){
        println("我在上大学")
    }
}
open class A{
    open fun f(){
        print("A")
    }
    fun a(){
        print("a")
    }
}
interface B{
    fun f(){print("B")}
    fun b(){print("b")}
}
class C():A(),B{
    override fun f() {
        super<B>.f()
    }

}

interface MyInterface{
    var name:String
    fun bar()
    fun foo(){
        print("foo")
    }

}
class Child:MyInterface{
    override var name: String
        get() = "Not yet implemented"
        set(value) {}

    override fun bar() {
        print("bar")
    }
}
class D:A(),B{
    override fun f() {
        super<B>.f()
    }

}

class User(var name:String)


fun User.print(){
    print("用户名:$name")
}

fun MutableList<Int>.swap(index1:Int,index2:Int){
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

open class E

class F: E(){
    fun foo() { println("成员函数") }
}

fun E.foo() = "c"   // 扩展函数 foo

fun F.foo()  { println("扩展函数") }  // 扩展函数 foo

fun printFoo(f: F) {
    println(f.foo())  // 类型是 C 类
}

fun Any?.toString():String{
    if(this == null) return "null"
    return toString()
}

class MyClass{
    companion object{}
}
fun MyClass.Companion.foo(){
    println("伴随对象的扩展函数")
}
val MyClass.Companion.no:Int
    get() =10

data class U(val name:String,val age: Int)

class Box<T>(t:T){
    var value = t
}

enum class Color{
    RED,BLACK,BLUE,GREEN,WHITE
}

inline fun <reified T: Enum<T>> printAllValues(){
    print(enumValues<T>().joinToString { it.name })
}

class Q{
    private fun foo()=object {
        val x:String ="x"
    }
    fun publicFoo()=object{
        val x:String="x"
    }
    fun bar(){
        val x1 = foo().x
//        val x2 = pulbicFoo().x
    }
}

class Site {
    var name = "斯柯达积分"
    object DeskTop{
        var url = "www.runoob.com"
        fun showName(){
            print("desk legs ") // 错误，不能访问到外部类的方法和变量
        }
    }
}

interface Bases{
    fun print()
}
class BasesImpl(val x:Int):Bases{
    override fun print(){print(x)}
}

class Deriveds(b:Bases):Bases by b{
    fun kkk(){
        println("kkkk")
    }
}