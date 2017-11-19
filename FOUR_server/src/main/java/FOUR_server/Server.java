package FOUR_server;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.util.List;


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
            server = new ServerSocket(5678, 10);
            while (true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                String s = (String)input.readObject();
                output.writeObject(getComment(s));
                System.out.println(s);

            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } catch (HeadlessException e) {
        } catch (ClassNotFoundException e) {
        }

    }

    private static String getComment(String s)
    {
        List<Alarm> alarms = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        int h=0;
        boolean t=true;
        String res="";
        try
        {
            String[] com = s.split(" ");
            h = Integer.valueOf(com[1]);
            if(com[2].equals("on"))
                t=true;
            else t=false;
            res = Alarm.getReaction(session,h,t);
            session.beginTransaction();
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally
        {
            //session.close();
            //sessionFactory.close();
        }
        return res;

    }


}