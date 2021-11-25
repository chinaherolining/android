package com.example.myapplication.kotlin

import kotlin.properties.Delegates
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty


class Example{
    var p:String by Delegate()
}

class Delegate{
    operator fun getValue(a:Any?,property:KProperty<*>):String{
        return "$a,这里委托了${property.name}属性"
    }
    operator fun setValue(thisRef:Any?,property:KProperty<*>,value:String){
        println("$thisRef 的${property.name}属性赋值为$value")
    }
}
class Users{
    var name:String by Delegates.observable("初始值"){
        prop,old,new -> println("旧值：$old->新值:$new")
    }
}
fun main(args: Array<String>) {
    val e = Example()
    println(e.p)     // 访问该属性，调用 getValue() 函数

    e.p = "Runoob"   // 调用 setValue() 函数
    println(e.p)

    val lazyValue:String by lazy{
        println("ksksjkdf")
        "Hello"
    }
    print(lazyValue)
    print(lazyValue)

    val user = Users()
    user.name = "第一次赋值"
    user.name = "第二次赋值"
}