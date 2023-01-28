import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private static ServerSocket serverSocket;
    private Socket socket;
    private static int serverPort;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String received = dataInputStream.readUTF();

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String serverOut = dataInputStream.readUTF();
            socket.close();
        }catch (Exception e){
            System.out.println("error connecting to port "+ serverPort
                    + "-> "+ e.getStackTrace());
        }
    }

    public static void main(String[] args) throws IOException {

        serverPort = 5500;
        serverSocket = new ServerSocket(serverPort);
        System.out.println("server on");

        while(true){
            Socket socket = serverSocket.accept();
            Server serverThread = new Server(socket);
            System.out.println("client connected! "+ socket.getLocalAddress());
            serverThread.start();
        }
    }
}

