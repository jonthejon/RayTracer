import java.awt.Color

data class Sphere (val center: Vector, val r: Double) : Objects {

    val color: Color = Color(12,150,50)
//    val albedo: Double = 0.75
    var normalVector: Vector = Vector(0.0,0.0,0.0)
    var ray: Vector = Vector(0.0,0.0,0.0)
    var origin: Vector = Vector(0.0,0.0,0.0)
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {
        this.ray = ray
        this.origin = origin
//        val vecCenter: Vector = Vector(center.x, center.y, center.z)
        val b: Double = Math.pow(ray * (origin - center),2.0)
        val c: Double = Math.pow((origin - center).getLength(),2.0)
        val d: Double = Math.pow(r,2.0)
        if ((b - c + d) >= 0) {
            computeCollision(b - c + d)
            return true
        } else {
            return false
        }
    }

    internal fun computeCollision(toRoot: Double) {
        val a: Double =  (ray * (origin - center))
//        val dist: Double = -a + Math.sqrt(toRoot)
        val dist: Double = - a - Math.sqrt(toRoot)
        normalVector = !((origin + (ray * dist)) - center)
//        ref = ray - 2(ray*normal)*normal
//        reflect = ray - ((normalVector * (ray * normalVector)) * 2.0)
    }

    override fun getColor(): Int {
        return this.color.rgb
    }
/*
    override fun getReflectVec(): Vector {
        return reflect
    }

    override fun getNormalVec(): Vector {
        return normalVector
    }*/
}