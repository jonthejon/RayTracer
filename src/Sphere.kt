import java.awt.Color

data class Sphere (val center: Point, val r: Double) : Objects() {

    override val material: Material = Material(0.75)
        var normalVector: Vector = Vector(Point(0.0,0.0,0.0))
    var ray: Vector = Vector(Point(0.0,0.0,0.0))
    var origin: Vector = Vector(Point(0.0,0.0,0.0))
    var centerVec: Vector = Vector(this.center)
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun checkColision(ray: Vector): Boolean {
                this.ray = ray
        this.origin = origin
                val b: Double = Math.pow(ray * (origin - centerVec),2.0)
        val c: Double = Math.pow((origin - centerVec).getModulo(),2.0)
        val d: Double = Math.pow(r,2.0)
        if ((b - c + d) >= 0) {
            computeCollision(b - c + d)
            return true
        } else {
            return false
        }
    }

    internal fun computeCollision(toRoot: Double) {
        val a: Double =  (ray * (origin - centerVec))
//        val dist: Double = -a + Math.sqrt(toRoot)
                val dist: Double = - a - Math.sqrt(toRoot)
        normalVector = !((origin + (ray * dist)) - centerVec)
//        ref = ray - 2(ray*normal)*normal
//        reflect = ray - ((normalVector * (ray * normalVector)) * 2.0)
    }

    override fun getColor(): Int {
        return Color(255,150,50).rgb
//        return this.getAlb().toInt()
    }

    override fun isLightSource(): Boolean {
        return false
    }
}