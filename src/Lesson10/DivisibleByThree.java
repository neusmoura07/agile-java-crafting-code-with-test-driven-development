package Lesson10;

import java.util.ArrayList;
import java.util.List;

public class DivisibleByThree {
    public static List<Integer> filterUsingModulo(int ... numbers) {
        List<Integer> result = new ArrayList<>();
        for(int n : numbers) {
            if(n % 3 == 0) {
                result.add(n);
            }
        }
        return result;
    }

    public static List<Integer> filterUsingDivision(int... numbers) {
        List<Integer> result = new ArrayList<>();
        for(int n : numbers) {
            int q = n / 3;
            if (q * 3 == n) {
                result.add(n);
            }
        }
        return result;
    }

}
