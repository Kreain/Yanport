package org.yanport;

import java.util.ArrayList;

public class Instructions {

    private final String instructionSequenceMemory;
    private final ArrayList<Character> instructionSequence;

    private ArrayList<Character> verifySequence(String instructionSequence) throws VacuumException {
        ArrayList<Character> sequence = new ArrayList<>();

        for (char c : instructionSequence.toCharArray()) {
            if (c != 'D' && c != 'G' && c != 'A')
                throw new VacuumException("Instructions contains an error");
            sequence.add(c);
        }
        return (sequence);
    }

    public Instructions(String instructionSequence) throws VacuumException {
        this.instructionSequence = verifySequence(instructionSequence);
        this.instructionSequenceMemory = instructionSequence;
    }

    public char getNextInstruction() {
        if (this.instructionSequence.isEmpty())
            return (0);

        char instruction = this.instructionSequence.get(0);
        this.instructionSequence.remove(0);
        return (instruction);
    }
}
