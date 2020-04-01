package TCP;

import java.io.*;
import java.net.*;

class TCPSender{

    public static void main(String [] args){

        try{

            Socket socket = new Socket("2001:630:d0:5010:65d4:42bd:4fe1:eee5",4322);
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            for(int i = 0; i < 10; i++){

                out.println("TCP message " + i);
                //https://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams
                out.flush();
                System.out.println("TCP message " + i + " sent");

                Thread.sleep(1000);

            }

        }catch (Exception e){

            System.out.println("error" + e);
        }
    }
}
