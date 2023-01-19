import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static int serverPort;

    public static void main(String[] args) throws IOException {

        serverPort = 5500;
        serverSocket = new ServerSocket(serverPort);
        socket = new Socket();
        System.out.println("server on");

        try{
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String serverOut = dataInputStream.readUTF();

            dataOutputStream.writeUTF(serverOut);

        }catch (IOException e){
            System.out.println("error connecting to port "+ serverPort
            + "-> "+ e.getStackTrace());
        }finally {
            socket.close();
        }
    }
}

