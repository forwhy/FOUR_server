import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server implements Runnable {

    static private ServerSocket server;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    public static void main(String[] args) {

        //callDB();
        new Thread(new Server()).start();

    }

    @Override
    public void run() {

        try {
            server = new ServerSocket(5432, 10);
            while (true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                //output.writeObject("Вы прислали: " + (String) input.readObject());
                System.out.println("Получено: "+ (String)input.readObject());
            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (HeadlessException e) {
        } catch (ClassNotFoundException e) {
        }

    }




}