import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun inputColor(msg: String): Color {
    println(msg)
    val red = print("Input the value for red (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val green = print("Input the value for green (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val blue = print("Input the value for blue (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    return Color(red, green, blue)
}

fun inputDimentions(msg: String): Pair<Int, Int> {
    return print(msg).run { readln().split(",").map { it.trim().toInt() } }.run { Pair(this[0], this[1]) }
}

fun primeList(limit: Int): List<Int> {
    val primes = mutableListOf<Int>()
    for (testNumber in 2..limit) {
        var isPrime = true
        for (prime in primes) {
            if (prime * prime > testNumber) break
            if (testNumber % prime == 0) {
                isPrime = false
                break
            }
        }
        if (isPrime) {
            primes.add(testNumber)
        }
    }
    return primes
}

fun main() {
    val bgColor = inputColor("Input the background color (RGB)")
    val primeColor = inputColor("Input the color for the prime numbers (RGB)")
    val (width, height) = inputDimentions("Input the image dimensions in pixels (width, height): ")
    val primes = primeList(width * height)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (x + 1 + width * y in primes) {
                image.setRGB(x, y, primeColor.rgb)
            } else {
                image.setRGB(x, y, bgColor.rgb)
            }
        }
    }
    ImageIO.write(image, "png", File("PrimeImage.png"))
}