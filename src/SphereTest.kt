import org.junit.Assert.*

class SphereTest {

    @org.junit.Test fun testSphereCollision () {
        val spherePos: Sphere = Sphere(Point(0.0f, 50.0f, 0.0f), 20f, Material())
        val rayY: Vector = !Vector(Point(0.0f,1.0f,0.0f))
        val rayX: Vector = !Vector(Point(1.0f,0.0f,0.0f))
        val rayZ: Vector = !Vector(Point(0.0f,0.0f,1.0f))
        val cameraPos: Point = Point(0f,0f,0f)
        val testCollPosY = spherePos.checkCollision(cameraPos, rayY)
        val testCollPosX = spherePos.checkCollision(cameraPos, rayX)
        val testCollPosZ = spherePos.checkCollision(cameraPos, rayZ)
        assertTrue(testCollPosY)
        assertTrue(!testCollPosX && !testCollPosZ)

        val sphereNeg: Sphere = Sphere(Point(0.0f, -50.0f, 0.0f), 20f, Material())
        val testCollNegY = sphereNeg.checkCollision(cameraPos, rayY)
        val testCollNegX = sphereNeg.checkCollision(cameraPos, rayX)
        val testCollNegZ = sphereNeg.checkCollision(cameraPos, rayZ)
        assertTrue(!testCollNegX && !testCollNegZ && !testCollNegY)
    }

    @org.junit.Test fun testCollisionPoint() {
        val spherePos: Sphere = Sphere(Point(0.0f, 50.0f, 0.0f), 20f, Material())
        val rayY: Vector = !Vector(Point(0.0f,1.0f,0.0f))
        val cameraPos: Point = Point(0f,0f,0f)
        spherePos.checkCollision(cameraPos, rayY)
        val hitDist = spherePos.hitPoint.getDistance(cameraPos)
        assertTrue(hitDist - 30.0 < 0.01)

        val ray1: Vector = !Vector(Point(0.1f,1.0f,-0.1f))
        spherePos.checkCollision(cameraPos, ray1)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray2: Vector = !Vector(Point(0.01f,1.0f,-0.17f))
        spherePos.checkCollision(cameraPos, ray2)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray3: Vector = !Vector(Point(-0.1865f,1.0f,0.2f))
        spherePos.checkCollision(cameraPos, ray3)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray4: Vector = !Vector(Point(0.112f,1.0f,-0.009f))
        spherePos.checkCollision(cameraPos, ray4)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)
    }

    @org.junit.Test fun testNormal() {
        val spherePos: Sphere = Sphere(Point(0.0f, 50.0f, 0.0f), 20f, Material())
        val rayY: Vector = !Vector(Point(0.0f,1.0f,0.0f))
        val cameraPos: Point = Point(0f,0f,0f)
        spherePos.checkCollision(cameraPos, rayY)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)

        val ray3: Vector = !Vector(Point(-0.1865f,1.0f,0.2f))
        spherePos.checkCollision(cameraPos, ray3)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)

        val ray4: Vector = !Vector(Point(0.112f,1.0f,-0.009f))
        spherePos.checkCollision(cameraPos, ray4)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)
    }
}