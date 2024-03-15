/*
Name: Farah Bey
UTEid: fkb282
Lab Section: 17630
 */


package assignment1;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int result = largestConsecutive(n, s);
        System.out.println(result);
    }

    public static int largestConsecutive(int n, String s){
        int largestMultiplied = 0;
        for(int i = 0; i < s.length() - n + 1; i++){
            int hold = 1;
            for(int x = i, cnt = 0; cnt < n ; x++, cnt++){
                hold = hold * Integer.parseInt(s.substring(x, x + 1));
                if(hold > largestMultiplied){
                    largestMultiplied = hold;
                }
            }
        }
        return largestMultiplied;
    }

}
