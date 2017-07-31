import java.util.*

data class Point (var x: Float, var y: Float, var z: Float) {
    constructor(): this(0f,0f,0f)

    companion object {
        fun getRandomPoint(low: Float, high: Float): Point {
            if (high <= low) throw IllegalArgumentException()
            val randX: Float = Random().nextFloat()
            val randY: Float = Random().nextFloat()
            val randZ: Float = Random().nextFloat()
            val range: Float = high - low
            return Point(((randX * range) + low), ((randY * range) + low), ((randZ * range) + low))
        }
    }

    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    operator fun minus(other: Point): Point {
        return Point(this.x - other.x, this.y - other.y, this.z - other.z)
    }

    operator fun times(num: Float): Point {
        return Point(this.x * num, this.y * num, this.z * num)
    }

    operator fun div(num: Float): Point {
        return Point((this.x / num), (this.y / num), (this.z / num))
    }

    fun getDistance(other: Point): Float {
        val power = 2.0
        val distX: Double = Math.pow((other.x.toDouble() - this.x), power)
        val distY: Double = Math.pow((other.y.toDouble() - this.y), power)
        val distZ: Double = Math.pow((other.z.toDouble() - this.z), power)
        return Math.sqrt(distX + distY + distZ).toFloat()
    }
}