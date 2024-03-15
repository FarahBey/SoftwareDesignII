package assignment5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            Socket s=new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            String msg;
            while(true){
                String serverResponse = din.readUTF();
                System.out.println(serverResponse);
                if(serverResponse.contains("has won")){
                    s.close();
                }
                if(serverResponse.contains("Sorry, you are out of guesses. You lose, boo-hoo.")){
                    s.close();
                }
                if(!s.isClosed()) {
                    System.out.print("> ");
                    msg = scanner.nextLine();
                    if (msg.equals("disconnect")) {
                        break;
                    }
                    else {
                        dout.writeUTF(msg);
                        dout.flush();
                    }
                }
            }

        }catch(IOException e){
            System.out.println(e.getMessage());}
    }
}
