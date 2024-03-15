package assignment5;

public class Guesses{
    public String guess;
    public String Result;
    public int B;
    public int W;


    public void result(String newUserInput, String Code){
        String hold = newUserInput;
        char[] holdChars = hold.toCharArray();

        String tempCode = Code;
        char [] tempCodeChar = tempCode.toCharArray();

        for(int i = 0; i < Code.length(); i++){
            if(holdChars[i] == tempCodeChar[i]){
                B++;
                holdChars[i] = '-';
                tempCodeChar[i] = '-';
            }
        }
        for(int i = 0; i < Code.length(); i++){
            for(int x = 0; x < Code.length(); x++){
                if((tempCodeChar[i] == holdChars[x]) && (tempCodeChar[i] != '-') && (holdChars[x] != '-')){
                    W++;
                    tempCodeChar[i] = '-';
                    holdChars[x] = '-';
                }
            }
        }
        //store in history
        guess = newUserInput;
        Result = new String(B + "B_" + W + "W");



    }
}
