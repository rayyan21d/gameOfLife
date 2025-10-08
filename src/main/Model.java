package main;

/**
 * Model class stores and fetches core adventure data.
 * Responsibilities:
 * - Store current command being processed
 * - Store player state information
 * - Track game statistics
 * - Provide data to Controller for decision making
 */
public class Model {

    // Current command data
    private Command currentCommand;
    private String lastInput;

    // Game state data that Controller needs
    private int commandCount;
    private int invalidCommandCount;
    private boolean gameActive;

    // Player interaction tracking
    private String lastVerb;
    private String lastNoun;
    private String lastResult;

    public Model() {
        this.commandCount = 0;
        this.invalidCommandCount = 0;
        this.gameActive = true;
    }

    // Command Management

    public void setCurrentCommand(Command command) {
        this.currentCommand = command;
        if (command != null) {
            this.lastVerb = command.getVerb();
            this.lastNoun = command.getNoun();
            this.commandCount++;
        }
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public void setLastInput(String input) {
        this.lastInput = input;
    }

    public String getLastInput() {
        return lastInput;
    }

    // Added setData method that was being called in Controller
    public void setData(String input) {
        setLastInput(input);
    }

    // Game State Management

    public void incrementInvalidCommands() {
        this.invalidCommandCount++;
    }

    public int getCommandCount() {
        return commandCount;
    }

    public int getInvalidCommandCount() {
        return invalidCommandCount;
    }

    public void setGameActive(boolean active) {
        this.gameActive = active;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    // Result Tracking

    public void setLastResult(String result) {
        this.lastResult = result;
    }

    public String getLastResult() {
        return lastResult;
    }

    // Statistics

    public String getStatistics() {
        double successRate = commandCount == 0 ? 0 :
                ((commandCount - invalidCommandCount) * 100.0 / commandCount);

        return String.format(
                "Total Commands: %d | Invalid: %d | Success Rate: %.1f%%",
                commandCount, invalidCommandCount, successRate
        );
    }

    // Data Fetching for Controller

    /**
     * Get summary of last action for Controller to process
     */
    public String getLastActionSummary() {
        if (lastVerb == null) return "No action taken yet.";

        String summary = "Last action: " + lastVerb;
        if (lastNoun != null && !lastNoun.isEmpty()) {
            summary += " " + lastNoun;
        }
        return summary;
    }

    /**
     * Check if player should see help based on invalid attempts
     */
    public boolean shouldSuggestHelp() {
        return invalidCommandCount >= 3;
    }

    /**
     * Reset statistics for new game
     */
    public void reset() {
        this.commandCount = 0;
        this.invalidCommandCount = 0;
        this.currentCommand = null;
        this.lastInput = null;
        this.lastVerb = null;
        this.lastNoun = null;
        this.lastResult = null;
        this.gameActive = true;
    }
}