package com.tianmaying.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeApp {
    public void init() {
        Grid grid = new Grid(20, 20);
        GameView gameView = null;

        //创建游戏窗体
        JFrame window = new JFrame("贪吃蛇");

        Container container = window.getContentPane();

        gameView = new GameView(grid);
        gameView.init();

        gameView.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
        container.add(gameView.getCanvas(), BorderLayout.CENTER);

        // 画出棋盘和贪吃蛇
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SnakeApp snakeApp = new SnakeApp();
        snakeApp.init();
    }
}