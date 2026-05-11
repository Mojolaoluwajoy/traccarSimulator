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
        long timestamp = System.currentTimeMillis();

        int latitude = (int) (6.4550 * 10_000_000);
        int longitude = (int) (3.3841 * 10_000_000);

        short speed = 60;

        byte[] avlRecord = new byte[]{
                (byte) (timestamp >> 58),
                (byte) (timestamp >> 48),
                (byte) (timestamp >> 40),
                (byte) (timestamp >> 32),
                (byte) (timestamp >> 24),
                (byte) (timestamp >> 16),
                (byte) (timestamp >> 8),
                (byte) (timestamp),

                0x00,

                (byte) (longitude >> 24),
                (byte) (longitude >> 16),
                (byte) (longitude >> 8),
                (byte) (longitude),


                (byte) (latitude >> 24),
                (byte) (latitude >> 16),
                (byte) (latitude >> 8),
                (byte) (latitude),

                0x00, 0x00,

                0x00, 0x00,

                0x07,

                (byte) (speed >> 8),
                (byte) (speed),

                0x00,

                0x00,
        };
        int dataLength = 3 + avlRecord.length + 2;

        byte[] packet = new byte[4 + 4 + dataLength + 4];
        int i = 0;

        packet[i++] = 0x00;
        packet[i++] = 0x00;
        packet[i++] = 0x00;
        packet[i++] = 0x00;

        packet[i++] = (byte) (dataLength >> 24);
        packet[i++] = (byte) (dataLength >> 16);
        packet[i++] = (byte) (dataLength >> 8);
        packet[i++] = (byte) (dataLength);

        packet[i++] = 0x08;

        packet[i++] = 0x01;

        for (byte b : avlRecord) {
            packet[i++] = b;
        }
            packet[i++]= 0x00;

            packet[i++] = 0x00;
            packet[i++] = 0x00;
            packet[i++] = 0x00;
            packet[i++] = 0x00;

            return packet;
        }


    }

