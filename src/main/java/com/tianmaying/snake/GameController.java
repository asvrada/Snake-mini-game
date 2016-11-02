package com.tianmaying.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable, KeyListener {
    private Grid grid;
    private GameView gameView;
    private boolean running;
    private boolean isPause;
    private boolean allowChange;
    private boolean isQuit;

    GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.running = true;
        this.isPause = false;
        this.allowChange = true;
        this.isQuit = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (allowChange) {
                    allowChange = false;
                    grid.changeDirection(Direction.UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (allowChange) {
                    allowChange = false;
                    grid.changeDirection(Direction.DOWN);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (allowChange) {
                    allowChange = false;
                    grid.changeDirection(Direction.RIGHT);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (allowChange) {
                    allowChange = false;
                    grid.changeDirection(Direction.LEFT);
                }
                break;
            // restart the game
            case KeyEvent.VK_ENTER:
                if (isPause || !running) {
                    grid.init();
                    isPause = false;
                    allowChange = true;
                    running = true;
                }
                break;
            // pause the game
            case KeyEvent.VK_SPACE:
                isPause = !isPause;
                if (isPause) {
                    allowChange = false;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                isQuit = true;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        // main game loop
        while (!isQuit) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (running && !isQuit) {
                try {
                    Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Pause game by skipping the nextRound
                if (isPause) {
                    continue;
                }
                allowChange = true;

                // 进入游戏下一步
                // 如果结束，则退出游戏
                if (!grid.nextRound()) {
                    running = false;
                    gameView.showGameOverMessage();
                } else {
                    // 如果继续，则绘制新的游戏页面
                    gameView.draw();
                }
            }

            running = false;
        }
    }
}