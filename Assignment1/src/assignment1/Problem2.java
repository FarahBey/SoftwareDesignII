/*
Name: Farah Bey
UTEid: fkb282
Lab Section: 17630
 */

package assignment1;

import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        oneDollar(s);
    }

    public static void oneDollar(String s){
        String value = "abcdefghijklmnopqrstuvwxyz";
        String[] seperated = s.split(" ");
        for(int i = 0; i< seperated.length;i++){
            int hold = 0;
            for(int x = 0; x < seperated[i].length(); x++){
                char letter = seperated[i].charAt(x);
                char lowercaseLetter = Character.toLowerCase(letter);
                if (value.indexOf(lowercaseLetter) != -1) {
                    hold += value.indexOf(lowercaseLetter) + 1;
                }
            }
            if(hold == 100){
                System.out.println(seperated[i]);
            }
        }
    }
}