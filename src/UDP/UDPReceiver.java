package UDP;//This is the receiver class that waits for a packet from sender, once that is finished it will send a responsePacket as confirmation that the packet has been received.
import java.net.*;
import java.util.ArrayList;

class UDPReceiver{

    static ArrayList<Integer> allBytesReceived = new ArrayList<>();
    static DatagramSocket socket;

    public static void main(String [] args){

        try{

            socket = new DatagramSocket(4321);
            byte[] buf = new byte[256];
            byte[] responseArr = new byte[1];
            responseArr[0] = 'x';
            for(int i = 0; i < 20; i++){

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet); //The receive method waits forever until a packet is received. If no packet is received, the server makes no further progress and just waits.
                System.out.println("receive DatagramPacket " + (new String(packet.getData())).trim() + " " + packet.getAddress() + ":" + packet.getPort());

                DatagramPacket responsePacket = new DatagramPacket(responseArr, responseArr.length, packet.getAddress(), packet.getPort());
                System.out.println("sending responsePocket " + (new String(responsePacket.getData())).trim() + " " + responsePacket.getAddress() + ":" + responsePacket.getPort());
                socket.send(responsePacket);


                if (isNumeric(new String(packet.getData()))){
                    System.out.println("---------------------------------");
                    allBytesReceived.add(i);
                }

                Thread.sleep(5000);

            }

            System.out.println("Number of packets lost: " + allBytesReceived.size());

        } catch(Exception e){
            System.out.println("error "+e);
            e.printStackTrace();
        }

    }


    //Method that checks if the packet contains a number
    public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
}