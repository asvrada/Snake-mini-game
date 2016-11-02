package com.tianmaying.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements Runnable, KeyListener {
    private Grid grid;
    private GameView gameView;
    private boolean running;
    private boolean isPause;

    private final Object syncThread = new Object();

    GameController(Grid grid, GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        init();
    }

    private void init() {
        this.running = true;
        this.isPause = true;
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
            // restart the game
            case KeyEvent.VK_ENTER:
                if (isPause || !running) {
                    grid.init();
                    init();
                    threadRun();
                    gameView.draw();
                }
                break;
            // pause the game
            case KeyEvent.VK_SPACE:
                isPause = !isPause;
                // wake up
                if (!isPause) {
                    threadRun();
                }
                break;
            default:
                break;
        }
    }

    // put current thread into sleep
    private void threadWait() throws InterruptedException {
        synchronized (syncThread) {
            syncThread.wait();
        }
    }

    // wake up current thread
    private void threadRun() {
        synchronized (syncThread) {
            syncThread.notify();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        try {
            // main game loop
            while (running) {
                // Pause game
                if (isPause) {
                    threadWait();
                }

                // 进入游戏下一步
                // 如果结束，则退出游戏
                if (!grid.nextRound()) {
                    running = false;
                    gameView.showGameOverMessage();
                    threadWait();
                } else {
                    // 如果继续，则绘制新的游戏页面
                    gameView.draw();
                }

                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            }

            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}