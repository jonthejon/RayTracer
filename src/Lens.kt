import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Lens (val width: Double = 600.0, val xRes: Int = 600, val yRes: Int = 600) {

    val pixelDim: Double = this.width / this.xRes
    val height: Double = this.yRes * pixelDim
    val dist: Double = ((width/2) / (Math.tan(0.6911504/2)))
    val pixels = Array<Array<Pixel>>(yRes) {Array<Pixel>(xRes) {Pixel()}}
    val numOfRaysPixel: Int = 30

    fun shootRays(): Unit {
        val scene: Scene = Scene()
        val midPix: Double = pixelDim / 2
        var point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
        val horizAdder: Point = Point(pixelDim, 0.0, 0.0)
        val vertAdder: Point = Point(0.0, 0.0, -pixelDim)
        var rayDot: Point
        var ray: Vector
        for (laps in 1..numOfRaysPixel) {
            print("\rIteration: " + laps + "/" + numOfRaysPixel)
            for (pixRows in pixels) {
                for (pixel in pixRows) {
                    val randPoint: Point = Point.getRandomPoint(-midPix, midPix)
                    rayDot = point0 + (horizAdder * pixRows.indexOf(pixel).toDouble())
                    rayDot.x += randPoint.x
                    rayDot.z += randPoint.z
                    ray = !Vector(rayDot)
                    val color = scene.trace(ray, 0)
//                if (color.red == 97) println("Got Sky")
//                if (color.red == 0) println("floor")
//                println(color)
                    pixel.addColor(color)
//                pixel.color = color
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
//        for (y in 0..(this.yRes - 1)) {
            for (pixel in pixRows) {
//            for (x in 0..(this.xRes - 1)) {
//                if (pixel.colorArr.size != 3) {
//                    println("Missed a Pixel!")
//                }
//                println(pixel.colorArr.size)
                val color = pixel.getFinalColor()
//                val color = pixel.color
//                val albedo = pixels[x][y].albedo
//                println(pixels[x][y].numRays)
//                image.setRGB(x,y,albedo)
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