package assignment5;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import static assignment5.MyServer.players;


public class MyServer {
    public static HashMap<Socket, Game> players = new HashMap<>();
    public static void main(String[] args){
        try{
            String code = SecretCodeGenerator.getInstance().getNewSecretCode();
            ServerSocket serverSocket = new ServerSocket(6666);
            //Socket ss = serverSocket.accept();//establishes connection
            System.out.println("Waiting for connection... ");

            while(true){
                Socket clientSocket = serverSocket.accept();

                System.out.println("Accepted socket from " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                Game R1 = new Game(true, code);
                players.put(clientSocket,R1);

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        }catch(IOException e){
            System.out.println(e.getMessage());}
    }
}


class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            DataInputStream datain = new DataInputStream(in);
            OutputStream out = clientSocket.getOutputStream();
            DataOutputStream dataout = new DataOutputStream(out);

            dataout.writeUTF(players.get(clientSocket).beginning() + "You are Player " + clientSocket.getPort() +
                    "\n\n" + players.get(clientSocket).introWords());
            dataout.flush();

            String message;
            while (true) {
                message = datain.readUTF();
                if(message == null){
                    break;
                }
                String response = players.get(clientSocket).runGame(message);


                if(players.get(clientSocket).HasWon()) {
                    for(Socket client : MyServer.players.keySet()) {
                        OutputStream winnerOut = client.getOutputStream();
                        DataOutputStream winnerDataout = new DataOutputStream(winnerOut);
                        winnerDataout.writeUTF("Player " + clientSocket.getPort() + " has won!");
                        winnerDataout.flush();
                    }
                }
                if(players.get(clientSocket).HasLost()){
                    dataout.writeUTF(response);
                    dataout.flush();
                }
                else {
                    dataout.writeUTF(response);
                    dataout.flush();
                }
                System.out.println("Received message: " + message);
            }
            clientSocket.close();
        } catch (IOException e) {
           System.out.println(e.getMessage());}
    }
}