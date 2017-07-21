import java.awt.Color

class Sky : Objects {

    val albedo: Color = Color(97,215,250)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {
        val testVec = ray + ray
        return (testVec.z > ray.z)
    }

    override fun getColor(): Int {
        return this.albedo.rgb
    }
/*
    override fun getReflectVec(): Vector {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNormalVec(): Vector {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/
}