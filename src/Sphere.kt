import kotlin.concurrent.fixedRateTimer

data class Sphere(val center: Point, val r: Double, val material: Material, val objId: Int) : Objects() {

//    override val material: Material = Material(Point(0.96, 0.30,0.20))
    override val id: Int = objId
    var normal = Vector()
    var L = Vector()
    var lambertian: Double = 0.0
    var ray = Vector() // Unit vector that represents the incident ray that hit this object
    var hitPoint = Point()
    val bias: Double = 0.9999
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun getHit(ray: Vector): Hit {
        return Hit(true, hitPoint, L, lambertian, material, this.id)
//        this.ray = ray
//        this.origin = Vector(ray.origin)
//        val b: Double = Math.pow(ray * (origin - centerVec), 2.0)
//        val c: Double = Math.pow((origin - centerVec).getModulo(), 2.0)
//        val d: Double = Math.pow(r, 2.0)
//        if ((b - c + d) >= 0) {
//            computeCollision(b - c + d)
//            print("Hit BALL -> ")
//            return Hit(true, hitVec, normal, material, 0)
//        } else {
//            return Hit()
//        }
    }

    override fun checkColision(ray: Vector): Boolean {
        this.ray = ray // updating the ray vector that is trying to hit this object
        val b: Double = Math.pow(ray * (ray.origin - center), 2.0)
        val c: Double = Math.pow(ray.origin.getDistance(center), 2.0)
        val d: Double = Math.pow(r, 2.0)
        if ((b - c + d) >= 0) {
            return computeCollision(b - c + d)
        } else {
            return false
        }
    }

    private fun computeCollision(toRoot: Double): Boolean {
        val a: Double = (ray * (ray.origin - center))
        val distPlus: Double = -a + Math.sqrt(toRoot)
        val distNeg: Double = -a - Math.sqrt(toRoot)
        if (distPlus < 0 || distNeg < 0) return false
        if (distNeg <= distPlus) {
            hitPoint = (ray * distNeg * bias).direction
        } else {
            hitPoint = (ray * distPlus * bias).direction
        }
        this.computeNomal()
        this.computeL()
        return true
//        normal = !((origin + hitVec) - centerVec)
//        println("normal Origin X: " + normal.origin.x)
//        println("normal Origin Y: " + normal.origin.y)
//        println("normal Origin Z: " + normal.origin.z)
//        normal = !((origin + (ray * dist)) - centerVec)
//        ref = ray - 2(ray*normal)*normal
//        reflect = ray - ((normal * (ray * normal)) * 2.0)
    }

    private fun computeNomal() {
        normal.origin = center
        normal.direction = hitPoint
        normal = !normal
        normal.origin = hitPoint
    }

    private fun computeL() {
        val zeroNormal = Vector(normal.direction)
        var exit: Boolean = false
        while (!exit) {
            val possibleL: Vector = Vector.randomUnitVector()
            val possibleLambertian = zeroNormal * possibleL
//            println("normal origin X: " + normal.origin.x)
//            println("normal origin Y: " + normal.origin.y)
//            println("normal origin Z: " + normal.origin.z)
//            println("normal direct X: " + normal.direction.x)
//            println("normal direct Y: " + normal.direction.y)
//            println("normal direct Z: " + normal.direction.z)
//            println("---")
//            println("zeroNormal origin X: " + zeroNormal.origin.x)
//            println("zeroNormal origin Y: " + zeroNormal.origin.y)
//            println("zeroNormal origin Z: " + zeroNormal.origin.z)
//            println("zeroNormal direct X: " + zeroNormal.direction.x)
//            println("zeroNormal direct Y: " + zeroNormal.direction.y)
//            println("zeroNormal direct Z: " + zeroNormal.direction.z)
//            println("---")
//            println("L origin X: " + possibleL.origin.x)
//            println("L origin Y: " + possibleL.origin.y)
//            println("L origin Z: " + possibleL.origin.z)
//            println("L direct X: " + possibleL.direction.x)
//            println("L direct Y: " + possibleL.direction.y)
//            println("L direct Z: " + possibleL.direction.z)
//            println("---")
//            println("Lambert: " + possibleLambertian)
            if (possibleLambertian > 0) {
                exit = true
                this.lambertian = possibleLambertian
                this.L = possibleL
            }
        }
    }
}