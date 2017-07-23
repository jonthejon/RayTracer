import java.awt.Color

data class Pixel(var numRays: Int = 0) {

    val colorArr = ArrayList<Color>()

    operator fun unaryPlus() {
        this.numRays++
    }

    fun addColor(color: Color) {
        this.colorArr.add(color)
    }

    fun getFinalColor(): Int {
        val numColors: Int = colorArr.size
        var reds: Int = 0
        var greens: Int = 0
        var blues: Int = 0
        for (color in colorArr) {
            reds += color.red
            greens += color.green
            blues += color.blue
        }
        return Color(reds/numColors, greens/numColors, blues/numColors).rgb
    }
}
//data class Pixel(var red: Int = 0, var green: Int = 0, var blue: Int = 0, var numRays: Int = 0)