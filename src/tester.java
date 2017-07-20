import java.util.ArrayList;

/**
 * Created by JonathanOliveira on 20/07/17.
 */
public class tester {

    Sphere sphere = new Sphere(new Point(0.0,0.0,0.0), 5);
    Sky sky = new Sky();

    ArrayList<Objects> objects = new ArrayList<>();

    void adder() {
        objects.add(sphere);
        objects.add(sky);
    }
}
