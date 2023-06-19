package org.yanport;

public class Coordinates {

    private int abscissa_x;
    private int ordinate_y;

    public Coordinates(int abscissa_x, int ordinate_y) {
        this.abscissa_x = abscissa_x;
        this.ordinate_y = ordinate_y;
    }

    public void setAbscissa_x(int abscissa_x) {
        this.abscissa_x = abscissa_x;
    }
    public void setOrdinate_y(int ordinate_y) {
        this.ordinate_y = ordinate_y;
    }
    public int getAbscissa_x() {
        return (this.abscissa_x);
    }
    public int getOrdinate_y() {
        return (this.ordinate_y);
    }
}
