package assignment5;

import java.util.ArrayList;

public class PastGuess {
    public ArrayList<Guesses> pastGuesses;

    public PastGuess() {
        pastGuesses = new ArrayList<>();
    }
    public void addGuess(Guesses g1){
        pastGuesses.add(g1);
    }
}
