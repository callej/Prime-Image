import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO

val primes = File(".\\src\\main\\kotlin\\primeList.txt").readText().split(",").map { it.trim().toInt() }.toSet()

fun inputColor(msg: String): Color {
    println(msg)
    val red = print("Input the value for red (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val green = print("Input the value for green (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val blue = print("Input the value for blue (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    return Color(red, green, blue)
}

fun inputDimentions(msg: String): Pair<Int, Int> {
    while (true) {
        try {
            return print(msg).run { readln().split(",").map { it.trim().toInt() } }.run { Pair(this[0], this[1]) }
        } catch (e: Exception) {
            println("\nIncorrect input!\nError Message:")
            println("${e.message}\n")
        }
    }
}

fun main() {
    val bgColor = inputColor("Input the background color (RGB)")
    val primeColor = inputColor("Input the color for the prime numbers (RGB)")
    val width: Int
    val height: Int
    while (true) {
        val (w, h) = inputDimentions("Input the image dimensions in pixels (width, height): ")
        if (w * h > primes.last()) {
            println("Too big image!\n width * height cannot be bigger than ${primes.last()}")
            println("You have provided: width = $w * height = $h = ${w * h}\n")
        } else {
            width = w
            height = h
            break
        }
    }
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