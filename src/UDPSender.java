//This is a sender class that will send packets to the receiver
//The Sender will only proceed to send the next packet once the responsePacket is received
import java.net.*;

class UDPSender{

    static DatagramSocket socket;

    public static void main(String [] args){

        try{

            InetAddress address = InetAddress.getByName("yourIP");
            socket = new DatagramSocket();
            byte[] responseArr = new byte[1];

            for(int i = 0; i < 20 ;i++){

                byte[] buf = String.valueOf(i).getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4321);
                socket.send(packet);

                System.out.println("send DatagramPacket " + new String(packet.getData()) + " " + packet.getAddress() + ":" + packet.getPort());

                DatagramPacket responsePacket = new DatagramPacket(responseArr, responseArr.length);

                socket.receive(responsePacket);
                System.out.println("received DatagramPacket " + new String(responsePacket.getData()) + " " + responsePacket.getAddress() + ":" + responsePacket.getPort());
                Thread.sleep(500);
            }

        }catch(Exception e) {

            System.out.println("error");
        }
    }

}
