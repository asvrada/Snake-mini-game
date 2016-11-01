package com.tianmaying.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private Grid grid;
    private GameView gameView;

    GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            grid.changeDirection(Direction.UP);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            grid.changeDirection(Direction.DOWN);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            grid.changeDirection(Direction.RIGHT);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            grid.changeDirection(Direction.LEFT);
        }

        grid.nextRound();

        gameView.draw();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}