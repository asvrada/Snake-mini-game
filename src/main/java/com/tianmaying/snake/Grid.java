package com.tianmaying.snake;

import java.util.Arrays;
import java.util.Random;

public class Grid {
    private static Random r = new Random();

    public final boolean[][] status;

    private Snake snake;
    private Node food;
    private Direction snakeDirection = Direction.LEFT;
    private Direction prevDirection = Direction.LEFT;

    private final int width;
    private final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        status = new boolean[width][height];

        init();
    }

    public void init() {
        // clear grid
        for (int lop = 0; lop < width; lop++) {
            Arrays.fill(status[lop], false);
        }

        snakeDirection = Direction.LEFT;
        prevDirection = Direction.LEFT;
        initSnake();
        createFood();
    }

    public Snake getSnake() {
        return snake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Node getFood() {
        return food;
    }

    public Snake initSnake() {
        snake = new Snake();

        // Create snake
        int x = width / 2;
        int y = height / 2;
        for (int lop = 0; lop < 5; lop++) {
            snake.addTail(new Node(x, y));
            x += 1;
        }

        // Update grid status
        snake.getBody().forEach(this::occupy);

        return snake;
    }

    public Node createFood() {
        int x;
        int y;

        // 使用Random设置x和y
        do {
            x = r.nextInt(width);
            y = r.nextInt(height);
        } while (!validPosition(new Node(x, y)));

        food = new Node(x, y);
        return food;
    }

    public boolean nextRound() {
        Node deletedTail = snake.move(snakeDirection);
        prevDirection = snakeDirection;

        // Head is NOT in valid position
        // Game Over
        if (!validPosition(snake.getHead())) {
            return false;
        }

        // Update head to grid
        occupy(snake.getHead());

        // if food is eaten
        if (isFood(snake.getHead())) {
            snake.addTail(deletedTail);
            createFood();
        } else {
            // no food eaten, delete tail from grid
            dispose(deletedTail);
        }

        return true;
    }

    public boolean validPosition(Node area) {
        int x = area.getX(), y = area.getY();
        return x >= 0 && x < width && y >= 0 && y < height && !status[x][y];
    }

    private void dispose(Node node) {
        status[node.getX()][node.getY()] = false;
    }

    private void occupy(Node node) {
        status[node.getX()][node.getY()] = true;
    }

    public boolean isFood(Node area) {
        int x = area.getX(), y = area.getY();
        return x == food.getX() && y == food.getY();
    }

    public void changeDirection(Direction newDirection) {
        if (prevDirection.compatibleWith(newDirection)) {
            snakeDirection = newDirection;
        }
    }
}