package xyz.noobjeff.snake;

public class Grid {
    public final boolean[][] status;

    private Snake snake;
    private Node food;
    private Direction snakeDirection = Direction.LEFT;

    private final int width;
    private final int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;

        status = new boolean[width][height];

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
        for (Node each : snake.getBody()) {
            status[each.getX()][each.getY()] = true;
        }

        return snake;
    }

    public void createFood() {

    }
}