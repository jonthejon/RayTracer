/**
 * Created by JonathanOliveira on 02/08/17.
 */
public class MainClass {

    public static void main(String[] args) {
        Lens lens = new Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200222");
        lens.shootRays();
    }
}
