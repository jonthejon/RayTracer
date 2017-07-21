import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Lens (val width: Double = 600.0, val xRes: Int = 600, val yRes: Int = 600) {

    val pixelDim: Double = this.width / this.xRes
    val height: Double = this.yRes * pixelDim
    val dist: Double = ((width/2) / (Math.tan(0.6911504/2)))
    val pixels = Array<Array<Pixel>>(yRes) {Array<Pixel>(xRes) {Pixel()}}

    fun shootRays(): Unit {
//        println("dist: " + dist)
        val scene: Scene = Scene()
        val midPix: Double = pixelDim / 2
//        println("xRes: " + xRes)
//        println("yRes: " + yRes)
//        println("width: " + width)
//        println("tan: " + (Math.tan(0.6911504/2)))
//        println("height: " + height)
//        println("pixelDim: " + pixelDim)
//        println("midPix: " + midPix)
//        println("----")
//        println("pixels X: " + pixels[0].size)
//        println("pixels Y: " + pixels.size)
//        println("----")
        var point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
//        println("point0: " + point0.x  + " " + point0.y + " " + point0.z)
//        println("----")
//        println("----")
        var horizAdder: Point = Point(pixelDim, 0.0, 0.0)
        var vertAdder: Point = Point(0.0, 0.0, -pixelDim)
        var rayDot: Point
        var ray: Vector
//        val camVector: Vector = Vector(0.0,0.0,0.0) todo: remove this comment
//        var pixel: Pixel

/*        pixel = pixels[0][0]
        rayDot = point0 + (horizAdder * 0)
        ray = !Vector(rayDot.x, rayDot.y, rayDot.z)
        val color = scene.trace(ray)
        pixel.color = color
        +pixel*/

//        for (y in 0..(this.yRes - 1)) {
        for (pixRows in pixels) {
//            for (x in 0..(this.xRes - 1)) {
            for (pixel in pixRows) {
//                pixel = pixels[x][y]
//                rayDot = point0 + (horizAdder * pixels[pixRows].indexOf(pixel))
//                rayDot = point0 + (horizAdder * pixRows.indexOf(pixel)) todo: remove this comment
//                println("rayDot: " + rayDot.x  + " " + rayDot.y + " " + rayDot.z)
//                println("rayDot x: " + rayDot.x)
//                println("rayDot y: " + rayDot.y)
//                println("rayDot z: " + rayDot.z)
//                println("----")
//                ray = Vector(rayDot.x, rayDot.y, rayDot.z)
//                ray = !Vector(rayDot.x, rayDot.y, rayDot.z) todo: remove this comment
//                println("ray: " + ray.x  + " " + ray.y + " " + ray.z)
//                println("ray Len: " + ray.getModulo())
//                val color = scene.trace(ray, camVector) todo: remove this comment
//                pixel.color = color todo: remove this comment
                +pixel
            }
//            horizAdder = Point(pixelDim, 0.0, 0.0)
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