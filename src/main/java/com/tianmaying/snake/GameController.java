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

        switch (keyCode) {
            case KeyEvent.VK_UP:
                grid.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                grid.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                grid.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                grid.changeDirection(Direction.LEFT);
                break;
            default:
                return;
        }

        assert grid.nextRound() : "FUUUUUCCCCCCCCCCCK";
        gameView.draw();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}