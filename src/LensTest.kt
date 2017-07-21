import org.junit.Assert.*


class LensTest {
    @org.junit.Test fun testPixelSize() {
        var lens = Lens(10.0)
        assertTrue(lens.height == 600.0)
        assertTrue(lens.pixelDim - 1 < 0.00001)

        lens = Lens(800.0, 1000, 1600)
        assertTrue(lens.height - 500 < 0.00001)
        assertTrue(lens.pixelDim - 0.5 < 0.00001)
    }

    @org.junit.Test fun testPixelDensity() {
        var lens = Lens(10.0)
        var expNumOfPixels = 600 * 600
        var numOfPixels = lens.pixels.size * lens.pixels[0].size
        assertTrue(expNumOfPixels == numOfPixels)

        lens = Lens(800.0, 1000, 1600)
        expNumOfPixels = 1000 * 1600
        numOfPixels = lens.pixels.size * lens.pixels[0].size
        assertTrue(expNumOfPixels == numOfPixels)
    }

    @org.junit.Test fun testRender() {
        val lens = Lens(800.0, 1600, 1000)
        lens.render()
    }

    @org.junit.Test fun testRayGenerator() {
        val lens = Lens(40.0, 320, 200)
        lens.shootRays()
        lens.render()
    }
}