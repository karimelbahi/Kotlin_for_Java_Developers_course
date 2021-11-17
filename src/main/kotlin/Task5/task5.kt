package main.kotlin.Task5



// declare the s variable to make the first line print null
// and the second one throw an exception

fun main(){

    val s = "any type except integer will work"

    println(s as? Int) //null
    println(s as Int?) //exception
}

