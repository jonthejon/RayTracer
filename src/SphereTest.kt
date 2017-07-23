import org.junit.Assert.*

class SphereTest {

    @org.junit.Test fun testSphereCollision () {
        val spherePos: Sphere = Sphere(Point(0.0, 50.0, 0.0), 20.0)
        val rayY: Vector = !Vector(Point(0.0,1.0,0.0))
        val rayX: Vector = !Vector(Point(1.0,0.0,0.0))
        val rayZ: Vector = !Vector(Point(0.0,0.0,1.0))
        val testCollPosY = spherePos.checkColision(rayY)
        val testCollPosX = spherePos.checkColision(rayX)
        val testCollPosZ = spherePos.checkColision(rayZ)
        assertTrue(testCollPosY)
        assertTrue(!testCollPosX && !testCollPosZ)

        val sphereNeg: Sphere = Sphere(Point(0.0, -50.0, 0.0), 20.0)
        val testCollNegY = sphereNeg.checkColision(rayY)
        val testCollNegX = sphereNeg.checkColision(rayX)
        val testCollNegZ = sphereNeg.checkColision(rayZ)
        assertTrue(!testCollNegX && !testCollNegZ && !testCollNegY)
    }

    @org.junit.Test fun testCollisionPoint() {
        val spherePos: Sphere = Sphere(Point(0.0, 50.0, 0.0), 20.0)
        val rayY: Vector = !Vector(Point(0.0,1.0,0.0))
        spherePos.checkColision(rayY)
        val hitDist = spherePos.hitPoint.getDistance(rayY.origin)
//        println(spherePos.hitPoint.x)
//        println(spherePos.hitPoint.y)
//        println(spherePos.hitPoint.z)
//        println(hitDist)
        assertTrue(hitDist - 30.0 < 0.01)

        val ray1: Vector = !Vector(Point(0.1,1.0,-0.1))
        spherePos.checkColision(ray1)
//        println(spherePos.hitPoint.x)
//        println(spherePos.hitPoint.y)
//        println(spherePos.hitPoint.z)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray2: Vector = !Vector(Point(0.01,1.0,-0.17))
        spherePos.checkColision(ray2)
//        println(spherePos.hitPoint.x)
//        println(spherePos.hitPoint.y)
//        println(spherePos.hitPoint.z)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray3: Vector = !Vector(Point(-0.1865,1.0,0.2))
        spherePos.checkColision(ray3)
//        println(spherePos.hitPoint.x)
//        println(spherePos.hitPoint.y)
//        println(spherePos.hitPoint.z)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)

        val ray4: Vector = !Vector(Point(0.112,1.0,-0.009))
        spherePos.checkColision(ray4)
//        println(spherePos.hitPoint.x)
//        println(spherePos.hitPoint.y)
//        println(spherePos.hitPoint.z)
        assertTrue(20 - spherePos.hitPoint.getDistance(spherePos.center) < 0.01)
    }

    @org.junit.Test fun testNormal() {
        val spherePos: Sphere = Sphere(Point(0.0, 50.0, 0.0), 20.0)
        val rayY: Vector = !Vector(Point(0.0,1.0,0.0))
        spherePos.checkColision(rayY)
//        println(spherePos.normal.origin.x)
//        println(spherePos.normal.origin.y)
//        println(spherePos.normal.origin.z)
//        println(spherePos.normal.direction.x)
//        println(spherePos.normal.direction.y)
//        println(spherePos.normal.direction.z)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)

        val ray3: Vector = !Vector(Point(-0.1865,1.0,0.2))
        spherePos.checkColision(ray3)
//        println(spherePos.normal.origin.x)
//        println(spherePos.normal.origin.y)
//        println(spherePos.normal.origin.z)
//        println(spherePos.normal.direction.x)
//        println(spherePos.normal.direction.y)
//        println(spherePos.normal.direction.z)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)

        val ray4: Vector = !Vector(Point(0.112,1.0,-0.009))
        spherePos.checkColision(ray4)
//        println(spherePos.normal.origin.x)
//        println(spherePos.normal.origin.y)
//        println(spherePos.normal.origin.z)
//        println(spherePos.normal.direction.x)
//        println(spherePos.normal.direction.y)
//        println(spherePos.normal.direction.z)
        assertTrue(spherePos.normal.getModulo() - 1 < 0.001)
    }
}