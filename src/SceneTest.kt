import org.junit.Assert.*

class SceneTest {
    @org.junit.Test fun testSingleRay() {
        val scene: Scene = Scene()
        val cameraPos: Point = Point(0f,0f,0f)
//        val ray1: Vector = !Vector(Point(0f,50f,0f))
//        scene.trace(cameraPos, ray1, 0)
//        val ray2: Vector = !Vector(Point(0f,50f,-2f))
//        scene.trace(cameraPos, ray2, 0)
//        val ray3: Vector = !Vector(Point(0f,50f,-4f))
//        scene.trace(cameraPos, ray3, 0)
//        val ray4: Vector = !Vector(Point(0f,50f,-6f))
//        scene.trace(cameraPos, ray4, 0)
        val ray5: Vector = !Vector(Point(0f,50f,-8f))
        scene.trace(cameraPos, ray5, 0)
//        val ray6: Vector = !Vector(Point(0f,50f,-11f))
//        scene.trace(cameraPos, ray6, 0)
    }
}