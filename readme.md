# Ray Tracer

This is a Ray Tracer written in Kotlin. It is good to notice that there are no external libraries being used in this project, so all functionalities are developed from scratch using Kotlin and some Java Collection classes. This Ray Tracer is still in development and more functionalities will be added in the following days/weeks.

Here are some cool pics you can generate so far with this tracer:

![Spheres with reflection](https://github.com/jonthejon/RayTracer/blob/master/2%20spheres%20refl.png)
![Shadows](https://github.com/jonthejon/RayTracer/blob/master/wallsTest%20copy.png)

### Prerequisites

Make sure you have a Java Virtual Machine up and running on your machine if you want to run this software, since Kotlin targets the standard JVM and uses a bunch of Java features.

[KOTLIN](https://kotlinlang.org/) - start here if you want to learn more about kotlin.

### Developing

If you want to help or if you're just in the mood to doodle around with the code, I suggest downloading the IntelliJ IDEA. It's an IDE from the guys at JetBrain, the same company that created Kotlin. So you can expect a mature development ecosystem for Kotlin.

[INTELLIJ](https://www.jetbrains.com/idea/) - download the IntelliJ IDEA here.

### Rendering

You can define the objects that will compose the scene inside the Scene.kt class.

```
val sphere: Sphere = Sphere(Point(-13.0, 0.0, 80.0), 10.0, Material(Point(0.96, 0.30,0.20), 1.0, 0.0))
val sphere2: Sphere = Sphere(Point(30.0, -3.0, 97.0), 7.0, Material(Point(0.9, 0.8,0.10), 1.0, 0.0))
val cube: Composition = Composition(Point(-50.0,-10.0,70.0), Vector(Point(1.0,0.0,0.0)), Vector(Point(0.0,1.0,0.0)), Vector(Point(0.0,0.0,1.0)))
val floor: Floor = Floor(Point(0.0, -10.0, 0.0), Vector(Point(0.0,1.0,0.0)), Material(Point(0.5,0.5,0.5), 1.0, 0.0))
```

You can define the camera settings inside the Main.kt class. To run the Tracer, just call the main method of the Main.kt class.
The rendered image will be saved as an .png file inside your root folder.

```
public fun main (args: Array<String>) {
    val xRes: Int = 750;
    val yRes: Int = 500;
    var lens: Lens? = null
    for (count in 1..1) {
        println(count)
        lens = Lens(15.0, xRes, yRes, Point(20.0,0.0,85.0), Vector(Point(0.0,0.35,1.0)), 400.0, 1, "wallsTest")
        lens.shootRays()
        lens = null
    }
Lens.render(xRes, yRes,"wallsTest")
```

## Libraries

No external libraries have been used in this project. All classes are 100% Kotlin code written from scratch. 

## Acknowledgments

* special thanks to [Tim Babb](https://github.com/trbabb) for sharing his deep knowledge on ray tracing. 
* [Daniel Reina](https://github.com/dgrcode) for pairing a lot with me on this.
