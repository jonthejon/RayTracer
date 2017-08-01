import java.awt.image.BufferedImage
import java.io.*
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
//        val image: Image = this.getImage("floor1ref_sphere2sol")
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
        val image: Image = Image()
        println("saving data to csv file.")
        image.addPixels("floor1ref_sphere2sol", pixels)
//        this.saveImage(image)
//        this.saveImage(pixels)
    }

/*    fun getImage(fileName: String): Image {
        println("Loading image")
        var fin: FileInputStream? = null
        var ois: ObjectInputStream? = null
        var image = Image(fileName, xRes, yRes)

        try {
            fin = FileInputStream("./" + fileName + ".ser")
            ois = ObjectInputStream(fin)
            val imageTemp = ois.readObject()
            if (imageTemp is Image) image = imageTemp
            image.name = fileName
        } catch (fex: FileNotFoundException) {
            println("File not found. Creating a new one.")
            return Image(fileName, xRes, yRes)
        }
        catch (ex: Exception) {
            println("Something went wrong during access to the file.")
            ex.printStackTrace()
        } finally {
            if (fin != null) {
                try {
                    fin.close()
                } catch (ioex: IOException) {
                    println("Unable to close FileInputStream.")
                    ioex.printStackTrace()
                }
            }
            if (ois != null) {
                try {
                    ois.close()
                } catch (ioex: IOException) {
                    println("Unable to close ObjectInputStream.")
                    ioex.printStackTrace()
                }
            }
        }
        return image
    }

    fun saveImage(image: Image) {
        println("Saving image")
        var fout: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            fout = FileOutputStream("./" + image.name + ".ser")
            oos = ObjectOutputStream(fout)
            oos.writeObject(image)
        } catch (fex: FileNotFoundException) {
            println("Unable to save Image. File not found!")
        }catch (ex: Exception) {
            println("Unable to save Image object to file.")
            ex.printStackTrace()
        } finally {
            if (fout != null) {
                try {
                    fout.close()
                } catch (ioex: IOException) {
                    println("Unable to close FileOutputStream.")
                    ioex.printStackTrace()
                }
            }
            if (oos != null) {
                try {
                    oos.close()
                } catch (ioex: IOException) {
                    println("Unable to close ObjectOutputStream.")
                    ioex.printStackTrace()
                }
            }
        }
    }*/


    fun render(): Unit {
        val screen: BufferedImage = BufferedImage(this.xRes, this.yRes, BufferedImage.TYPE_INT_RGB)

        var countx = 0
        var county = 0
        for (pixRows in pixels) {
            for (pixel in pixRows) {
                val color = pixel.getFinalColor()
                screen.setRGB(countx,county,color)
                countx++
            }
            countx = 0
            county++
        }

        val output: File = File("./screen.png")
        ImageIO.write(screen, "png", output)
    }

    companion object {

        fun render(xRes: Int, yRes: Int, fileName: String): Unit {
            println("Rendering image")
            val screen: BufferedImage = BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB) // creating the buffered image file
            val comma: String = "," // creating the delimeter for reading

            var countx = 0 // counter for the x coordinate of the screen
            var county = 0 // counter for the y coordinate of the screen
            var fileReader: BufferedReader? = null // creating a new Buffered reader
            try {
                fileReader = BufferedReader(FileReader("./" + fileName + ".csv")) // trying to open the file.
                val colorArr = ArrayList<MyColor>() // creating an arraylist that will hold all the colors of a given pixel
                fileReader.forEachLine { // each line in the csv file represents a pixel
                    val strFloats: List<String> = it.split(comma) // getting a list of strings that holds the floats colors
                    var color: MyColor = MyColor(-1f,-1f,-1f) // creating a blank new color with -1f values
                    strFloats.forEach { // each string is a float that may be a red, green or blue color
                        if (color.isFull()) { // this will run if the color object is filled with r, g, b values other than -1f
                            colorArr.add(color) // adding the filled color to the collor array
                            color = MyColor(-1f,-1f,-1f) // reset the color so we can fill it with other values
                            color.add(it.toFloat()) // adding the next float color into the color object
                        } else { // this will run in case the color object is not full. (there is at least one color that is -1f)
                            color.add(it.toFloat()) // adding the next float color into the color object
                        }
                    } // end of the colors of this pixel. But the color object is full with the last triplet
                    if (color.isFull()) colorArr.add(color) // adding the filled color to the collor array
                    val pixelColor: MyColor = getAverageColor(colorArr) // getting the average color given all the colors of this pixel


//                    val (r,g,b) = pixelColor
//                    println("---")
//                    println("Final red: " + r)
//                    println("Final green: " + g)
//                    println("Final blue: " + b)
//                    println("---")


//                    println("X: " + countx + "; Y: " + county)
                    screen.setRGB(countx,county,pixelColor.getJavaColor()) // setting the correct pixel with the final color set to int
                    if (countx < xRes - 1) countx++ // until the x coord is less than the X-resolution, increment it
                    else { // if not, then set the x coord to 0 and increment the y coord
                        countx = 0
                        county++
                    }
                    colorArr.clear() // clearing all the data inside this array so we can use it again for another pixel
                } // ends the computation for this particular pixel
            } catch (fex: FileNotFoundException) { // this will run if the file does not exists
                println("")
                println("No csv file found. Exiting the render.")
                return
            } finally { // will run independently
                if (fileReader != null) { // checking to see if we have a working reader
                    fileReader.close() // closing the reader
                }
            }
            val output: File = File("./" + fileName + ".png") // creating the actual image file
            ImageIO.write(screen, "png", output) // writing the image file using the buffered image
        }

        fun getAverageColor(colorArr: ArrayList<MyColor>): MyColor {
            val numColors: Int = colorArr.size
            var reds: Float = 0f
            var greens: Float = 0f
            var blues: Float = 0f
            colorArr.forEach {
                reds += it.r
                greens += it.g
                blues += it.b
            }
            reds /= numColors
            greens /= numColors
            blues /= numColors
            return MyColor(reds, greens, blues)
        }
    }
}