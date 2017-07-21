import java.awt.Color

data class Sphere (val center: Vector, val r: Double) : Objects {

    val color: Color = Color(12,150,50)
//    val albedo: Double = 0.75
//    var normalVector: Vector = Vector(0.0,0.0,0.0) todo: remove this comment
//    var ray: Vector = Vector(0.0,0.0,0.0) todo: remove this comment
//    var origin: Vector = Vector(0.0,0.0,0.0) todo: remove this comment
//    var reflect: Vector = Vector(0.0,0.0,0.0)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {
//        this.ray = ray todo: remove this comment
//        this.origin = origin todo: remove this comment
//        val vecCenter: Vector = Vector(center.x, center.y, center.z)
//        val b: Double = Math.pow(ray * (origin - center),2.0) todo: remove this comment
//        val c: Double = Math.pow((origin - center).getModulo(),2.0) todo: remove this comment
//        val d: Double = Math.pow(r,2.0) todo: remove this comment
//        if ((b - c + d) >= 0) { todo: remove this comment
//            computeCollision(b - c + d) todo: remove this comment
//            return true todo: remove this comment
//        } else { todo: remove this comment
//            return false todo: remove this comment
//        } todo: remove this comment
        return false
    }

    internal fun computeCollision(toRoot: Double) {
//        val a: Double =  (ray * (origin - center)) todo: remove this comment
//        val dist: Double = -a + Math.sqrt(toRoot)
//        val dist: Double = - a - Math.sqrt(toRoot) todo: remove this comment
//        normalVector = !((origin + (ray * dist)) - center) todo: remove this comment
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