package com.samcymbaluk.robots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Simulates a universe full of houses where robots move around delivering presents to houses.
 */
public class RobotSimulation {

    private String moves;
    private List<House> robots = new ArrayList<>();

    private int turn = 0;
    private Map<House, Integer> presents = new HashMap<>();

    /**
     * Constructs a new simulation that consists of 1 robot at the origin.
     * @param moves A String of moves that will be executed sequentially. Valid moves are <code>^ V < ></code>
     */
    public RobotSimulation(String moves) {
        this.moves = moves;
        robots.add(House.ORIGIN);
    }

    /**
     * Constructs a new simulation that consists of a given number of robots at the origin.
     * @param robots The number of robots to simulate
     * @param moves A String of moves that will be executed sequentially. Valid moves are <code>^ V < ></code>
     */
    public RobotSimulation(int robots, String moves) {
        this.moves = moves;
        for (int i = 0; i < robots; i++) {
            this.robots.add(House.ORIGIN);
        }
    }

    /**
     * Runs the entire sequence of moves given when the simulation was constructed.
     */
    public void runSimulation() {
        while (step()) {}
    }

    /**
     * Steps through a single move
     * @return <code>true</code> if the move occurred; <code>false</code> if there are not more moves in the move sequence.
     */
    public boolean step() {
        if (turn < moves.length()) {
            House robot = robots.get(turn % robots.size());
            char move = moves.charAt(turn);
            House rel = robot.getRelative(move);

            //Deliver present
            if (!robots.contains(rel)) {
                 if (presents.containsKey(rel)) {
                     presents.put(rel, presents.get(rel) + 1);
                 } else {
                     presents.put(rel, 1);
                 }
            }

            robots.set(turn % robots.size(), rel);
            System.out.println("Robot #" + ((turn % robots.size()) + 1) + ": " + robot + " -> " + rel);

            turn++;
            return true;
        }
        return false;
    }

    /**
     * @return A list of the {@link com.samcymbaluk.robots.House} each robot is currently at
     */
    public List<House> getRobots() {
        return robots;
    }

    /**
     * @return The total amount of presents delivered
     */
    public int getPresentsDelivered() {
        int amount = 0;
        for (int i : presents.values()) {
            amount += i;
        }
        return amount;
    }

    /**
     * @param threshold
     * @return The number of houses that have had a number of presents delivered greater than or equal to the threshold
     */
    public int housesAboveThreshold(int threshold) {
        int amount = 0;
        for (int i : presents.values()) {
            if (i >= threshold) amount++;
        }
        return amount;
    }

}
