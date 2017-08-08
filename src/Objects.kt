abstract class Objects {

    abstract fun getHitObj(): Hit
    abstract fun checkCollision(origin: Point, ray: Vector): Boolean
}