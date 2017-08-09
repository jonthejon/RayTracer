import com.sun.org.apache.bcel.internal.generic.FLOAD
import java.awt.image.BufferedImage
import java.io.*
import java.util.*
import javax.imageio.ImageIO

class Lens (val width: Double, val xRes: Int, val yRes: Int, val focal: Point, val lensVector: Vector, val sceneDist: Double, val iterations: Int, val fileName: String) {


//    val pixelDim: Float = this.width / this.xRes

//    lens/camera definitions
//    val height: Float = this.yRes * pixelDim
    var height: Double = 0.0
    val pixels = Array<Array<Pixel>>(yRes) {Array<Pixel>(xRes) {Pixel()}}

    val numOfRaysPixel: Int = iterations
    val scene: Scene = Scene()
    //    val midPix: Float = pixelDim / 2
    //    var point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
    val cameraPos: Point = Point()
    val point0: Point = Point()
    val horizReset: Point = Point()
    val horizAdder: Point = Point()
    val vertAdder: Point = Point()
    //    val vertAdder: Point = Point(0f, 0f, -pixelDim)
//    val horizAdder: Point = Point(pixelDim, 0f, 0f)
//    val lensVector: Vector = cameraVector * dist
//    val cameraVector: Vector = !Vector(Point(0f,1f,0f))
//    val cameraPos: Point = Point(0f,0f,0f)

    init {
        val unitLensVec: Vector = !lensVector
//        val (x8,y8,z8) = unitLensVec.direction
//        println("UnitLensVec: " + x8 + ", " + y8 + ", " + z8)
        height = (width / xRes) * yRes // calculating the height of the lens
        val middleLens: Point = focal + (unitLensVec * sceneDist).direction // defining the point that resides in the middle of the lens
//        val middleLens: Point = focal + (lensVector * sceneDist).direction // defining the point that resides in the middle of the lens

//        we do not need to calculate the plane function, given that all we need to continue is the normal of the plane that contains the lens and we already ahve that: lensVector
        val Nh: Vector = !Vector(Point(0.0,0.0,1.0)) // The horizontal guide plane will be the zVector=0 plane. So the normal of this plane is given in this function
//        val (x7,y7,z7) = Nh.direction
//        println("Nh: " + x7 + ", " + y7 + ", " + z7)
//        val Nh: Vector = Vector(Point(0f,0f,1f)) // The horizontal guide plane will be the zVector=0 plane. So the normal of this plane is given in this function
        val guideH: Vector = !(unitLensVec.getCrossProduct(Nh)) // this vector will guide the incremental horizontal steps we'll do on the surface of the lens
//        val guideH: Vector = Nh.getCrossProduct(lensVector) // this vector will guide the incremental horizontal steps we'll do on the surface of the lens
//        val (x5,y5,z5) = guideH.direction
//        println("guideH: " + x5 + ", " + y5 + ", " + z5)
        val Nv: Vector = !Vector(Point(1.0,0.0,0.0)) // The vertical guide plane will be the x=0 plane. So the normal of this plane is given in this function
//        val Nv: Vector = Vector(Point(1f,0f,0f)) // The vertical guide plane will be the x=0 plane. So the normal of this plane is given in this function
        val guideV: Vector = !(unitLensVec.getCrossProduct(Nv)) // this vector will guide the incremental vertical steps we'll do on the surface of the lens
//        val guideV: Vector = Nv.getCrossProduct(lensVector) // this vector will guide the incremental vertical steps we'll do on the surface of the lens
//        val (x6,y6,z6) = guideV.direction
//        println("guideV: " + x6 + ", " + y6 + ", " + z6)

        val p3: Point = middleLens + (guideH * (width/2)).direction // calculating the most extreme point to the right of the lens (left from the camera perspective)
        val p4: Point = p3 + (guideV * (height/2) * (-1.0)).direction // calculating the most extreme upper point starting from the previous point. The result will be point0 from the camera perspective
        horizReset.setPoint(p4)
        point0.setPoint(p4) // setting the point0 of the camera with the calculated extreme point

        val hAdder: Point = (guideH * (-1.0) * (width / xRes)).direction // calculating the horizontal adder so we can navigate on the lens surface
        horizAdder.setPoint(hAdder) // defining the horizontal adder
//        val (x13,y13,z13) = horizAdder
//        println("horizAdder: " + x13 + ", " + y13 + ", " + z13)
        val vAdder: Point = (guideV * (height / yRes)).direction // calculating the vertical adder so we can navigate on the lens surface
        vertAdder.setPoint(vAdder) // defining the vertical adder
//        val (x15,y15,z15) = vertAdder
//        println("vertAdder: " + x15 + ", " + y15 + ", " + z15)

        val camDist: Double = ((width/2) / (Math.tan(0.6911504/2))) // calculating the best distance from the camera to the lens to avoid distortion. Using the logic behind 50mm lenses
//        val camPos: Point = middleLens + (unitLensVec*camDist).direction // calculating the final position of the camera in the scene
        val camPos: Point = middleLens + (!lensVector*camDist).direction // calculating the final position of the camera in the scene
//        val camPosCorrector = 200.0
//        val camPos2 = camPos + (!lensVector*camPosCorrector).direction
        cameraPos.setPoint(camPos) // setting the camera position
//        cameraPos.setPoint(camPos2) // setting the camera position

//        println("Cam dist from lens: " + camDist)



//        val (x,y,zVector) = cameraPos
//        println("camera pos: " + x + ", " + y + ", " + zVector)
//        val (x1,y1,z1) = middleLens
//        println("middle lens pos: " + x1 + ", " + y1 + ", " + z1)
//        val (x20,y20,z20) = p3
//        println("P3 pos: " + x20 + ", " + y20 + ", " + z20)
//        val (x2,y2,z2) = p4
//        println("P4 pos: " + x2 + ", " + y2 + ", " + z2)
//
//        val fromCam: Vector = !Vector(middleLens - cameraPos)
//        val (x3,y3,z3) = fromCam.direction
//        println("from Cam: " + x3 + ", " + y3 + ", " + z3)
//        val lensVec: Vector = !Vector(p4 - middleLens)
//        val (x4,y4,z4) = lensVec.direction
//        println("lens vec 1: " + x4 + ", " + y4 + ", " + z4)
//        val lensVec2: Vector = !Vector(p3 - middleLens)
//        val (x0,y0,z0) = lensVec2.direction
//        println("lens vec 2: " + x0 + ", " + y0 + ", " + z0)
//        val lensVec3: Vector = !Vector(p3 - p4)
//        val (x01,y01,z01) = lensVec3.direction
//        println("lens vec 3: " + x01 + ", " + y01 + ", " + z01)
//        val dotProd1 = (fromCam * lensVec)
//        val dotProd2 = (fromCam * lensVec2)
//        val dotProd3 = (fromCam * lensVec3)
//        println(dotProd1)
//        println(dotProd2)
//        println(dotProd3)
//        println(dotProd1 < 0.00001 && dotProd1 > -0.00001)
//        println(dotProd2 < 0.00001 && dotProd2 > -0.00001)
//        println(dotProd3 < 0.00001 && dotProd3 > -0.00001)
    }

