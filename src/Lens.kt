import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Lens (val width: Float = 600f, val xRes: Int = 600, val yRes: Int = 600, val iterations: Int = 1) {

    val pixelDim: Float = this.width / this.xRes
    val height: Float = this.yRes * pixelDim
    val dist: Float = ((width/2) / (Math.tan(0.6911504/2)).toFloat())
    val pixels = Array<Array<Pixel>>(yRes) {Array<Pixel>(xRes) {Pixel()}}
    val numOfRaysPixel: Int = iterations
    val scene: Scene = Scene()
    val midPix: Float = pixelDim / 2
    var point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
    val horizAdder: Point = Point(pixelDim, 0f, 0f)
    val vertAdder: Point = Point(0f, 0f, -pixelDim)
    val cameraPos: Point = Point(0f,0f,0f)

    fun shootRays(): Unit {
        var rayDot: Point
        var ray: Vector
        for (laps in 1..numOfRaysPixel) {
            print("\rIteration: " + laps + "/" + numOfRaysPixel)
            for (pixRows in pixels) {
                for (pixel in pixRows) {
                    val randPoint: Point = Point.getRandomPoint(-midPix, midPix)
                    rayDot = point0 + (horizAdder * pixRows.indexOf(pixel).toFloat())
                    rayDot.x += randPoint.x
                    rayDot.z += randPoint.z
                    ray = !Vector(rayDot)
                    val color = scene.trace(cameraPos, ray, 0)
                    pixel.addColor(color)
                    +pixel
                }
                point0 += vertAdder
            }
            point0 = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
        }
    }

    fun render(): Unit {
        val image: BufferedImage = BufferedImage(this.xRes, this.yRes, BufferedImage.TYPE_INT_RGB)

        var countx = 0
        var county = 0
        for (pixRows in pixels) {
            for (pixel in pixRows) {
                val color = pixel.getFinalColor()
                image.setRGB(countx,county,color)
                countx++
            }
            countx = 0
            county++
        }

        val output: File = File("./image.png")
        ImageIO.write(image, "png", output)
    }
}