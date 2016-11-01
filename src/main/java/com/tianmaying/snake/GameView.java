package com.tianmaying.snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jeff on 01/11/2016.
 */
public class GameView {
    private final Grid grid;

    public GameView(Grid grid) {
        this.grid = grid;
    }

    public void draw(Graphics graphics) {
        drawGridBackground(graphics);
        drawSnake(graphics, grid.getSnake());
        drawFood(graphics, grid.getFood());
    }

    public void drawSnake(Graphics graphics, Snake snake) {
    }

    public void drawFood(Graphics graphics, Node squareArea) {
    }

    public void drawGridBackground(Graphics graphics) {
    }

    private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1);
    }


    private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(squareArea.getX() * size, squareArea.getY() * size, size, size);
    }
}
