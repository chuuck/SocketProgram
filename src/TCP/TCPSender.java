package TCP;


import java.io.*;
import java.net.*;

class TCPSender{

    public static void main(String [] args){

        try{

            Socket socket = new Socket("2001:630:d0:5010:f915:ee5f:f3b6:ce99",4322);
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            for(int i = 0; i < 10; i++){

                out.println("TCP message " + i);

                //https://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams
                out.flush();
                System.out.println("TCP message " + i + " sent");


                //TODO :: comment the next 4 lines if you want the TCPReceiver to work
                //receives the acknowledgment from the TCPReceiverThreadedClass
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println(reader.readLine());


                Thread.sleep(500);

            }

        }catch (Exception e){

            System.out.println("error" + e);
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


                while((line = in.readLine()) != null)
                    System.out.println(line);

                client.close();
            }catch(Exception e){ }
        }
    }
}
