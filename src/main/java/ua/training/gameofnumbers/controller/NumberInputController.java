package ua.training.gameofnumbers.controller;

import ua.training.gameofnumbers.model.GameOfNumbersModel;
import ua.training.gameofnumbers.view.ConsoleView;

import java.util.Objects;
import java.util.Scanner;

public class NumberInputController {

    private static final String NAN_INPUT ="Please, enter integer number!";
    private static final String OUT_OF_BOUNDS_INPUT ="Your number is out of bounds. Please, enter integer number in [{};{}]!";
    private static final String TURN_TIP ="Guess integer number in bounds: [{};{}] (inclusive)";

    private GameOfNumbersModel model;
    private ConsoleView view;
    private boolean keepListening = true;

    public NumberInputController(GameOfNumbersModel model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void makeTurn() {
        view.displayMessage(fillPlaceholders(TURN_TIP, model.getActualLowerBound(), model.getActualUpperBound()));
        view.displayMessage(model.getTableOfTurns());

        Scanner scanner = new Scanner(System.in);
        int enteredNumber;

        if (scanner.hasNextInt()) {
            enteredNumber = scanner.nextInt();
        } else {
            view.displayMessage(NAN_INPUT);
            return;
        }

        if (isInBounds(enteredNumber)) {
            interpretResult(model.acceptTurn(enteredNumber));
        } else {
            view.displayMessage(fillPlaceholders(OUT_OF_BOUNDS_INPUT, model.getActualLowerBound(), model.getActualUpperBound()));
        }
    }

    private boolean isInBounds(int number) {
        return (number >= model.getActualLowerBound())
                && (number <= model.getActualUpperBound());
    }

    private void interpretResult(GameOfNumbersModel.NumberGroup result) {
       view.displayMessage(result.getMessage());
       keepListening = !Objects.equals(result, GameOfNumbersModel.NumberGroup.EQUAL);
    }

    public boolean isKeepListening() {
        return keepListening;
    }

    private String fillPlaceholders(String template, Object... fillers) {
        String placeholder = "[{]}";
        String result = template;

        for (Object filler: fillers) {
            result = result.replaceFirst(placeholder, filler.toString());
        }

        return result;
    }


}
