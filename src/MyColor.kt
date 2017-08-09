import java.awt.Color
import java.io.Serializable

data class MyColor (var r: Double, var g: Double, var b: Double) : Serializable {
    constructor() : this(0.0,0.0,0.0)

    private val serialVersionUID: Long = 1L

    operator fun times(albedo: Point): MyColor {
        return MyColor(this.r * albedo.x, this.g * albedo.y, this.b * albedo.z)
    }

    operator fun times(flux: Double): MyColor {
        return MyColor(this.r * flux, this.g * flux, this.b * flux)
    }

    operator fun times(num: Int): MyColor {
        return MyColor(this.r * num, this.g * num, this.b * num)
    }

    operator fun plus(other: MyColor): MyColor {
        return MyColor(this.r + other.r, this.g + other.g, this.b + other.b)
    }

    operator fun div(num: Int): MyColor {
        return MyColor(this.r / num, this.g / num, this.b / num)
    }

    fun getJavaColor(): Int {
        if (this.r > 1f) this.r = 1.0
        if (this.g > 1f) this.g = 1.0
        if (this.b > 1f) this.b = 1.0
//        println(this.r)
//        println(this.g)
//        println(this.b)
        return Color(this.r.toFloat(), this.g.toFloat(), this.b.toFloat()).rgb
    }

    fun isFull(): Boolean {
        return (this.r != -1.0 && this.g != -1.0 && this.b != -1.0)
    }

    fun add(color: Double) {
        if (this.r == -1.0) {
            this.r = color
            return
        }
        if (this.g == -1.0) {
            this.g = color
            return
        }
        if (this.b == -1.0) {
            this.b = color
            return
        }
    }
}