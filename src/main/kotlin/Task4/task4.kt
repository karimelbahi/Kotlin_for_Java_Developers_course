package main.kotlin.Task4

/*
* Safe casts
* Type cast as throws ClassCastException, if the cast is unsuccessful. Safe cast as? returns null,
* if the cast is unsuccessful. Declare the s variable to make the first line
* print null and the second one throw an exception.*/


fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() eq true
    s2.isEmptyOrNull() eq true

    val s3 = "   "
    s3.isEmptyOrNull() eq false
}

infix fun <T> T.eq(other: T) {
    if (this == other) println("OK")
    else println("Error: $this != $other")
}

fun String?.isEmptyOrNull() = this == null || isEmpty()
