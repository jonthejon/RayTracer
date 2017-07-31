data class Vector(var direction: Point) {
    constructor() : this(Point(0f, 0f, 0f))

    companion object {
        fun randomUnitVector(): Vector {
            while (true) {
                val randVec: Vector = Vector(Point.getRandomPoint(-1f, 1f))
                if (randVec.getModulo() <= 1) {
                    return !randVec
                }
            }
        }
    }

    //    addition
    operator fun plus(other: Vector): Vector {
        return Vector(this.direction + other.direction)
    }

    //    subtraction
    operator fun minus(other: Vector): Vector {
        val invOther: Vector = other * (-1f)
        return this + invOther
    }

    //    dot product
    operator fun times(other: Vector): Float {
        return (this.direction.x * other.direction.x) + (this.direction.y * other.direction.y) + (this.direction.z * other.direction.z)
    }

    //    dot product with Point
    operator fun times(point: Point): Float {
        return (this.direction.x * point.x) + (this.direction.y * point.y) + (this.direction.z * point.z)
    }

    //    scalar product
    operator fun times(num: Float): Vector {
        return Vector(this.direction * num)
    }

    //    cross product
    fun getCrossProduct(other: Vector): Vector {
        val a: Float = (this.direction.y * other.direction.z - this.direction.z * other.direction.y)
        val b: Float = (this.direction.z * other.direction.x - this.direction.x * other.direction.z)
        val c: Float = (this.direction.x * other.direction.y - this.direction.y * other.direction.x)
        return Vector(Point(a, b, c))
    }

    //    unit
    operator fun not(): Vector {
        val length = this.getModulo()
        return Vector(this.direction / length)
    }

    //    length
    fun getModulo(): Float {
        if (this.isUnitized()) {
            val power = 2.0
            return 1f
        } else {
            return this.direction.getDistance(Point(0f,0f,0f))
        }
    }

    private fun isUnitized(): Boolean {
        val power = 2.0
        val dist =  Math.sqrt(Math.pow(this.direction.x.toDouble(), power) + Math.pow(this.direction.y.toDouble(), power) + Math.pow(this.direction.z.toDouble(), power))
        return (dist - 1 < 0.0001)
    }

    fun getReflection(normal: Vector): Vector {
        return this - (normal * (this * normal) * 2f)
    }

}