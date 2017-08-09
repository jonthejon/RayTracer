class Floor (val point: Point, val normalVec: Vector, val mat: Material) : Objects() {

    val material: Material = mat
    var L = Vector()
    var Refl: Vector = Vector()
    val normal: Vector = !normalVec
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
//            println("FLOOR HitPoint: " + this.hitPoint)
//            println("FLOOR Reflection: " + this.Refl)
//            println("---")
            return Hit(true, hitPoint, Refl, material)
        }
    }

    override fun checkCollision(origin: Point, ray: Vector): Boolean {
        this.ray = ray
        this.origin = origin
        val check = ray * normal
        if (check == 0.0) return false
        return computeCollision(check)
    }

    private fun computeCollision(denom: Double): Boolean {
        val numer = normal * (point - origin)
        val dist = numer / denom
        if (dist <= 0) return false // THIS CAN BE DANGEROUS! MAKE TESTS FOR THIS LATER.
        hitPoint = (ray * dist * bias).direction + this.origin
        this.computeL()
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