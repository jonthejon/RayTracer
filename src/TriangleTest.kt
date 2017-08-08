import org.junit.Assert.*

class TriangleTest {

    @org.junit.Test fun testTriangleCollision () {
        val p0: Point = Point(-5f,10f,-5f)
        val p1: Point = Point(0f,10f,5f)
        val p2: Point = Point(5f,10f,-5f)
        val triangle: Triangle = Triangle(p0, p1, p2, Material())
        val ray1: Vector = !Vector(Point(0f,1f,0f))
        val ray2: Vector = !Vector(Point(4.5f,9.5f,-4.5f))
        val ray3: Vector = !Vector(Point(0f,0f,1f))
        val cameraPos: Point = Point(0f,0f,0f)
        triangle.checkCollision(cameraPos, ray1)
        triangle.checkCollision(cameraPos, ray2)
        triangle.checkCollision(cameraPos, ray3)
    }

    @org.junit.Test fun testTriangleCollision2 () {
        val p0: Point = Point(-5f,20f,-5f)
        val p1: Point = Point(0f,10f,5f)
        val p2: Point = Point(5f,10f,-5f)
        val triangle: Triangle = Triangle(p0, p1, p2, Material())
        val ray1: Vector = !Vector(Point(0f,20f,0f))
        val ray2: Vector = !Vector(Point(1f,0f,0f))
        val ray3: Vector = !Vector(Point(0f,0f,1f))
        val cameraPos: Point = Point(0f,0f,0f)
        triangle.checkCollision(cameraPos, ray1)
        triangle.checkCollision(cameraPos, ray2)
        triangle.checkCollision(cameraPos, ray3)
    }

    @org.junit.Test fun testTriangleCollision3 () {
        val p0: Point = Point(-10f,80f,-10f)
        val p1: Point = Point(10f,80f,-10f)
        val p2: Point = Point(0f,80f,10f)
        val triangle: Triangle = Triangle(p0, p1, p2, Material())
        val ray1: Vector = !Vector(Point(-9.5f,80f,-9.5f))
        val cameraPos: Point = Point(0f,0f,0f)
        triangle.checkCollision(cameraPos, ray1)
    }

    @org.junit.Test fun testTriangleCollision4 () {
        val p0: Point = Point(-10f,-80f,-10f)
        val p1: Point = Point(10f,-80f,-10f)
        val p2: Point = Point(0f,-80f,10f)
        val triangle: Triangle = Triangle(p0, p1, p2, Material())
        val ray1: Vector = !Vector(Point(0f,80f,0f))
        val cameraPos: Point = Point(0f,0f,0f)
        triangle.checkCollision(cameraPos, ray1)
    }

}