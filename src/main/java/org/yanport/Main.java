package org.yanport;

import org.apache.commons.cli.*;

import java.util.Optional;

public class Main {

    private static Vacuum initiateFromFile(String fileName) {
        return (null);
    }

    private static Vacuum initiateFromCommandLine(String[] args, Options arguments) throws Exception {
        final Map map;
        final Coordinates coordinates;
        final Optional<Orientation> orientation;

        CommandLineParser parser = new DefaultParser();
        CommandLine command;
        command = parser.parse(arguments, args);

        String[] size = command.getOptionValue("s").split(":");
        String[] coord = command.getOptionValue("c").split(":");

        map = new Map(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
        coordinates = new Coordinates(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        orientation = Orientation.getOrientationFromCharAttribute(command.getOptionValue("o"));

        if (orientation.isEmpty())
            throw new VacuumException("Unknown orientation");

        return (new Vacuum(map, coordinates, orientation.get(), command.getOptionValue("i")));
    }

    public static void main(String[] args) {
        final Vacuum vacuum;
        final Options fileArguments = new Options().addOption(Option.builder("f")
                .argName("file").hasArg().required(false).desc("Arguments as JSON file").build());
        final Options arguments = new Options();

        arguments.addOption(Option.builder("s")
                .argName("size").hasArg().required(true).desc("Size of the map as x:y").build());
        arguments.addOption(Option.builder("c")
                .argName("coordinates").hasArg().required(true).desc("Coordinates of vacuum as x:y").build());
        arguments.addOption(Option.builder("o")
                .argName("orientation").hasArg().required(true).desc("Orientation of start [N,E,S,W]").build());
        arguments.addOption(Option.builder("i")
                .argName("instructions").hasArg().required(true).desc("Instructions for vacuum [D, G, A] ie: DADAA").build());
        arguments.addOption(Option.builder("f")
                .argName("file").hasArg().required(false).desc("Arguments as JSON file").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine fileCommand;
        HelpFormatter helper = new HelpFormatter();

        try {
            fileCommand = parser.parse(fileArguments, args, true);

            if (fileCommand.hasOption("f"))
                vacuum = initiateFromFile(fileCommand.getOptionValue("file"));
            else
                vacuum = initiateFromCommandLine(args, arguments);

            assert vacuum != null;
            vacuum.launchVacuum();
            vacuum.displayCoordinates();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            helper.printHelp("Usage:", arguments);
            System.exit(0);
        }
    }
}
