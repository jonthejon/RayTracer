import java.awt.Color

class Sky : Objects {

    val albedo: Color = Color(97,215,250)

//    override fun checkColision(ray: Vector, origin: Vector): Boolean {
    override fun checkColision(ray: Vector): Boolean {
        val testVec = ray + ray
        return (testVec.direction.z > ray.direction.z)
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