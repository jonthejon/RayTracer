import org.junit.Assert.*

class VectorPointTest {

    @org.junit.Test fun testScalarProduct() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var result = vec1 * 3.0
        assertTrue(result.direction.x == 15.0)
        assertTrue(result.direction.y == 18.0)
        assertTrue(result.direction.z == 21.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        result = vec1 * (-1.0)
        assertTrue(result.direction.x == 3.0)
        assertTrue(result.direction.y == 1.0)
        assertTrue(result.direction.z == 4.0)
    }

    @org.junit.Test fun testDotProduct() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 * vec2
        assertTrue(result == 49.0)
    }

    @org.junit.Test fun testAddition() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 + vec2
        assertTrue(result.direction.x == 8.0)
        assertTrue(result.direction.y == 7.0)
        assertTrue(result.direction.z == 11.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        vec2 = Vector(Point(3.0, 1.0, 4.0))
        result = vec1 + vec2
        assertTrue(result.direction.x + result.direction.y + result.direction.z == 0.0)
    }

    @org.junit.Test fun testSubtraction() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 - vec2
        assertTrue(result.direction.x == 2.0)
        assertTrue(result.direction.y == 5.0)
        assertTrue(result.direction.z == 3.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        vec2 = Vector(Point(3.0, 1.0, 4.0))
        result = vec1 - vec2
        assertTrue(result.direction.x == -6.0)
        assertTrue(result.direction.y == -2.0)
        assertTrue(result.direction.z == -8.0)
    }

    @org.junit.Test fun testNormalized() {
//        var vec1 = Vector(Point(3.0, 4.0, 7.0))
//        var product = !vec1
//        assertTrue(product.getModulo() - 1 < 0.00001 && product.getModulo() - 1 > -0.00001)
//
//        vec1 = Vector(Point(1.0, 1.0, 1.0))
//        product = !vec1
//        assertTrue(product.getModulo() - 1 < 0.00001)

        var vec1 = Vector(Point(-0.7, 0.0, 0.0))
        var product = !vec1
        val (x7,y7,z7) = product.direction
        println("result: " + x7 + ", " + y7 + ", " + z7)
//        println(product.getModulo())
//        assertTrue(product.getModulo() - 1 < 0.00001)
    }

    @org.junit.Test fun testModulo() {
//        origin
        var vec1 = Vector(Point(3.0, 4.0, 7.0))
        var result = vec1.getModulo()
        assertTrue(result - 8.6 < 0.1)
    }

    @org.junit.Test fun testCrossProduct() {
        var vec1 = Vector(Point(5.0, 0.0, 0.0))
        var vec2 = Vector(Point(0.0, 3.0, 0.0))
        var result = vec1.getCrossProduct(vec2)
        assertTrue(result.direction.x == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.z > 0.0)

        vec1 = Vector(Point(0.0, 0.0, -10.0))
        vec2 = Vector(Point(0.0, 3.0, 0.0))
        result = vec1.getCrossProduct(vec2)
        assertTrue(result.direction.z == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.x > 0.0)
    }

    @org.junit.Test fun testRandomGenerator() {
        var random = Point.getRandomPoint(-1.0,1.0)
        assertTrue(random.x >= -1 && random.x < 1)
        assertTrue(random.y >= -1 && random.y < 1)
        assertTrue(random.z >= -1 && random.z < 1)

        random = Point.getRandomPoint(-0.004,0.6)
        assertTrue(random.x >= -0.004 && random.x < 0.6)
        assertTrue(random.y >= -0.004 && random.y < 0.6)
        assertTrue(random.z >= -0.004 && random.z < 0.6)
    }

    @org.junit.Test fun testRandomUnitVector() {
        var randVec1 = Vector.randomUnitVector()
        var randVec2 = Vector.randomUnitVector()
        var randVec3 = Vector.randomUnitVector()
        println(randVec1.getModulo())
        println(randVec2.getModulo())
        println(randVec3.getModulo())
        assertTrue(randVec1.getModulo() - 1 < 0.001 && randVec1.getModulo() - 1 > -0.001)
        assertTrue(randVec2.getModulo() - 1 < 0.001 && randVec2.getModulo() - 1 > -0.001)
        assertTrue(randVec3.getModulo() - 1 < 0.001 && randVec3.getModulo() - 1 > -0.001)

        var cross: Vector = !(randVec2.getCrossProduct(randVec1))
        println(cross.getModulo())
        var dot1 = cross * randVec1
        println(dot1)
        var dot2 = cross * randVec2
        println(dot2)

//        var vecTest1 = Vector(Point(0f,-0.70710695f,-0.70710665f))
//        var vecTest2 = Vector(Point(0.7071066f,-0.5000004f,0.49999985f))
//        var dot3 = vecTest1 * vecTest2
//        println(dot3)
    }
}