package assignment2;
import java.util.Scanner;
public class Game {
    public Boolean testingMode;
    private String code;
    public int guessesLeft = GameConfiguration.guessNumber;
    public int won =0;
    public String yesPlay;
    public Game(){
        testingMode = false;
    }
    public Game(Boolean tm){
        testingMode = tm;
    }
    public void setCode(){
        code = SecretCodeGenerator.getInstance().getNewSecretCode();
    }
    public String getCode(){
        return code;
    }

    Scanner in = new Scanner(System.in);
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
    public void notValid(String userGuess){
        System.out.print("\n" + userGuess + " -> INVALID GUESS\n\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:");
    }
    public void printHistory(PastGuess list){
        for(int i = 0; i < list.pastGuesses.size();i++){
            System.out.print("\n" + list.pastGuesses.get(i).guess + "        " + list.pastGuesses.get(i).Result + "\n");
        }
        System.out.print("\nYou have " + guessesLeft + " guesses left.\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:");
    }
    public Boolean isHistory(String userGuess){
        if(userGuess.equals("HISTORY")){
            return true;
        }
        return false;
    }


    public void beginning() {
        System.out.print("Welcome to Mastermind. Here are the rules.\n\n" +
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
                "game. Are you ready to play? (Y/N):");

        setCode();
        yesPlay = in.nextLine();
        if(yesPlay.equals("Y")) {
            runGame();
        }
    }
    public void introWords(){
        if (yesPlay.equals("Y")) {
            System.out.print("\nGenerating secret code ...");
            if (testingMode) {
                System.out.print(" (for this example the secret code is " + code + ")");
            }
        }
        System.out.print("\n\nYou have " + guessesLeft + " guesses left.\n" +
                "What is your next guess?\n" +
                "Type in the characters for your guess and press enter.\n" +
                "Enter guess:");
    }
    public void runGame(){
        introWords();
        String newinp = in.nextLine();

        while(yesPlay.equals("Y")) {
            PastGuess History = new PastGuess();
            while (guessesLeft != 0 && won == 0) {
                if(isHistory(newinp)) {
                    printHistory(History);
                    newinp = in.nextLine();
                }
                else if(!isValid(newinp)){
                    notValid(newinp);
                    newinp = in.nextLine();
                }
                else{
                    guessesLeft--;
                    Guesses newGuess = new Guesses();
                    newGuess.result(newinp,getCode());
                    History.addGuess(newGuess);
                    if(newGuess.B == getCode().length()){
                        won = 1;
                        System.out.print("\n" + newinp + " -> Result: " + newGuess.Result + " - You win !!\n");
                    }
                    else if(guessesLeft == 0) {
                        System.out.print("\nSorry, you are out of guesses. You lose, boo-hoo.\n");
                    }
                    else if(guessesLeft != 0){
                        System.out.print("\n" + newinp + " -> Result: " + newGuess.Result + "\n");
                        System.out.print("You have " + guessesLeft + " guesses left.\n" +
                                "What is your next guess?\n" +
                                "Type in the characters for your guess and press enter.\n" +
                                "Enter guess:");
                        newinp = in.nextLine();
                    }
                }
            }
            System.out.print("\n\nAre you ready for another game (Y/N):");
            yesPlay = in.nextLine();
            if(yesPlay.equals("Y")){
                won = 0;
                setCode();
                guessesLeft = GameConfiguration.guessNumber;
                introWords();
                newinp = in.nextLine();
            }
        }
    }

}

