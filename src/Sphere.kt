import kotlin.concurrent.fixedRateTimer

data class Sphere(val center: Point, val r: Double) : Objects() {

    override val material: Material = Material(Point(0.8, 0.8,0.8))
    override val id: Int = 1
    var normal = Vector()
    var ray = Vector() // Unit vector that represents the incident ray that hit this object
    var hitPoint = Point()
    val bias: Double = 0.9999
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun getHit(ray: Vector): Hit {
        return Hit(true, hitPoint, normal, 0.0, material, this.id)
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

    internal fun computeCollision(toRoot: Double): Boolean {
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

        return true
//        normal = !((origin + hitVec) - centerVec)
//        println("normal Origin X: " + normal.origin.x)
//        println("normal Origin Y: " + normal.origin.y)
//        println("normal Origin Z: " + normal.origin.z)
//        normal = !((origin + (ray * dist)) - centerVec)
//        ref = ray - 2(ray*normal)*normal
//        reflect = ray - ((normal * (ray * normal)) * 2.0)
    }

    internal fun computeNomal() {
        normal.origin = center
        normal.direction = hitPoint
        normal = !normal
        normal.origin = hitPoint
    }
}