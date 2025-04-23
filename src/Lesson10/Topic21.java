package Lesson10;

public class Topic21 {
    public static void swap(int[] xy) {
        xy[0] = xy[0] ^ xy[1];
        xy[1] = xy[0] ^ xy[1];
        xy[0] = xy[0] ^ xy[1];
    }
}
