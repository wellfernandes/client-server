import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static String ip;
    private static int serverPort;
    private static PrintStream out;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {

        ip = "localhost";
        serverPort = 5500;
        try{
            socket = new Socket(ip, serverPort);
            System.out.println("connected client.");

           scanner = new Scanner(System.in);
            out = new PrintStream(socket.getOutputStream());
            out.println(scanner.nextLine());
        }catch(IOException e){
            System.out.println("error connecting to server." +
                    "->: "+ e.getStackTrace());
        }finally {
            out.close();
            scanner.close();
            socket.close();
        }
        while(scanner.hasNext()){
            out.println("Text: "+ scanner.nextLine());
        }
    }
}
