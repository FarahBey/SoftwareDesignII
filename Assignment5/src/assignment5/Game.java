package assignment5;

import java.util.Scanner;
public class Game {
    public Boolean testingMode;
    private String code;

    private PastGuess History = new PastGuess();
    public int guessesLeft = GameConfiguration.guessNumber;
    public boolean won = false;
    public boolean lost = false;

    public boolean HasWon() {
        return won;
    }
    public boolean HasLost() {return lost;}
    public String yesPlay;
    public Game(){
        testingMode = false;
    }
    public Game(Boolean tm){
        testingMode = tm;
    }

    public Game(Boolean tm, String secretcode){
        testingMode = tm;
        code = secretcode;
    }
    public String getCode(){
        return code;
    }


    public Boolean isValid(String userGuess) {
        if(userGuess.length() != GameConfiguration.pegNumber){
            return false;
        }
        int element = -1;
        for(int i = 0; i < userGuess.length(); i++){
            for(int x = 0; x < GameConfiguration.colors.length; x++){
                if(userGuess.substring(i,i+1).equals(GameConfiguration.colors[x])){
                    element = x;
                }
            }
            if(element == -1){
                return false;
            }
        }
        return true;
    }
    public String notValid(String userGuess){
        String msg = "\n" + userGuess + " -> INVALID GUESS\n\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:";
        return msg;
    }
    public String printHistory(PastGuess list){
        String message = "";
        for(int i = 0; i < list.pastGuesses.size();i++){
            message = message + "\n" + list.pastGuesses.get(i).guess + "        " + list.pastGuesses.get(i).Result + "\n";
        }
        message = message + "\nYou have " + guessesLeft + " guesses left.\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:";
        return message;
    }
    public Boolean isHistory(String userGuess){
        if(userGuess.equals("HISTORY")){
            return true;
        }
        return false;
    }


    public String beginning() {
        String message = "Welcome to Mastermind. Here are the rules.\n\n" +
                "This is a text version of the classic board game Mastermind.\n\n" +
                "The computer will think of a secret code. The code consists of 4\n" +
                "colored pegs. The pegs MUST be one of six colors: blue, green,\n" +
                "orange, purple, red, or yellow. A color may appear more than once in\n" +
                "the code. You try to guess what colored pegs are in the code and\n" +
                "what order they are in. After you make a valid guess the result\n" +
                "(feedback) will be displayed.\n\n" +
                "The result consists of a black peg for each peg you have guessed\n" +
                "exactly correct (color and position) in your guess. For each peg in\n" +
                "the guess that is the correct color, but is out of position, you get\n" +
                "a white peg. For each peg, which is fully incorrect, you get no\n" +
                "feedback.\n\n" +
                "Only the first letter of the color is displayed. B for Blue, R for\n" +
                "Red, and so forth. When entering guesses you only need to enter the\n" +
                "first character of each color as a capital letter.\n\n" +
                "You have 12 guesses to figure out the secret code or you lose the\n" +
                "game.";
        return message;
    }
    public String introWords(){
        String message;
        message = "\nGenerating secret code ...";
            if (testingMode) {
                message = message + " (for this example the secret code is " + code + ")";
        }

        message = message + "\n\nYou have " + guessesLeft + " guesses left.\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:";
        return message;
    }
    public String runGame(String msg){
            if( guessesLeft != 0) {
                if(isHistory(msg)) {
                    return printHistory(History);
                }
                else if(!isValid(msg)){
                    return notValid(msg);
                }
                else{
                    guessesLeft--;
                    Guesses newGuess = new Guesses();
                    newGuess.result(msg,getCode());
                    History.addGuess(newGuess);
                    if(newGuess.B == getCode().length()){
                        won = true;
                        return  "\n" + msg + " -> Result: " + newGuess.Result + "\n";
                    }
                    else if(guessesLeft != 0){
                        String messageleft;
                        messageleft = "\n" + msg + " -> Result: " + newGuess.Result + "\n";
                        messageleft = messageleft + "You have " + guessesLeft + " guesses left.\n" +
                                "What is your next guess?\n" +
                                "Type in the characters for your guess and press enter.\n" +
                                "Enter guess:";
                        return messageleft;
                    }
                }
            }
           else{
               lost = false;
               return "\nSorry, you are out of guesses. You lose, boo-hoo.\n";}
    return "";
    }

}


