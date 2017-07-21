import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Lens (val width: Double = 600.0, val xRes: Int = 600, val yRes: Int = 600) {

    val pixelDim: Double = this.width / this.xRes
    val height: Double = this.yRes * pixelDim
    val dist: Double = ((width/2) / (Math.tan(0.6911504/2)))
    val pixels = Array<Array<Pixel>>(yRes) {Array<Pixel>(xRes) {Pixel()}}

    fun shootRays(): Unit {
        val scene: Scene = Scene()
        val midPix: Double = pixelDim / 2
        var point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
        val horizAdder: Point = Point(pixelDim, 0.0, 0.0)
        val vertAdder: Point = Point(0.0, 0.0, -pixelDim)
        var rayDot: Point
        var ray: Vector
//        val camVector: Vector = Vector(Point(0.0,0.0,0.0))
        for (pixRows in pixels) {
            for (pixel in pixRows) {
                rayDot = point0 + (horizAdder * pixRows.indexOf(pixel).toDouble())
                ray = !Vector(rayDot)
                val color = scene.trace(ray)
//                val color = scene.trace(ray, camVector)
                pixel.color = color
                +pixel
            }
            point0 += vertAdder
        }
    }

    fun render(): Unit {
        val image: BufferedImage = BufferedImage(this.xRes, this.yRes, BufferedImage.TYPE_INT_RGB)

        var countx = 0
        var county = 0
        for (pixRows in pixels) {
//        for (y in 0..(this.yRes - 1)) {
            for (pixel in pixRows) {
//            for (x in 0..(this.xRes - 1)) {
                val color = pixel.color
//                val color = pixels[x][y].color
//                println(pixels[x][y].numRays)
//                image.setRGB(x,y,color)
//                println("pixel x: " + pixels.indexOf(pixRows))
//                println("pixel y: " + pixRows.indexOf(pixel))
//                println("pixel x: " + countx)
//                println("pixel y: " + county)
//                println("----")
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