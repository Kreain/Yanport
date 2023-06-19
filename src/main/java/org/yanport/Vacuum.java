package org.yanport;

import java.util.Optional;

public class Vacuum {

    private final Map map;
    private final Coordinates coordinates;
    private Orientation orientation;
    private final Instructions instructions;

    public Vacuum(Map map, Coordinates coordinates, Orientation orientation, String instructionSequence) throws VacuumException {
        this.map = map;
        this.coordinates = coordinates;
        this.orientation = orientation;

        this.instructions = new Instructions(instructionSequence);
    }

    public void turnRight() {
        final int nextIndex = orientation.getIndex() + 1;
        Optional<Orientation> newOrientation = Orientation.getOrientationFromIndex(
                nextIndex > 3 ? 0 : nextIndex);

        newOrientation.ifPresent(value -> this.orientation = value);
    }

    public void turnLeft() {
        final int prevIndex = orientation.getIndex() - 1;
        Optional<Orientation> newOrientation = Orientation.getOrientationFromIndex(
                prevIndex < 0 ? 3 : prevIndex);

        newOrientation.ifPresent(value -> this.orientation = value);
    }

    public void goForward() throws VacuumException {
        final int newAbscissa = this.coordinates.getAbscissa_x() + orientation.getAbscissaModifier();
        final int newOrdinate = this.coordinates.getOrdinate_y() + orientation.getOrdinateModifier();

        if (newAbscissa >= map.getAbscissaSize() || newOrdinate >= map.getOrdinateSize())
            throw new VacuumException("Sorry, cannot go through map edge, not going forward");

        this.coordinates.setAbscissa_x(newAbscissa);
        this.coordinates.setOrdinate_y(newOrdinate);
    }

    public void launchVacuum() {
       char c;

       while ((c = instructions.getNextInstruction()) != 0) {
           switch (c) {
               case 'D' -> turnRight();
               case 'G' -> turnLeft();
               case 'A' -> {
                   try {
                       goForward();
                   } catch (VacuumException exception) {
                       System.out.println(exception.getMessage());
                   }
               }
           }
       }
    }

    public void displayCoordinates() {
        System.out.println("Vacuum Coordinates:");
        System.out.println("X -> " + this.coordinates.getAbscissa_x());
        System.out.println("Y -> " + this.coordinates.getOrdinate_y());
        System.out.println("Oriented towards: " + this.orientation.name());
    }
}
