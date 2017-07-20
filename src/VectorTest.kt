import org.junit.Assert.*

class VectorTest {
    @org.junit.Test fun testScalarProduct() {
        var vec1 = Vector(5.0, 6.0, 7.0)
        var vec2 = Vector(3.0, 1.0, 4.0)
        var product = vec1 * vec2
        assertTrue(product == 49.0)

        vec1 = Vector(2.1, 1.0, 3.6)
        vec2 = Vector(6.9, 0.9, 1.2)
        product = vec1 * vec2
        assertTrue(product == 19.71)

        vec1 = Vector(1.0, 1.0, 1.0)
        vec2 = Vector(1.0, 1.0, 1.0)
        product = vec1 * vec2
        assertTrue(product == 3.0)
    }

    @org.junit.Test fun testAddition() {
        var vec1 = Vector(5.0, 6.0, 7.0)
        var vec2 = Vector(3.0, 1.0, 4.0)
        var product = vec1 + vec2
        assertTrue(product.x == 8.0)
        assertTrue(product.y == 7.0)
        assertTrue(product.z == 11.0)

        vec1 = Vector(2.1, 1.0, 3.6)
        vec2 = Vector(6.9, 0.9, 1.2)
        product = vec1 + vec2
        assertTrue(product.x == 9.0)
        assertTrue(product.y == 1.9)
        assertTrue(product.z == 4.8)

        vec1 = Vector(1.0, 1.0, 1.0)
        vec2 = Vector(1.0, 1.0, 1.0)
        product = vec1 + vec2
        assertTrue(product.x == 2.0)
        assertTrue(product.y == 2.0)
        assertTrue(product.z == 2.0)
    }

    @org.junit.Test fun testSubtraction() {
        var vec1 = Vector(5.0, 6.0, 7.0)
        var vec2 = Vector(3.0, 1.0, 4.0)
        var product = vec1 - vec2
        assertTrue(product.x == 2.0)
        assertTrue(product.y == 5.0)
        assertTrue(product.z == 3.0)

        vec1 = Vector(6.9, 1.0, 3.6)
        vec2 = Vector(2.0, 0.9, 1.2)
        product = vec1 - vec2
        assertTrue(product.x == (6.9 - 2.0))
        assertTrue(product.y == (1.0 - 0.9))
        assertTrue(product.z == (3.6 - 1.2))

        vec1 = Vector(1.0, 1.0, 1.0)
        vec2 = Vector(1.0, 1.0, 1.0)
        product = vec1 - vec2
        assertTrue(product.x == 0.0)
        assertTrue(product.y == 0.0)
        assertTrue(product.z == 0.0)
    }

    @org.junit.Test fun testNormalized() {
        var vec1 = Vector(5.0, 6.0, 7.0)
        var product = !vec1
        var length = Math.sqrt((product.x*product.x) + (product.y*product.y) + (product.z*product.z))
        assertTrue(length - 1 < 0.000001)

        vec1 = Vector(6.9, 1.0, 3.6)
        product = !vec1
        length = Math.sqrt((product.x*product.x) + (product.y*product.y) + (product.z*product.z))
        assertTrue(length - 1 < 0.000001)

        vec1 = Vector(1.0, 1.0, 1.0)
        product = !vec1
        length = Math.sqrt((product.x*product.x) + (product.y*product.y) + (product.z*product.z))
        assertTrue(length - 1 < 0.000001)
    }

    @org.junit.Test fun testLength() {
        var vec1 = Vector(5.0, 6.0, 7.0)
        var vecNorm = !vec1
        var length = vecNorm.getLength()
        assertTrue(length - 1 < 0.000001)

        vec1 = Vector(6.9, 1.0, 3.6)
        vecNorm = !vec1
        length = vecNorm.getLength()
        assertTrue(length - 1 < 0.000001)
    }
}