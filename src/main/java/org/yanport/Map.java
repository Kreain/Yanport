package org.yanport;

public class Map {

    private final int abscissaSize;
    private final int ordinateSize;

    public Map(int abscissaSize, int ordinateSize) {
        this.abscissaSize = abscissaSize;
        this.ordinateSize = ordinateSize;
    }

    public int getAbscissaSize() {
        return (this.abscissaSize);
    }
    public int getOrdinateSize() {
        return (this.ordinateSize);
    }
}
