import java.awt.Color

class Sky : Objects {

    val albedo: Color = Color(97,215,250)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {
        val testVec = ray + ray
        return false // todo: remove this statement
//        return (testVec.z > ray.z) todo: remove this comment
    }

    override fun getColor(): Int {
        return this.albedo.rgb
    }
/*
    override fun getReflectVec(): Vector {
    }

    override fun getNormalVec(): Vector {
    }*/
}