    fun shootRays(): Unit {
//        val image: Image = this.getImage("floor1ref_sphere2sol")
//        var rayDot: Point
//        var ray: Vector
        for (laps in 1..numOfRaysPixel) {
//            val point0: Point = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
            print("\rIteration: " + laps + "/" + numOfRaysPixel)
            for (pixRows in pixels) {
                for (pixel in pixRows) {
//                    val randPoint: Point = Point.getRandomPoint(-midPix, midPix)
//                    val rayDot = point0 + (horizAdder * pixRows.indexOf(pixel).toFloat())
//                    rayDot.x += randPoint.x
//                    rayDot.zVector += randPoint.zVector
//                    val ray = !Vector(rayDot)
                    val upLeftDot: Point = point0 + (horizAdder * pixRows.indexOf(pixel).toDouble()) // this is the upper left point of the pixel we are iterating
                    upLeftDot.valAdd(horizAdder * getRandomDouble()) // adding a random fraction of the horizontal adder to the upper left dot of the pixel
                    upLeftDot.valAdd(vertAdder * getRandomDouble()) // adding a random fraction of the vertical adder to the upper left dot of the pixel
                    val ray: Vector = getRandomRay(upLeftDot) // this function returns a unit random ray within the boundaries of this pixel

//                    val (x4,y4,z4) = upLeftDot
//                    println("Random ray Dot: " + x4 + ", " + y4 + ", " + z4)
//                    val (x3,y3,z3) = ray.direction
//                    println("Random ray: " + x3 + ", " + y3 + ", " + z3)

                    val color = scene.trace(upLeftDot, ray, 0) // sending the ray and the camera position to the scene
//                    val color = scene.trace(cameraPos, ray, 0) // sending the ray and the camera position to the scene
                    pixel.addColor(color) // adding the calculated color to the pixel
                    +pixel // updating the counter of rays for this particular pixel
                }
//                point0 += vertAdder
                point0.valAdd(vertAdder) // adding to the point0, the vertical adder so we can start computing the next row of pixels
            }
            point0.setPoint(horizReset)
//            point0.setPoint(Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix))
//            point0 = Point(-(this.width/2) + midPix,this.dist,(this.height/2) - midPix)
        }
//        val image: Image2 = Image2(xRes, yRes, fileName)
//        val image: Image = Image()
        val image: Image2 = getImage(xRes, yRes, fileName)
        image.addPixels(numOfRaysPixel, pixels)
        saveImage(image)
//        image.checkFileExists("floorR_2spheres_1600x1200", xRes, yRes)
//        println("saving data to csv file.")
//        image.addPixels(fileName, pixels)
    }

