package mastermind

import main.kotlin.Task3.Evaluation

fun main() {

    val result = Evaluation(rightPosition = 1, wrongPosition = 1)
    println(evaluateGuessTest("BCDF", "ACEB").equals(result))
    println(evaluateGuessTest("AAAF", "ABCA").equals(result))
    println(evaluateGuessTest("ABCA", "AAAF").equals(result))

}

fun evaluateGuessTest(secret: String, guess: String): Evaluation {

/*    var positions = 0
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
    return Evaluation(positions,letters)*/

    val rightPositions = secret.zip(guess).count{result-> result.first==result.second }

    val commonLetters = "ABCDEF".sumOf { ch ->
//        println(ch)
//        Math.min(secret.count { it == ch }, guess.count { it == ch })
        secret.count { it == ch }.coerceAtMost(guess.count { it == ch })

    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}