package lh.monty.hall.simulation;

public class Boxes {
    private final int moneyBox;

    public Boxes(int moneyBox) {
        this.moneyBox = moneyBox;
    }

    public boolean priceBehind(int boxNumber) {
        return boxNumber == moneyBox;
    }
}
