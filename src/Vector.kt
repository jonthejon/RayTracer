
data class Vector(val x: Double, val y: Double, val z: Double) {

    operator fun plus(other: Vector): Vector {
        return Vector(x+other.x, y+other.y,z+other.z)
    }

    operator fun minus(other: Vector): Vector {
        return Vector(x-other.x, y-other.y,z-other.z)
    }

    operator fun times(other: Vector): Double {
        return (x*other.x) + (y*other.y) + (z*other.z)
    }

    operator fun times(num: Double): Vector {
        return Vector(x*num,y*num,z*num)
    }

    operator fun not(): Vector {
        val length = this.getLength()
        return Vector(x/length,y/length,z/length)
    }

    fun getLength(): Double {
        return Math.sqrt((x*x) + (y*y) + (z*z))
    }
}