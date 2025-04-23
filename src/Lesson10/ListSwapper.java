package Lesson10;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListSwapper {
    private Random random = new Random();

    /** Permite injetar um Random “fake” no teste. */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Realiza `times` trocas aleatórias na lista.
     * Cada troca escolhe dois índices i,j em [0..list.size()) e faz swap.
     */
    public void swapMany(List<Integer> list, int times) {
        int size = list.size();
        for (int k = 0; k < times; k++) {
            int i = random.nextInt(size);
            int j = random.nextInt(size);
            Collections.swap(list, i, j);
        }
    }
}
