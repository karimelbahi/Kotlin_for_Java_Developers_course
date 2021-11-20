package main.kotlin.Task7

fun String.isNice(): Boolean {

/*    fun hasBadSubstrings(): Boolean {
        if (!this.contains("bu") &&
            !this.contains("ba") &&
            !this.contains("be")) return true
        return false
    }*/

/*    fun hasBadSubstrings()= setOf("bu","ba","be").none { !this.contains(it) }*/

    val hasBadSubstrings = setOf("bu", "ba", "be").all { !this.contains(it) }

/*    fun hasThreeVowels(): Boolean {

        return count{
            it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u'
        } >=3

//        if (this.filter
//            { c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' }
//                .count() >= 3) return true
//        return false
//    }*/

    /*val hasThreeVowels=count {it in setOf('a','e','i','o','u')  }>=3*/

    val hasThreeVowels = count { it in "aeiou" } >= 3

/*    fun hasDoubleLetter(): Boolean {
        this.zipWithNext { a, b -> if (a == b) return true }
        return false
    }*/

    /*val hasDoubleLetter = (0 until lastIndex).any { this[it] == this[it + 1] }*/
   /* val hasDoubleLetter = this.windowed(2).any { it[0]==it[1] }*/   // sif str= abcdef window returns ab bc cd de ef ...etc
    val hasDoubleLetter = this.zipWithNext().any{ it.first==it.second }


/*    if (hasBadSubstrings && hasThreeVowels ||
        hasBadSubstrings && hasDoubleLetter ||
        hasThreeVowels && hasDoubleLetter
    ) return true
    return false*/

    /*return listOf(hasBadSubstrings,hasDoubleLetter,hasThreeVowels).filter { it==true }.size>=2*/

    return listOf(hasBadSubstrings,hasDoubleLetter,hasThreeVowels).count { it==true }>=2
}