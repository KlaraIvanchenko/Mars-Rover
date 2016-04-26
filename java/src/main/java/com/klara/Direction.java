package com.klara;

/**
 * @author Klara
 * @since 2016-04-21
 */
public enum Direction {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W')
    ;
    private Character direction;

    Direction(Character direction) {
        this.direction = direction;
    }

    public static Direction get(Character c) {
        for (Direction direction : Direction.values()) {
            if (direction.getDirection().equals(c)) {
                return direction;
            }
        }
        return NORTH;
    }

    public Character getDirection() {
        return direction;
    }
}
