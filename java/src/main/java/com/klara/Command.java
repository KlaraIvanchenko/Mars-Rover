package com.klara;

/**
 * @author Klara
 * @since 2016-04-21
 */
public enum Command {

    LEFT('L'),
    RIGHT('R'),
    MOVE('M'),
    ;
    private Character command;

    Command(Character command) {
        this.command = command;
    }

    public static Command get(Character c) {
        for (Command command : Command.values()) {
            if (command.getCommand().equals(c)) {
                return command;
            }
        }
        return MOVE;
    }

    public Character getCommand() {
        return command;
    }
}
