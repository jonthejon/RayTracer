import org.junit.Assert.*

class VectorPointTest {

    @org.junit.Test fun testScalarProduct() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var result = vec1 * 3.0
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == 15.0)
        assertTrue(result.direction.y == 18.0)
        assertTrue(result.direction.z == 21.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        result = vec1 * (-1.0)
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == 3.0)
        assertTrue(result.direction.y == 1.0)
        assertTrue(result.direction.z == 4.0)

        vec1 = Vector(Point(5.0, 6.0, 7.0), Point(5.0, 6.0, 7.0))
        result = vec1 * 2.0
        assertTrue(result.origin.x == 5.0)
        assertTrue(result.origin.y == 6.0)
        assertTrue(result.origin.z == 7.0)
        assertTrue(result.direction.x == 10.0)
        assertTrue(result.direction.y == 12.0)
        assertTrue(result.direction.z == 14.0)
    }

    @org.junit.Test fun testDotProduct() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 * vec2
        assertTrue(result == 49.0)

        vec1 = Vector(Point(5.0, 6.0, 7.0), Point(8.0, 8.0, 5.0))
        vec2 = Vector(Point(1.0, 8.0, 6.0), Point(3.0, 1.0, 4.0))
        result = vec1 * vec2
        assertTrue(result == (6.0)+(-14.0)+(4.0))
    }

    @org.junit.Test fun testAddition() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 + vec2
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == 8.0)
        assertTrue(result.direction.y == 7.0)
        assertTrue(result.direction.z == 11.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        vec2 = Vector(Point(3.0, 1.0, 4.0))
        result = vec1 + vec2
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x + result.direction.y + result.direction.z == 0.0)

        vec1 = Vector(Point(5.0, 6.0, 7.0), Point(5.0, 6.0, 7.0))
        vec2 = Vector(Point(1.0, 8.0, 6.0), Point(3.0, 1.0, 4.0))
        result = vec1 + vec2
        assertTrue(result.origin.x == 5.0)
        assertTrue(result.origin.y == 6.0)
        assertTrue(result.origin.z == 7.0)
        assertTrue(result.direction.x == 8.0)
        assertTrue(result.direction.y == 7.0)
        assertTrue(result.direction.z == 11.0)
    }

    @org.junit.Test fun testSubtraction() {
        var vec1 = Vector(Point(5.0, 6.0, 7.0))
        var vec2 = Vector(Point(3.0, 1.0, 4.0))
        var result = vec1 - vec2
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == 2.0)
        assertTrue(result.direction.y == 5.0)
        assertTrue(result.direction.z == 3.0)

        vec1 = Vector(Point(-3.0, -1.0, -4.0))
        vec2 = Vector(Point(3.0, 1.0, 4.0))
        result = vec1 - vec2
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == -6.0)
        assertTrue(result.direction.y == -2.0)
        assertTrue(result.direction.z == -8.0)

        vec1 = Vector(Point(5.0, 6.0, 7.0), Point(5.0, 6.0, 7.0))
        vec2 = Vector(Point(1.0, 8.0, 6.0), Point(3.0, 1.0, 4.0))
        result = vec1 - vec2
        assertTrue(result.origin.x == 5.0)
        assertTrue(result.origin.y == 6.0)
        assertTrue(result.origin.z == 7.0)
        assertTrue(result.direction.x == 2.0)
        assertTrue(result.direction.y == 5.0)
        assertTrue(result.direction.z == 3.0)
    }

    @org.junit.Test fun testNormalized() {
        var vec1 = Vector(Point(3.0, 4.0, 7.0))
        var product = !vec1
        assertTrue(product.getModulo() - 1 < 0.00001)

        vec1 = Vector(Point(2.0, 2.0, 2.0), Point(5.0, 6.0, 9.0))
        product = !vec1
//        println(product.direction.x)
//        println(product.direction.y)
//        println(product.direction.z)
//        println("Final modulo: " + product.getModulo())
        assertTrue(product.getModulo() - 1 < 0.00001)

        vec1 = Vector(Point(1.0, 1.0, 1.0))
        product = !vec1
        assertTrue(product.getModulo() - 1 < 0.00001)

        vec1 = Vector(Point(10.0, 10.0, 10.0), Point(5.0, 6.0, 9.0))
        product = !vec1
//        println("Final modulo: " + product.getModulo())
//        println(product.direction.x)
//        println(product.direction.y)
//        println(product.direction.z)
        assertTrue(product.getModulo() - 1 < 0.00001)
    }

    @org.junit.Test fun testModulo() {
//        origin
        var vec1 = Vector(Point(3.0, 4.0, 7.0))
        var result = vec1.getModulo()
        assertTrue(result - 8.6 < 0.1)

//        far
        vec1 = Vector(Point(2.0, 2.0, 2.0), Point(5.0, 6.0, 9.0))
        result = vec1.getModulo()
        assertTrue(result - 8.6 < 0.1)

//        dot product test
        vec1 = Vector(Point(1.0, 3.0, 10.0), Point(5.0, 6.0, 7.0))
        result = vec1.getModulo()
        val dotTest = Math.sqrt(vec1 * vec1)
        assertTrue(result == dotTest)
    }

    @org.junit.Test fun testCrossProduct() {
        var vec1 = Vector(Point(5.0, 0.0, 0.0))
        var vec2 = Vector(Point(0.0, 3.0, 0.0))
        var result = vec1.getCrossProduct(vec2)
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.x == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.z > 0)

        vec1 = Vector(Point(0.0, 0.0, -10.0))
        vec2 = Vector(Point(0.0, 3.0, 0.0))
        result = vec1.getCrossProduct(vec2)
        assertTrue(result.origin.x + result.origin.y + result.origin.z == 0.0)
        assertTrue(result.direction.z == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.x > 0)

        vec1 = Vector(Point(5.0, 6.0, 7.0),Point(0.0, 0.0, -10.0))
        vec2 = Vector(Point(1.0, 1.0, 1.0),Point(0.0, 3.0, 0.0))
        result = vec1.getCrossProduct(vec2)
        assertTrue(result.origin.x == 5.0)
        assertTrue(result.origin.y == 6.0)
        assertTrue(result.origin.z == 7.0)
        assertTrue(result.direction.z == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.x > 0)

        vec1 = Vector(Point(5.0, 6.0, 7.0),Point(0.0, 0.0, -10.0))
        vec2 = Vector(Point(1.0, 1.0, 1.0),Point(0.0, 3.0, 0.0))
        result = vec2.getCrossProduct(vec1)
        assertTrue(result.origin.x == 1.0)
        assertTrue(result.origin.y == 1.0)
        assertTrue(result.origin.z == 1.0)
        assertTrue(result.direction.z == 0.0)
        assertTrue(result.direction.y == 0.0)
        assertTrue(result.direction.x < 0)
    }
}