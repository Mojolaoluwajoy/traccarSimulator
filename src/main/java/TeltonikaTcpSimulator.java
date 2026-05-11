import java.io.OutputStream;
import java.net.Socket;

public class TeltonikaTcpSimulator {

    private static final String SERVER_IP="localhost";
    private static final int SERVER_PORT=5027;

    public static void main(String[] args) throws Exception{
        System.out.println("Connecting to Traccar on port " +SERVER_PORT+ "...");

        Socket socket=new Socket(SERVER_IP,SERVER_PORT);
        System.out.println("Connected");

        OutputStream out=socket.getOutputStream();

        byte[] packet = buildCodec8Packet();
        out.write(packet);
        out.flush();

        System.out.println("Packet sent to Traccar!");

        socket.close();
    }

    private static byte[] buildCodec8Packet() {

        return new byte[]{};
    }
}
