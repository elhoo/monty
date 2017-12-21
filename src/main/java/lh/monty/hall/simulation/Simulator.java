package lh.monty.hall.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Simulator {
    private static final Logger LOG = LoggerFactory.getLogger(Simulator.class);

    private final int NUMBER_OF_BOXES = 3;

    private final SecureRandom secureRandom = new SecureRandom();

    private final int iterations;
    private int winsStaying = 0;
    private int winsChanging = 0;

    public Simulator(int iterations) {
        this.iterations = iterations;
    }

    public void run() {
        LOG.debug("Starting the simulation with {} iterations", iterations);

        for (int i = 0; i < iterations; i++) {
            int moneyBox = secureRandom.nextInt(NUMBER_OF_BOXES);

            Boxes boxes = new Boxes(moneyBox);
            int firstBox = secureRandom.nextInt(NUMBER_OF_BOXES);
            int shownBox = getNextBox(firstBox, moneyBox);

            boolean changeBox = secureRandom.nextBoolean();

            if (changeBox) {
                int secondBox = getNextBox(firstBox, shownBox);
                if (boxes.priceBehind(secondBox)) {
                    winsChanging++;
                }
            } else {
                if (boxes.priceBehind(firstBox)) {
                    winsStaying++;
                }
            }
        }
    }

    /**
     * @return a new random box number that is not one of <i>notInBox</i>
     */
    private int getNextBox(Integer... notInBox) {
        List<Integer> list = Arrays.asList(notInBox);
        int nextBox;
        do {
            nextBox = secureRandom.nextInt(NUMBER_OF_BOXES);
        } while (list.contains(nextBox));
        return nextBox;
    }

    public int getWinsStaying() {
        return winsStaying;
    }

    public int getWinsChanging() {
        return winsChanging;
    }
}
