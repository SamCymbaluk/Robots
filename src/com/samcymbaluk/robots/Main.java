package com.samcymbaluk.robots;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Command line wrapper for {@link RobotSimulation}
 */
public class Main {

    private static final String[] ARG_LIST = {"-r","-m"};
    private static RobotSimulation sim;

    public static void main(String[] clArgs) {
        Map<String, String> args;
        try {
            args = parseArgs(clArgs);
        } catch (Exception ex) {
            System.err.println("Unable to parse arguments. Please consult documentation");
            return;
        }

        String moves = args.get("-m");
        int robots = args.containsKey("-r") ? Integer.parseInt(args.get("-r")) : 1;
        if (moves == null) {
            System.err.println("Missing required argument -m");
            return;
        }

        sim = new RobotSimulation(robots, moves);
        commandListener();
    }

    private static void commandListener() {
        Scanner input = new Scanner(System.in);
        while (true) {
            if(!commandExecutor(input.nextLine())) {
                System.err.println("Invalid command or arguments");
            }
        }
    }

    /**
     * @param line
     * @return <code>false</code> if command or arguments are invalid
     */
    private static boolean commandExecutor(String line) {
        try {
            String command = line.split(" ")[0].toLowerCase();

             switch (command) {
                case "simulate":
                    sim.runSimulation();
                    System.out.println("Simulation complete");
                    return true;
                case "step":
                    sim.step();
                    return true;
                 case "robots":
                     System.out.println("The robots are at the following locations:");
                     System.out.println(sim.getRobots());
                     return true;
                 case "presents":
                     System.out.println(sim.getPresentsDelivered() + " presents have been delivered");
                     return true;
                 case "threshold":
                     int threshold = Integer.parseInt(line.split(" ")[1]);
                     int amount = sim.housesAboveThreshold(threshold);
                     System.out.printf("There %s %d house%s with at least %d present%s\n",
                             amount == 1 ? "is" : "are",
                             amount,
                             amount == 1 ? "" : "s",
                             threshold,
                             threshold == 1 ? "" : "s");
                     return true;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            return false;
        }
        return false;
    }



    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> parsedArgs = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            for (String arg : ARG_LIST) {
                if (args[i].equalsIgnoreCase(arg)) {
                    parsedArgs.put(args[i], args[i+1]);
                }
            }
        }
        return parsedArgs;
    }
}
