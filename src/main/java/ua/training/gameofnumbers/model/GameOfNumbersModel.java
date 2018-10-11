package ua.training.gameofnumbers.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Model that implements "game of numbers" ("guess the number") logic.
 */
public class GameOfNumbersModel {

    private final int targetNumber;
    private int actualLowerBound;
    private int actualUpperBound;

    private Map<Integer, NumberGroup> turns;

    /**
     * Creates and configures new model, including target number random generation.
     * @param initialMaximum upper bound for number to be guessed.
     */
    public GameOfNumbersModel(int initialMaximum) {
        actualLowerBound = 0;
        actualUpperBound = initialMaximum;
        // LinkedHashMap used to preserve order of turns
        turns = new LinkedHashMap<>();
        targetNumber = new Random().nextInt(actualUpperBound + 1);
    }

    /**
     * Compares input number with target one and made appropriate changes to model state.
     * @param number value to be compared with target number.
     * @return group for number (less, more or equal).
     */
    public NumberGroup acceptTurn(int number) {
        NumberGroup turnResult = evaluateNumberGroup(number);
        turns.put(number, turnResult);
        estimateActualBounds(number);
        return turnResult;
    }

    private NumberGroup evaluateNumberGroup(int number) {
        NumberGroup numberGroup = NumberGroup.UNKNOWN;
        switch (Integer.compare(targetNumber, number)) {
            case -1:
                numberGroup = NumberGroup.LESS; break;
            case 0:
                numberGroup = NumberGroup.EQUAL; break;
            case 1:
                numberGroup = NumberGroup.MORE; break;
            default:
                assert false;
        }
        return numberGroup;
    }

    private void estimateActualBounds(int number) {
        if (evaluateNumberGroup(number) == NumberGroup.MORE) {
            actualLowerBound = Math.max(actualLowerBound, number);
        }
        else if (evaluateNumberGroup(number) == NumberGroup.LESS) {
            actualUpperBound = Math.min(actualUpperBound, number);
        }

    }

    public int getActualLowerBound() {
        return actualLowerBound;
    }

    public int getActualUpperBound() {
        return actualUpperBound;
    }

    /**
     * Forms table of turns.
     * @return table of turns is string representation.
     */
    public String getTableOfTurns() {
        StringBuilder table = new StringBuilder("PREVIOUS TURNS");
        String separator = System.lineSeparator();
        table.append(separator)
             .append("Number --> Result")
             .append(separator);

        for (Map.Entry<Integer, NumberGroup> turn: turns.entrySet()) {
            table.append(turn.getKey())
                 .append(" --> ")
                 .append(turn.getValue())
                 .append(separator);
        }
        return table.toString();
    }

    /**
     *
     */
    public enum NumberGroup {
        MORE ("Target number is more than your one."),
        LESS ("Target number is less than your one."),
        EQUAL("Congratulations! You guessed the target number."),
        UNKNOWN("Unknown group");

        private final String message;

        NumberGroup(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
