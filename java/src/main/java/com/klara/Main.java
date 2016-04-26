package com.klara;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * @author Klara
 * @since 2016-04-21
 */
public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static final String FILE_FOLDER = "D:\\dev\\mars-rover\\src\\main\\resources\\";
    public static final String FILE_NAME = "test1.txt";

    public static void main(String[] args) {
        final FieldInfo fieldInfo = new FieldInfo();
        final List<Rover> rovers = new ArrayList<>();
        final List<List<Command>> commands = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(FILE_FOLDER + FILE_NAME))) {
            final AtomicBoolean infoFilled = new AtomicBoolean(false);
            final AtomicBoolean newRover = new AtomicBoolean(true);

            stream.forEach(s -> {
                if (!infoFilled.get()) {
                    String[] coords = s.split(" ");
                    fieldInfo.setMaxX(Integer.parseInt(coords[0]));
                    fieldInfo.setMaxY(Integer.parseInt(coords[1]));
                    infoFilled.set(true);
                } else {
                    if (newRover.get()) {
                        String[] info = s.split(" ");
                        Rover rover = new Rover();
                        rover.setStartX(Integer.parseInt(info[0]));
                        rover.setCurrentX(rover.getStartX());

                        rover.setStartY(Integer.parseInt(info[1]));
                        rover.setCurrentY(rover.getStartY());

                        rover.setStartDirection(Direction.get(info[2].charAt(0)));
                        rover.setCurrentDirection(rover.getStartDirection());
                        rovers.add(rover);
                        newRover.set(false);
                    } else {
                        List<Command> currentRoverCommands = new ArrayList<>();
                        for (int i = 0; i < s.length(); i++) {
                            currentRoverCommands.add(Command.get(s.charAt(i)));
                        }
                        commands.add(currentRoverCommands);
                        newRover.set(true);
                    }
                }
            });

        } catch (IOException e) {
            logger.error("Cannot open file", e);
        }

        for (int i = 0; i < rovers.size(); i++) {
            RoverCoordinator.processCommands(fieldInfo, rovers.get(i), commands.get(i));
        }

        System.out.println(rovers.size());
    }
}
