package com.tianmaying.snake;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    private final int directionCode;

    Direction(int directionCode) {
        this.directionCode = directionCode;
    }

    public int directionCode() {
        return directionCode;
    }

}