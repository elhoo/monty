package lh.monty.hall.simulation;

import org.hibernate.validator.constraints.Range;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;

@ShellComponent
public class SimulationCommands {
    @ShellMethod("Run the simulation")
    public Table run(
            @ShellOption(help = "the number of iterations", defaultValue = "1000") @Range(min = 1) int iterations) {
        Simulator simulator = new Simulator(iterations);
        simulator.run();
        String[][] data = new String[2][3];
        TableModel model = new ArrayTableModel(data);
        TableBuilder tableBuilder = new TableBuilder(model);

        int winsChanging = simulator.getWinsChanging();
        int winsStaying = simulator.getWinsStaying();
        int totWinningGames = winsChanging + winsStaying;

        int row = 0;
        data[row][0] = "Wins changing";
        data[row][1] = String.valueOf(winsChanging);
        data[row][2] = getPercentageString(winsChanging, totWinningGames);

        row++;
        data[row][0] = "Wins staying";
        data[row][1] = String.valueOf(winsStaying);
        data[row][2] = getPercentageString(winsStaying, totWinningGames);

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }

    private String getPercentageString(float wins, int totWinningGames) {
        return String.format("%.2f%%", 100 * (wins / totWinningGames));
    }


}
