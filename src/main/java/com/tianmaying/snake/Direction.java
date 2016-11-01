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

    /**
     * 判断方向改变是否有效，例如从向上变为向下为无效，从向上变为向左为有效
     * @param direction where to go next
     * @return can you go this way?
     */
    public boolean compatibleWith(Direction direction) {
        return Math.abs(direction.directionCode - directionCode) != 2;
    }
}