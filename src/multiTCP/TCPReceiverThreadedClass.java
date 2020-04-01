package multiTCP;

import java.io.*;
import java.net.*;

class TCPReceiverThreadedClass{

    public static void main(String [] args){
        try{

            ServerSocket ss = new ServerSocket(4322);

            for(;;){
                try{
                    //The call Socket client = ss.accept() blocks and waits until a client attempts a connection
                    Socket client = ss.accept();
                    new Thread(new ServiceThread(client)).start();

                }catch(Exception e){
                    System.out.println("error " + e);
                }
            }
        }catch(Exception e){
            System.out.println("error "+e);
        }
    }

    static class ServiceThread implements Runnable{

        Socket client;

        ServiceThread(Socket c){
            client = c;
        }

        public void run(){

            try{

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;

            while((line = in.readLine()) != null){


                System.out.println(line+" received from " + client.getInetAddress());

                Thread.sleep(3000);
                //Sending acknowledgement
                PrintWriter out = new PrintWriter(client.getOutputStream());
                out.println("Message Received");
                out.flush();
                System.out.println("-Acknowledging message");
            }

                client.close();
            }catch(Exception e){ }
        }
    }
}