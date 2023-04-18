data class Color(var r: Int, var g: Int, var b: Int)

fun inputColor(msg: String): Color {
    println(msg)
    val red = print("Input the value for red (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val green = print("Input the value for green (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    val blue = print("Input the value for blue (0 - 255): ").run { readln().toInt().coerceIn(0..255) }
    return Color(red, green, blue)
}

fun main() {
    val bgColor = inputColor("Input the background color (RGB)")
    val primeColor = inputColor("Input the color for the prime numbers (RGB)")
    println(bgColor)
    println(primeColor)
}