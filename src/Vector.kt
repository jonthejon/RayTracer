data class Vector(var direction: Point) {
    constructor() : this(Point(0.0, 0.0, 0.0))

    companion object {
        fun randomUnitVector(): Vector {
            while (true) {
                val randVec: Vector = Vector(Point.getRandomPoint(-1.0, 1.0))
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
        val invOther: Vector = other * (-1.0)
        return this + invOther
    }

    //    dot product
    operator fun times(other: Vector): Double {
        return (this.direction.x * other.direction.x) + (this.direction.y * other.direction.y) + (this.direction.z * other.direction.z)
    }

    //    dot product with Point
    operator fun times(point: Point): Double {
        return (this.direction.x * point.x) + (this.direction.y * point.y) + (this.direction.z * point.z)
    }

    //    scalar product
    operator fun times(num: Double): Vector {
        return Vector(this.direction * num)
    }

    //    cross product
    fun getCrossProduct(other: Vector): Vector {
        val a: Double = (this.direction.y * other.direction.z - other.direction.y * this.direction.z)
        val b: Double = (other.direction.x * this.direction.z - this.direction.x * other.direction.z)
        val c: Double = (this.direction.x * other.direction.y - other.direction.x * this.direction.y)
//        val a: Float = (this.direction.y * other.direction.zVector - this.direction.zVector * other.direction.y)
//        val b: Float = (this.direction.zVector * other.direction.x - this.direction.x * other.direction.zVector)
//        val c: Float = (this.direction.x * other.direction.y - this.direction.y * other.direction.x)
        return Vector(Point(a, b, c))
    }

    //    unit
    operator fun not(): Vector {
        val length = this.getModulo()
        return Vector(this.direction / length)
    }

    //    length
    fun getModulo(): Double {
//        if (this.isUnitized()) {
//            println("said is unitized!")
//            val power = 2.0
//            return 1.0
//        } else {
//            println("said is NOT unitized!")
            return this.direction.getDistance(Point(0.0,0.0,0.0))
//        }
    }

    private fun isUnitized(): Boolean {
        val power = 2.0
        val dist =  Math.sqrt(Math.pow(this.direction.x.toDouble(), power) + Math.pow(this.direction.y.toDouble(), power) + Math.pow(this.direction.z.toDouble(), power))
//        println(dist)
        return (dist - 1 < 0.0001 && dist - 1 > -0.0001)
    }

    fun getReflection(normal: Vector): Vector {
        return this - (normal * (this * normal) * 2.0)
    }

}