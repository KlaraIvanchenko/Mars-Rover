package com.klara;

import static com.klara.Command.*;
import static com.klara.Direction.*;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Klara
 * @since 2016-04-21
 */
public class RoverCoordinator {

    private static Logger logger = Logger.getLogger(RoverCoordinator.class);

    public static void processCommands(FieldInfo fieldInfo, Rover rover, List<Command> commands) {
        logger.debug("Rover start info: x: " + rover.getStartX() + ", y: " + rover.getStartY() + ", direction: " + rover.getStartDirection());

        for (Command command : commands) {
            if (command == MOVE) {
                move(rover);
            } else {
                rotate(rover, command);
            }
        }
        logger.debug("Rover finish info: x: " + rover.getCurrentX() + ", y: " + rover.getCurrentY() + ", direction: " + rover.getCurrentDirection());
    }

    private static void move(Rover rover) {
        if (rover.getCurrentDirection() == NORTH) {
            rover.setCurrentY(rover.getCurrentY() + 1);
        } else if (rover.getCurrentDirection() == SOUTH) {
            rover.setCurrentY(rover.getCurrentY() - 1);
        } else if (rover.getCurrentDirection() == WEST) {
            rover.setCurrentX(rover.getCurrentX() - 1);
        } else if (rover.getCurrentDirection() == EAST) {
            rover.setCurrentX(rover.getCurrentX() + 1);
        }
    }

    private static void rotate(Rover rover, Command rotateCommand) {
        if (rotateCommand == RIGHT) {
            if (rover.getCurrentDirection() == NORTH) {
                rover.setCurrentDirection(EAST);
            } else if (rover.getCurrentDirection() == EAST) {
                rover.setCurrentDirection(SOUTH);
            } else if (rover.getCurrentDirection() == SOUTH) {
                rover.setCurrentDirection(WEST);
            } else if (rover.getCurrentDirection() == WEST) {
                rover.setCurrentDirection(NORTH);
            }
        } else if (rotateCommand == LEFT) {
            if (rover.getCurrentDirection() == NORTH) {
                rover.setCurrentDirection(WEST);
            } else if (rover.getCurrentDirection() == WEST) {
                rover.setCurrentDirection(SOUTH);
            } else if (rover.getCurrentDirection() == SOUTH) {
                rover.setCurrentDirection(EAST);
            } else if (rover.getCurrentDirection() == EAST) {
                rover.setCurrentDirection(NORTH);
            }
        }
    }
}
