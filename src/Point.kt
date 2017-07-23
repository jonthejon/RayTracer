data class Point (val x: Double, val y: Double, val z: Double) {
    constructor(): this(0.0,0.0,0.0)

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