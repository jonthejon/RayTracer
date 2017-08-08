import java.awt.Color
import java.io.*
import java.text.DecimalFormat

class Image() {

    private val comma: String = ","
    private val newLine: String = "\n"
    private var line: String? = null

    fun checkFileExists(fileName: String, xRes: Int, yRes: Int) {
        var fileReader: BufferedReader? = null
        var fileWriter: FileWriter? = null
        val dummy = "-3.0"
        try {
            fileReader = BufferedReader(FileReader("./" + fileName + ".csv"))
        } catch (fex: FileNotFoundException) {
            println("")
            println("No csv file found. Creating a new one.")
            fileWriter = FileWriter("./" + fileName + ".csv")
            for (i in 0..((xRes * yRes)-1)) {
//                println(i)
                fileWriter.append(dummy)
                fileWriter.append(comma)
                fileWriter.append(dummy)
                fileWriter.append(comma)
                fileWriter.append(dummy)
                fileWriter.append(newLine)
            }
        } finally {
            if (fileReader != null) {
                fileReader.close()
            }
            if (fileWriter != null) {
                try {
                    fileWriter.flush()
                    fileWriter.close()
                } catch (ioex: IOException) {
                    println("Unable to close FileInputStream.")
                    ioex.printStackTrace()
                }
            }
        }
    }

    fun addPixels(fileName: String, colors: Array<Array<Pixel>>) {

//        println(colors[0][0].colorArr.size)
//        var fileWriter: FileWriter? = null
        var fileReader: BufferedReader? = null
        var fileWriter: BufferedWriter? = null
//        val lineArr = ArrayList<String>()
        val nf = DecimalFormat("#.####")

        try {
//            fileReader = BufferedReader(FileReader("./" + fileName + ".csv"))
            fileReader = BufferedReader(FileReader("./" + fileName + ".csv"), 4096)
            fileWriter = BufferedWriter(FileWriter("./" + fileName + "_blend" + ".csv"), 4096)
//            line = fileReader.readLine()
//            println(line)
            line = fileReader.readLine()
//            println(line)
//            line = fileReader.readLine()
//            println(line)
//            line = fileReader.readLine()
//            println(line)
//            line = fileReader.readLine()
//            println(line)
//            line = fileReader.readLine()
//            println(line)
            while (line != null) {
//                println(line)
                for (pixRows in colors) {
//                    println("Ys: " + pixRows.size)
                    for (pixel in pixRows) {
//                        println("Xs: " + pixel.colorArr.size)
                        val tempNum = pixel.colorArr.size
//                        println(tempNum)
                        for (c in 0..tempNum - 1) {
                            val (r, g, b) = pixel.colorArr[c]
                            fileWriter.append(nf.format(r))
                            fileWriter.append(comma)
                            fileWriter.append(nf.format(g))
                            fileWriter.append(comma)
                            fileWriter.append(nf.format(b))
                            fileWriter.append(comma)
                        }
                        fileWriter.append(line)
                        fileWriter.append(newLine)
                        line = fileReader.readLine()
                    }
                }
            }
//            for (l in fileReader.readLine()) {
//                println(l)
//            }
        } catch (ex: Exception) {
            println("Something went wrong during writing to file.")
            ex.printStackTrace()
        } finally {
            if (fileReader != null) {
                fileReader.close()
            }
            if (fileWriter != null) {
                try {
                    fileWriter.flush()
                    fileWriter.close()
                } catch (ioex: IOException) {
                    println("Unable to close FileInputStream.")
                    ioex.printStackTrace()
                }
            }
        }
    }

/*        try {
//            fileWriter = FileWriter("./" + fileName + ".csv")
            var y = 0
            var x = 0
            var counter = 0
            val nf = DecimalFormat("#.####")
            for (colRows in colors) {
                for (color in colRows) {
                    if (lineArr.size > 0) {
                        fileWriter.append(lineArr[counter])
                        fileWriter.append(comma)
                    }
                    val tempNum = color.colorArr.size
                    for (c in 0..tempNum-1) {
                        val (r, g, b) = color.colorArr[c]
                        fileWriter.append(nf.format(r))
                        fileWriter.append(comma)
                        fileWriter.append(nf.format(g))
                        fileWriter.append(comma)
                        fileWriter.append(nf.format(b))
                        if (c < tempNum - 1) fileWriter.append(comma)
                    }
                    fileWriter.append(newLine)
                    x++
                    counter++
                }
                x = 0
                y++
            }
        }
        catch (ex: Exception) {
            println("Something went wrong during writing to file.")
            ex.printStackTrace()
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.flush()
                    fileWriter.close()
                } catch (ioex: IOException) {
                    println("Unable to close FileInputStream.")
                    ioex.printStackTrace()
                }
            }
        }
    }*/
}