    private fun getRandomRay(upperLeft: Point): Vector {
        val rayVector: Vector = Vector(upperLeft - cameraPos) // defining the ray from the camera position to the calculated random location of the pixel
        return !rayVector // returning the unitized vector of the calculated ray
    }




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

        val randObj: Random = Random()

//        fun render(xRes: Int, yRes: Int, fileName: String): Unit {
//            println("Rendering image")
//            val screen: BufferedImage = BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB) // creating the buffered image file
//            val comma: String = "," // creating the delimeter for reading
//
//            var countx = 0 // counter for the x coordinate of the screen
//            var county = 0 // counter for the y coordinate of the screen
//            var fileReader: BufferedReader? = null // creating a new Buffered reader
//            try {
//                fileReader = BufferedReader(FileReader("./" + fileName + ".csv"), 4096) // trying to open the file.
//                val colorArr = ArrayList<MyColor>() // creating an arraylist that will hold all the colors of a given pixel
//                fileReader.forEachLine { // each line in the csv file represents a pixel
//                    val strFloats: List<String> = it.split(comma) // getting a list of strings that holds the floats colors
//                    var color: MyColor = MyColor(-1f,-1f,-1f) // creating a blank new color with -1f values
//                    strFloats.forEach { // each string is a float that may be a red, green or blue color
//                        if (color.isFull()) { // this will run if the color object is filled with r, g, b values other than -1f
//                            colorArr.add(color) // adding the filled color to the collor array
//                            color = MyColor(-1f,-1f,-1f) // reset the color so we can fill it with other values
//                            color.add(it.toFloat()) // adding the next float color into the color object
//                        } else { // this will run in case the color object is not full. (there is at least one color that is -1f)
//                            if (it.toFloat() < -2f) {
//
//                            } else {
//                                color.add(it.toFloat()) // adding the next float color into the color object
//                            }
//                        }
//                    } // end of the colors of this pixel. But the color object is full with the last triplet
//                    if (color.isFull()) colorArr.add(color) // adding the filled color to the collor array
//                    val pixelColor: MyColor = getAverageColor(colorArr) // getting the average color given all the colors of this pixel
//
//
////                    val (r,g,b) = pixelColor
////                    println("---")
////                    println("Final red: " + r)
////                    println("Final green: " + g)
////                    println("Final blue: " + b)
////                    println("---")
//
//
////                    println("X: " + countx + "; Y: " + county)
//                    screen.setRGB(countx,county,pixelColor.getJavaColor()) // setting the correct pixel with the final color set to int
//                    if (countx < xRes - 1) countx++ // until the x coord is less than the X-resolution, increment it
//                    else { // if not, then set the x coord to 0 and increment the y coord
//                        countx = 0
//                        county++
//                    }
//                    colorArr.clear() // clearing all the data inside this array so we can use it again for another pixel
//                } // ends the computation for this particular pixel
//            } catch (fex: FileNotFoundException) { // this will run if the file does not exists
//                println("")
//                println("No csv file found. Exiting the render.")
//                return
//            } finally { // will run independently
//                if (fileReader != null) { // checking to see if we have a working reader
//                    fileReader.close() // closing the reader
//                }
//            }
//            val output: File = File("./" + fileName + ".png") // creating the actual image file
//            ImageIO.write(screen, "png", output) // writing the image file using the buffered image
//        }

        fun render(xRes: Int, yRes: Int, fileName: String): Unit {
            val image: Image2 = getImage(xRes, yRes, fileName)
            val screen: BufferedImage = BufferedImage(image.xRes, image.yRes, BufferedImage.TYPE_INT_RGB)
            var countx = 0
            var county = 0
            for (colorRows in image.pixels) {
                for (color in colorRows) {
                    val colorr = color.getJavaColor()
//                    val color = pixel.getFinalColor()
                    screen.setRGB(countx,county,colorr)
                    countx++
                }
                countx = 0
                county++
            }

            val output: File = File("./" + fileName + ".png")
            ImageIO.write(screen, "png", output)
        }

        fun getAverageColor(colorArr: ArrayList<MyColor>): MyColor {
            val numColors: Int = colorArr.size
            var reds: Double = 0.0
            var greens: Double = 0.0
            var blues: Double = 0.0
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

        fun getRandomDouble(): Double {
            return this.randObj.nextDouble()
        }

        fun getImage(xRes: Int, yRes: Int, fileName: String): Image2 {
            println("Loading image")
            var fin: FileInputStream? = null
            var ois: ObjectInputStream? = null
            var image = Image2(xRes, yRes, fileName)

            try {
                fin = FileInputStream("./" + fileName + ".ser")
                ois = ObjectInputStream(fin)
                val imageTemp = ois.readObject()
                if (imageTemp is Image2) image = imageTemp
                image.name = fileName
            } catch (fex: FileNotFoundException) {
                println("File not found. Creating a new one.")
                return Image2(xRes, yRes, fileName)
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

        fun saveImage(image: Image2) {
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
        }
    }
}