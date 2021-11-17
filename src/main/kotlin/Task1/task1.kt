package main.kotlin.Task1

/*
* Checking identifier
* 
* Implement the function that checks whether a string is a valid identifier. A valid identifier is a
* non-empty string that starts with a letter or underscore and consists
* of only letters, digits and underscores.*/

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty() || s[0] in '0'..'9') return false
    for (ch in s)
        if (!isValidChar(ch)) return false
    return true
}
fun isValidChar(ch:Char)=ch=='_' || ch in '0'..'9' || ch in 'a'..'z'|| ch in 'A'..'Z'

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}