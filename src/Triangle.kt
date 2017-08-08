import java.util.*

data class Triangle(var p0: Point, var p1: Point, var p2: Point, var material: Material) : Objects() {
//    constructor() : this(Point(0f,0f,0f), Point(0f,0f,0f), Point(0f,0f,0f), Material())

    var normal = Vector()
    var L = Vector()
    var Refl: Vector = Vector()
    var ray = Vector() // Unit vector that represents the incident ray that hit this object
    var origin = Point()
    var hitPoint = Point()
    val bias: Float = 0.9999f

    override fun getHitObj(): Hit {
//        println("Normal: " + normal)
        if (Lens.getRandomFloat() > this.material.refl) {
            return Hit(true, hitPoint, L, material)
        } else {
            this.material.lambertian = 1f
            return Hit(true, hitPoint, Refl, material)
        }
    }

    override fun checkCollision(origin: Point, ray: Vector): Boolean {
        this.ray = ray // updating the ray vector that is trying to hit this object
        this.origin = origin

        val e1 = Vector(p1 - p0)
        val e2 = Vector(p2 - p0)

        val pvec = ray.getCrossProduct(e2)
        val det = e1*pvec
//        normal = !e2.getCrossProduct(e1)
        normal = !e1.getCrossProduct(e2)

        if (det < 0.001 && det > -0.001) return false

        val inv_det = 1 / det
        val tvec = Vector(origin - p0)
        val u = (tvec*pvec) * inv_det

        if (u < 0 || u > 1) return false

        val qvec = tvec.getCrossProduct(e1)
        val v = (ray*qvec) * inv_det

        if (v < 0 || u+v > 1) return false

        val dist = (e2*qvec) * inv_det

        if (dist <= 0) return false

        hitPoint= (ray * dist * bias).direction + origin
//        val (x,y,z) = hitPoint
//        println("X: " + x)
//        println("Y: " + y)
//        println("Z: " + z)
//        println("Normal: " + normal)
        computeL()
        return true
    }

    private fun computeL() {
        this.Refl = !ray.getReflection(normal)
        var exit: Boolean = false
        while (!exit) {
            val possibleL: Vector = Vector.randomUnitVector()
            val possibleLambertian = normal * possibleL
            if (possibleLambertian > 0) {
                exit = true
                this.material.lambertian = possibleLambertian
                this.L = possibleL
            }
        }
    }
}