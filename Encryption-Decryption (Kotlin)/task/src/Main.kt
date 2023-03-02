package encryptdecrypt
import java.io.FileNotFoundException
import java.io.File
import kotlin.math.abs

val SMALL_ALPHABET: CharRange = ('a' .. 'z')
val BIG_ALPHABET: CharRange = ('A' .. 'Z')
// val ALPHABET: List<Char> = (SMALL_ALPHABET + BIG_ALPHABET)

fun encrypt(message: String, key: Int, algorithm: String = "shift"): String {
    var translated: String = ""
    if (algorithm == "unicode") {
        for (char in message) {
            var asciiIndex: Int = char.code + key
            if (asciiIndex >= 127) {
                asciiIndex -= 127
            }
            translated += asciiIndex.toChar()
        }
        return translated
    } else {
        for (char in message) {
            var index: Int = char.toInt() + key
            if (char in SMALL_ALPHABET) {
                if (index >= 'z'.toInt()) {    // 26 - 1 = 25
                    index = abs(index - SMALL_ALPHABET.count())
                }
                translated += index.toChar()
            } else if (char in BIG_ALPHABET) {
                if (index >= 'Z'.toInt()) {    // 26 - 1 = 25
                    index = abs(index - BIG_ALPHABET.count())
                }
                translated += index.toChar()
            } else {
                translated += char
            }
        }
        return translated
    }
}

fun decrypt(message: String, key: Int, algorithm: String = "shift"): String {
    var translated: String = ""
    if (algorithm == "unicode") {
        for (char in message) {
            var asciiIndex: Int = char.code - key
            if (asciiIndex < 0) {
                asciiIndex += 127
            }
            translated += asciiIndex.toChar()
        }
        return translated
    } else {
        for (char in message) {
            var index: Int = char.toInt() - key
            if (char in SMALL_ALPHABET) {
                if (index < 'a'.toInt()) {    // 26 - 1 = 25
                    index = abs(index + SMALL_ALPHABET.count())
                }
                translated += index.toChar()
            } else if (char in BIG_ALPHABET) {
                if (index < 'A'.toInt()) {    // 26 - 1 = 25
                    index = abs(index + BIG_ALPHABET.count())
                }
                translated += index.toChar()
            } else {
                translated += char
            }
        }
        return translated
    }
}

fun main(args: Array<String>) {

    var writeToFile: Boolean = false
    var readFromFile: Boolean = false

    try {

        val mode = if (args.indexOf("-mode") == -1) "enc" else args[args.indexOf("-mode") + 1]
        val key = if (args.indexOf("-key") == -1) 0 else args[args.indexOf("-key") + 1].toInt()
        val data =
            if (args.indexOf("-data") == -1 && args.indexOf("-in") == -1) {
                ""
            } else {
                args[args.indexOf("-data") + 1]
            }
        var input = File("")
        if (args.indexOf("-in") != -1) {
            input = File(args[args.indexOf("-in") + 1])
            readFromFile = true
        }
        var output: File = File("")
        if (args.indexOf("-out") != -1) {
            output = File(args[args.indexOf("-out") + 1])
            writeToFile = true
        }

        val alg = if(args.indexOf("-alg") == -1) "shift" else args[args.indexOf("-alg") + 1]

        when (mode) {
            "enc" -> {
                val message: String = if (readFromFile) {
                    encrypt(input.readText(), key, algorithm = alg)
                } else {
                    encrypt(data, key, algorithm = alg)
                }
                if (writeToFile) {
                    output.writeText(message)
                } else {
                    println(message)
                }
            }
            "dec" -> {
                val message: String = if (readFromFile) {
                    decrypt(input.readText(), key, algorithm = alg)
                } else {
                    decrypt(data, key, algorithm = alg)
                }
                if (writeToFile) {
                    output.writeText(message)
                } else {
                    println(message)
                }
            }
        }

    } catch (e: FileNotFoundException) {
        println("Error: ${e.message}")
    } catch (e: AccessDeniedException) {
        println("Error: ${e.message}")
    } catch (e: OutOfMemoryError) {
        println("Error: ${e.message}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }

}

/*
val mode = if (args.indexOf("-mode") == -1) "enc" else args[args.indexOf("-mode") + 1]
val key = if (args.indexOf("-key") == -1) 0 else args[args.indexOf("-key") + 1].toInt()
val data = if (args.indexOf("-data") == -1) "" else args[args.indexOf("-data") + 1]
*/
/*
val op = args[args.indexOf("-mode") + 1]
val message = args[args.indexOf("-data") + 1]
val key = args[args.indexOf("-key") + 1]
*/

/*
val ALPHABET: CharRange = ('a' .. 'z')
// const val LENGTH_ALPHABET: Int = 26

fun encrypt(message: String, key: Int): String {
    var translated: String = ""
    for (char in message) {
        var index: Int = char.toInt() + key
        if (char in ALPHABET) {
            if (index >= 'z'.toInt()) {    // 26 - 1 = 25
                index = abs(index - ALPHABET.count())
            }
        }
        translated += index.toChar()
    }
    return translated
}

fun decrypt(encryptedMessage: String, key: Int): String {
    var translated: String = ""
    for (char in encryptedMessage) {
        var index: Int = char.toInt() - key
        if (char in ALPHABET) {
            if (index < 'a'.toInt()) {    // 26 - 1 = 25
                index = abs(index + ALPHABET.count())
            }
        }
        translated += index.toChar()
    }
    return translated
}

fun main() {
    val operation: String = readln()
    val text: String = readln()
    val key: Int = readln().toInt()
    when(operation) {
        "enc" -> print(encrypt(text, key))
        "dec" -> print(decrypt(text, key))
        else -> print("Wrong Input!")
    }
}
 */
/*
    val message: String = readln()
    val key: Int = readln().toInt()
    val alphabet: CharRange = ('a' .. 'z')
    val listOfAlphabet: List<Char> = alphabet.toList()
    var translated: String = ""
    for (char in message) {
        if (char in alphabet) {
            var index: Int = alphabet.indexOf(char) + key
            if (index >= alphabet.count() - 1) {
                index = abs(index - alphabet.count())
            }
            translated += listOfAlphabet[index]
        } else {
            translated += char
        }
    }
    print(translated)
 */
/*
    val message: String = "we found a treasure!"
    val alphabet: CharRange = ('a' .. 'z')
    val list: List<Char> = alphabet.toList()
    //val reversedAlphabet: CharProgression = ('z' downTo 'a')
    var translated: String = ""
    for (char in message) {
        if (char in alphabet) {
            val charIndex: Int = alphabet.indexOf(char)
            translated += list[abs(charIndex - (alphabet.count() - 1))]
        } else {
            translated += char
        }
    }
    print(translated)
*/