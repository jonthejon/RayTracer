import java.awt.Color
import java.io.Serializable

data class Pixel(var numRays: Int = 0) : Serializable {

    private val serialVersionUID: Long = 1L

    val colorArr = ArrayList<MyColor>()

    operator fun unaryPlus() {
        this.numRays++
    }

    fun addColor(color: MyColor) {
        this.colorArr.add(color)
    }

    fun addColor(colorArr: ArrayList<MyColor>) {
        for (color in colorArr) {
            this.colorArr.add(color)
        }
    }

    fun getFinalColor(): Int {
        val numColors: Int = colorArr.size
        var reds: Double = 0.0
        var greens: Double = 0.0
        var blues: Double = 0.0
        for (color in colorArr) {
            reds += color.r
            greens += color.g
            blues += color.b
        }
        reds = reds/numColors
        greens = greens/numColors
        blues = blues/numColors
        if (reds > 1f) reds = 1.0
        if (greens > 1f) greens = 1.0
        if (blues > 1f) blues = 1.0
        return Color(reds.toFloat(), greens.toFloat(), blues.toFloat()).rgb
    }

    fun getFinalMyColor(): MyColor {
        val numColors: Int = colorArr.size
        var reds: Double = 0.0
        var greens: Double = 0.0
        var blues: Double = 0.0
        for (color in colorArr) {
            reds += color.r
            greens += color.g
            blues += color.b
        }
        reds = reds/numColors
        greens = greens/numColors
        blues = blues/numColors
//        if (reds > 1f) reds = 1f
//        if (greens > 1f) greens = 1f
//        if (blues > 1f) blues = 1f
        return MyColor(reds, greens, blues)
    }

    fun getSumFinalMyColor(): MyColor {
//        val numColors: Int = colorArr.size
        var reds: Double = 0.0
        var greens: Double = 0.0
        var blues: Double = 0.0
        for (color in colorArr) {
            reds += color.r
            greens += color.g
            blues += color.b
        }
        return MyColor(reds, greens, blues)
    }
}