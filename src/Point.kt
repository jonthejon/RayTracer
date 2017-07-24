import java.util.*

data class Point (var x: Double, var y: Double, var z: Double) {
    constructor(): this(0.0,0.0,0.0)

    companion object {
        fun getRandomPoint(low: Double, high: Double): Point {
            if (high <= low) throw IllegalArgumentException()
            val randX: Double = Random().nextDouble()
            val randY: Double = Random().nextDouble()
            val randZ: Double = Random().nextDouble()
            val range: Double = high - low
            return Point(((randX * range) + low), ((randY * range) + low), ((randZ * range) + low))
        }
    }

    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    operator fun minus(other: Point): Point {
        return Point(this.x - other.x, this.y - other.y, this.z - other.z)
    }

    operator fun times(num: Double): Point {
        return Point(this.x * num, this.y * num, this.z * num)
    }

    operator fun div(num: Double): Point {
//        println("-------")
//        println("number: " + num)
//        println("new x: " + (this.x / num))
//        println("new y: " + (this.y / num))
//        println("new z: " + (this.z / num))
//        println("-------")
        val testPoint = Point((this.x / num), (this.y / num), (this.z / num))
//        println("-------")
//        println("number: " + num)
//        println("new x: " + (testPoint.x))
//        println("new y: " + (testPoint.y))
//        println("new z: " + (testPoint.z))
//        println("-------")
        return Point((this.x / num), (this.y / num), (this.z / num))
    }

    fun getDistance(other: Point): Double {
        val power = 2.0
        val distX: Double = Math.pow((other.x - this.x), power)
        val distY: Double = Math.pow((other.y - this.y), power)
        val distZ: Double = Math.pow((other.z - this.z), power)
        return Math.sqrt(distX + distY + distZ)
    }
}