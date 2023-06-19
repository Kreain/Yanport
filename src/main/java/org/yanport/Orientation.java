package org.yanport;

import java.util.Arrays;
import java.util.Optional;

public enum Orientation {
    NORTH(0, 1, 0, 'N'),
    EAST(1, 0, 1, 'E'),
    SOUTH(0, -1, 2, 'S'),
    WEST(-1, 0, 3, 'W');

    private final int abscissaModifier;
    private final int ordinateModifier;
    private final int index;
    private final char charAttribute;

    Orientation(int abscissaModifier, int ordinateModifier, int index, char charAttribute) {
        this.abscissaModifier = abscissaModifier;
        this.ordinateModifier = ordinateModifier;
        this.index = index;
        this.charAttribute = charAttribute;
    }

    public int getAbscissaModifier() {
        return (this.abscissaModifier);
    }
    public int getOrdinateModifier() {
        return (this.ordinateModifier);
    }
    public int getIndex() {
        return (this.index);
    }
    public char getCharAttribute() {
        return (this.charAttribute);
    }

    public static Optional<Orientation> getOrientationFromCharAttribute(char charAttribute) {
        return (Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getCharAttribute() == charAttribute).findFirst());
    }

    public static Optional<Orientation> getOrientationFromCharAttribute(String charAttribute) throws VacuumException {
        if (charAttribute.length() > 1)
            throw new VacuumException("Unknown orientation");
        return (Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getCharAttribute() == charAttribute.charAt(0))
                .findFirst());
    }

    public static Optional<Orientation> getOrientationFromIndex(int index) {
        return (Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getIndex() == index).findFirst());
    }
}
