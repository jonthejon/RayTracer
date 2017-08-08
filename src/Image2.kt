import java.io.Serializable

class Image2(val xRes: Int, val yRes: Int, var name: String): Serializable {

    private val serialVersionUID: Long = 1L

    val pixels = Array<Array<MyColor>>(yRes) {Array<MyColor>(xRes) {MyColor()}}
    var iterations: Int = 0;

    fun addPixels(iter: Int, toAdd: Array<Array<Pixel>>) {
        var x: Int = 0
        var y: Int = 0
        for (pixRows in toAdd) {
            for (pixel in pixRows) {
                val newSum: MyColor = pixel.getSumFinalMyColor()
                val oldMed: MyColor = pixels[y][x]
//    ((old med * old iter) + new sum) / (old iter + new iter)
                val numer: MyColor = (oldMed * iterations) + newSum
                val denom: Int = iterations + iter
                val newMed: MyColor = numer / denom
                pixels[y][x] = newMed
                x++
            }
            x=0
            y++
        }
        iterations += iter
    }

}