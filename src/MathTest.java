import junit.framework.TestCase;
import junit.framework.*;

import java.util.*;

public class MathTest extends TestCase {
    static final double TOLERANCE = 0.05;

    public void testHypotenuse() {
        assertEquals(5.0, Math.hypot(3.0, 4.0), TOLERANCE);
    }

    public void testCoinFlips() {
        final long seed = 100L;
        final int total = 10;

        Random random1 = new Random(seed);
        List<Boolean> flips1 = new ArrayList<Boolean>();

        for(int i = 0; i < total; i++)
            flips1.add(random1.nextBoolean());

        Random random2 = new Random(seed);
        List<Boolean> flips2 = new ArrayList<Boolean>();

        for(int i = 0; i < total; i++)
            flips2.add(random2.nextBoolean());

        assertEquals(flips1, flips2);
    }
}
