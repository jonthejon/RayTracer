class Sphere(val center: Point, val r: Double, val material: Material) : Objects() {

    var normal = Vector()
    var L = Vector()
    var Refl: Vector = Vector()
    var ray = Vector() // Unit vector that represents the incident ray that hit this object
    var origin = Point()
    var hitPoint = Point()
    val bias: Double = 0.9999

    override fun getHitObj(): Hit {
        if (Lens.getRandomDouble() > this.material.refl) {
            return Hit(true, hitPoint, L, material)
        } else {
            this.material.lambertian = 1.0
//            println("---")
//            println("SPHERE HitPoint: " + this.hitPoint)
//            println("SPHERE Reflection: " + this.Refl)
//            println("---")
            return Hit(true, hitPoint, Refl, material)
        }
    }

    override fun checkCollision(origin: Point, ray: Vector): Boolean {
        this.ray = ray // updating the ray vector that is trying to hit this object
        this.origin = origin
        val b: Double = Math.pow((ray * (origin - center)).toDouble(), 2.0)
        val c: Double = Math.pow(origin.getDistance(center).toDouble(), 2.0)
        val d: Double = Math.pow(r, 2.0)
        if ((b - c + d) >= 0) {
            return computeCollision(b - c + d)
        } else {
            return false
        }
    }

    private fun computeCollision(toRoot: Double): Boolean {
        val a: Double = (ray * (origin - center)).toDouble()
        val distPlus: Double = (-a + Math.sqrt(toRoot))
        val distNeg: Double = (-a - Math.sqrt(toRoot))
        if (distPlus < 0 || distNeg < 0) return false
        if (distNeg <= distPlus) {
            hitPoint = (ray * distNeg * bias).direction + this.origin
        } else {
            hitPoint = (ray * distPlus * bias).direction + this.origin
        }
        this.computeNomal()
        this.computeL()
        return true
    }

    private fun computeNomal() {
        normal = !Vector(hitPoint - center)
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