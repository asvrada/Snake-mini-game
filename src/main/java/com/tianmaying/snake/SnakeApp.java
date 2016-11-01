package com.tianmaying.snake;

import javax.swing.*;

public class SnakeApp {
    public void init() {

        //创建游戏窗体
        JFrame window = new JFrame("贪吃蛇");

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