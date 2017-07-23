import java.awt.Color

data class Sphere(val center: Point, val r: Double) : Objects() {

    override val material: Material = Material(Point(0.8, 0.8,0.8))
    override val id: Int = 1
    var normalVector = Vector()
    var ray = Vector()
    var origin = Vector()
    var centerVec: Vector = Vector(this.center)
    var hitVec = Vector()
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun getHit(ray: Vector): Hit {
        return Hit(true, hitVec, normalVector, material, this.id)
//        this.ray = ray
//        this.origin = Vector(ray.origin)
//        val b: Double = Math.pow(ray * (origin - centerVec), 2.0)
//        val c: Double = Math.pow((origin - centerVec).getModulo(), 2.0)
//        val d: Double = Math.pow(r, 2.0)
//        if ((b - c + d) >= 0) {
//            computeCollision(b - c + d)
//            print("Hit BALL -> ")
//            return Hit(true, hitVec, normalVector, material, 0)
//        } else {
//            return Hit()
//        }
    }

    override fun checkColision(ray: Vector): Boolean {
        this.ray = ray // updating the ray vector that is trying to hit this object
        this.origin = Vector(ray.origin) // creating the origin vector using the origin of the ray
        val b: Double = Math.pow(ray * (origin - centerVec), 2.0)
        val c: Double = Math.pow((origin - centerVec).getModulo(), 2.0)
        val d: Double = Math.pow(r, 2.0)
        if ((b - c + d) >= 0) {
            computeCollision(b - c + d)
            return true
        } else {
            return false
        }
    }

    internal fun computeCollision(toRoot: Double) {
        val a: Double = (ray * (origin - centerVec))
//        val distPlus: Double = -a + Math.sqrt(toRoot)
        val dist: Double = -a - Math.sqrt(toRoot)
        hitVec = ray * dist
        normalVector = !((origin + hitVec) - centerVec)
//        println("normal Origin X: " + normalVector.origin.x)
//        println("normal Origin Y: " + normalVector.origin.y)
//        println("normal Origin Z: " + normalVector.origin.z)
//        normalVector = !((origin + (ray * dist)) - centerVec)
//        ref = ray - 2(ray*normal)*normal
//        reflect = ray - ((normalVector * (ray * normalVector)) * 2.0)
    }
}