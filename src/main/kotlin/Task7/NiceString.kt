package main.kotlin.Task7

fun String.isNice(): Boolean {

    fun hasBadSubstrings(): Boolean {
        if (!this.contains("bu") &&
            !this.contains("ba") &&
            !this.contains("be")) return true
        return false
    }

    fun hasThreeVowels(): Boolean {

        return count{
            it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u'
        } >=3

//        if (this.filter
//            { c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' }
//                .count() >= 3) return true
//        return false
    }

    fun hasDoubleLetter(): Boolean {
        this.zipWithNext { a, b -> if (a == b) return true }
        return false
    }

    if (hasBadSubstrings() && hasThreeVowels() ||
        hasBadSubstrings() && hasDoubleLetter() ||
        hasThreeVowels() && hasDoubleLetter()) return true
    return false
}