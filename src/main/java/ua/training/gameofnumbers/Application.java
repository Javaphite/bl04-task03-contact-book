package ua.training.gameofnumbers;

import ua.training.gameofnumbers.controller.NumberInputController;
import ua.training.gameofnumbers.model.GameOfNumbersModel;
import ua.training.gameofnumbers.view.ConsoleView;

import java.io.IOException;

public final class Application {

    public static void main(String[] args) throws IOException {
        GameOfNumbersModel model = new GameOfNumbersModel(100);
        ConsoleView view = new ConsoleView();
        NumberInputController controller = new NumberInputController(model, view);

        while (controller.isKeepListening()) {
            controller.makeTurn();
        }
    }
}
