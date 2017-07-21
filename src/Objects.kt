interface Objects {
    fun checkColision(ray: Vector, origin: Vector): Boolean
    open fun getColor(): Int
//    open fun getReflectVec(): Vector
//    open fun getNormalVec(): Vector
}