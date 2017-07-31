import org.junit.Assert.*

class VectorPointTest {

    @org.junit.Test fun testScalarProduct() {
        var vec1 = Vector(Point(5f, 6f, 7f))
        var result = vec1 * 3f
        assertTrue(result.direction.x == 15f)
        assertTrue(result.direction.y == 18f)
        assertTrue(result.direction.z == 21f)

        vec1 = Vector(Point(-3f, -1f, -4f))
        result = vec1 * (-1f)
        assertTrue(result.direction.x == 3f)
        assertTrue(result.direction.y == 1f)
        assertTrue(result.direction.z == 4f)
    }

    @org.junit.Test fun testDotProduct() {
        var vec1 = Vector(Point(5f, 6f, 7f))
        var vec2 = Vector(Point(3f, 1f, 4f))
        var result = vec1 * vec2
        assertTrue(result == 49f)
    }

    @org.junit.Test fun testAddition() {
        var vec1 = Vector(Point(5f, 6f, 7f))
        var vec2 = Vector(Point(3f, 1f, 4f))
        var result = vec1 + vec2
        assertTrue(result.direction.x == 8f)
        assertTrue(result.direction.y == 7f)
        assertTrue(result.direction.z == 11f)

        vec1 = Vector(Point(-3f, -1f, -4f))
        vec2 = Vector(Point(3f, 1f, 4f))
        result = vec1 + vec2
        assertTrue(result.direction.x + result.direction.y + result.direction.z == 0f)
    }

    @org.junit.Test fun testSubtraction() {
        var vec1 = Vector(Point(5f, 6f, 7f))
        var vec2 = Vector(Point(3f, 1f, 4f))
        var result = vec1 - vec2
        assertTrue(result.direction.x == 2f)
        assertTrue(result.direction.y == 5f)
        assertTrue(result.direction.z == 3f)

        vec1 = Vector(Point(-3f, -1f, -4f))
        vec2 = Vector(Point(3f, 1f, 4f))
        result = vec1 - vec2
        assertTrue(result.direction.x == -6f)
        assertTrue(result.direction.y == -2f)
        assertTrue(result.direction.z == -8f)
    }

    @org.junit.Test fun testNormalized() {
        var vec1 = Vector(Point(3f, 4f, 7f))
        var product = !vec1
        assertTrue(product.getModulo() - 1 < 0.00001)

        vec1 = Vector(Point(1f, 1f, 1f))
        product = !vec1
        assertTrue(product.getModulo() - 1 < 0.00001)
    }

    @org.junit.Test fun testModulo() {
//        origin
        var vec1 = Vector(Point(3f, 4f, 7f))
        var result = vec1.getModulo()
        assertTrue(result - 8.6 < 0.1)
    }

    @org.junit.Test fun testCrossProduct() {
        var vec1 = Vector(Point(5f, 0f, 0f))
        var vec2 = Vector(Point(0f, 3f, 0f))
        var result = vec1.getCrossProduct(vec2)
        assertTrue(result.direction.x == 0.0f)
        assertTrue(result.direction.y == 0.0f)
        assertTrue(result.direction.z > 0f)

        vec1 = Vector(Point(0.0f, 0.0f, -10.0f))
        vec2 = Vector(Point(0.0f, 3.0f, 0.0f))
        result = vec1.getCrossProduct(vec2)
        assertTrue(result.direction.z == 0.0f)
        assertTrue(result.direction.y == 0.0f)
        assertTrue(result.direction.x > 0f)
    }

    @org.junit.Test fun testRandomGenerator() {
        var random = Point.getRandomPoint(-1.0f,1.0f)
        assertTrue(random.x >= -1 && random.x < 1)
        assertTrue(random.y >= -1 && random.y < 1)
        assertTrue(random.z >= -1 && random.z < 1)

        random = Point.getRandomPoint(-0.004f,0.6f)
        assertTrue(random.x >= -0.004 && random.x < 0.6)
        assertTrue(random.y >= -0.004 && random.y < 0.6)
        assertTrue(random.z >= -0.004 && random.z < 0.6)
    }

    @org.junit.Test fun testRandomUnitVector() {
        var randVec1 = Vector.randomUnitVector()
        var randVec2 = Vector.randomUnitVector()
        var randVec3 = Vector.randomUnitVector()
        assertTrue(randVec1.getModulo() - 1 < 0.001)
        assertTrue(randVec2.getModulo() - 1 < 0.001)
        assertTrue(randVec3.getModulo() - 1 < 0.001)
    }
}