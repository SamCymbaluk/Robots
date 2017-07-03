package com.samcymbaluk.robots;

public class House {

    public static final House ORIGIN = new House(0,0);

    private final int x;
    private final int y;

    /**
     * Constructs an immutable position vector in the 2D plane
     * @param x
     * @param y
     */
    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * @param dir Valid chars are <code>^ V < ></code>
     * @return The {@link House} that is relative in the direction specified
     */
    public House getRelative(char dir) {
        switch (dir) {
            case '^':
                return new House(x, y + 1);
            case 'V':
                return new House(x, y - 1);
            case '>':
                return new House(x + 1, y);
            case '<':
                return new House(x - 1, y);
        }
        return null;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        return 53 * x * y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof House) {
            House o = (House) obj;
            return o.getX() == x && o.getY() == y;
        }
        return false;
    }
}
