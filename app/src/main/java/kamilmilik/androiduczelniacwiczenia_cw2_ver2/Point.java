package kamilmilik.androiduczelniacwiczenia_cw2_ver2;

/**
 * Created by kamil on 09.01.2018.
 */

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}