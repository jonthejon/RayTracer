//data class Vector(val x: Double, val y: Double, val z: Double) {
data class Vector(var origin: Point, var direction: Point) {
    constructor() : this(Point(0.0, 0.0, 0.0), Point(0.0, 0.0, 0.0))
    constructor(direction: Point) : this(Point(0.0, 0.0, 0.0), direction)

//    var isUnit: Boolean = false

    //    addition
    operator fun plus(other: Vector): Vector {
        return Vector(this.origin, this.direction + other.direction)
    }

    //    subtraction
    operator fun minus(other: Vector): Vector {
        val invOther: Vector = other * (-1.0)
        return this + invOther
    }

    //    dot product
    operator fun times(other: Vector): Double {
        val newThis = shiftToOrigin(this)
        val newOther = shiftToOrigin(other)
        return (newThis.direction.x * newOther.direction.x) + (newThis.direction.y * newOther.direction.y) + (newThis.direction.z * newOther.direction.z)
    }

    //    dot product with Point
    operator fun times(point: Point): Double {
        val newThis = shiftToOrigin(this)
        return (newThis.direction.x * point.x) + (newThis.direction.y * point.y) + (newThis.direction.z * point.z)
    }

    //    scalar product
    operator fun times(num: Double): Vector {
        return Vector(this.origin, this.direction * num)
    }

    //    cross product
    fun getCrossProduct(other: Vector): Vector {
        val a: Double = (this.direction.y * other.direction.z - this.direction.z * other.direction.y)
        val b: Double = (this.direction.z * other.direction.x - this.direction.x * other.direction.z)
        val c: Double = (this.direction.x * other.direction.y - this.direction.y * other.direction.x)
        return Vector(this.origin, Point(a, b, c))
    }

    //    unit
    operator fun not(): Vector {
        val newThis = shiftToOrigin(this)
        val length = newThis.getModulo()
//        this.isUnit = true
//        println("length: " + length)
        return Vector(this.origin, newThis.direction / length)
    }

    //    length
    fun getModulo(): Double {
        if (this.isUnitized()) {
            val power = 2.0
            return Math.sqrt(Math.pow(this.direction.x, power) + Math.pow(this.direction.y, power) + Math.pow(this.direction.z, power))
        } else {
//            val newThis = shiftToOrigin(this)
            return this.direction.getDistance(this.origin)
        }
//        val newThis = shiftToOrigin(this)
//        val power = 2.0
//        return Math.sqrt(Math.pow(newThis.direction.x, power) + Math.pow(newThis.direction.y, power) + Math.pow(newThis.direction.z, power))
    }

    private fun shiftToOrigin(vec: Vector): Vector {
//        println("Origin x: " + (vec.origin - vec.origin).x)
//        println("Origin y: " + (vec.origin - vec.origin).y)
//        println("Origin z: " + (vec.origin - vec.origin).z)
//        println("Direction x: " + (vec.direction - vec.origin).x)
//        println("Direction y: " + (vec.direction - vec.origin).y)
//        println("Direction z: " + (vec.direction - vec.origin).z)
        return Vector(vec.origin - vec.origin, vec.direction - vec.origin)
    }

    private fun isUnitized(): Boolean {
        val power = 2.0
        val dist =  Math.sqrt(Math.pow(this.direction.x, power) + Math.pow(this.direction.y, power) + Math.pow(this.direction.z, power))
        return (dist - 1 < 0.0001)
    }

}