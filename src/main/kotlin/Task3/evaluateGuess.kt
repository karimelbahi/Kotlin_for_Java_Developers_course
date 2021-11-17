package main.kotlin.Task3;

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var positions = 0
    var letters = 0
    val sb = StringBuilder()
    val sb2 = StringBuilder()
    sb.append(secret)
    sb2.append(guess)
    for (index in 0..3) {
        if (secret[index] == guess[index]) {
            positions++
            sb[index] = '1'
            sb2[index] = '1'
        }
    }

    for (ch in sb) {
        if (ch != '1') {
            for (ch2 in sb2) {
                if (ch2 == ch) {
                    letters++
                    sb2[sb2.indexOf(ch2)]='1'
                    break
                }
            }
        }
    }
    return Evaluation(positions,letters)
}
