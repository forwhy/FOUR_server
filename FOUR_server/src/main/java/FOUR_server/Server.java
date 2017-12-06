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

import java.util.Calendar;
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
        String res="Инвалидная команда";
        try
        {
            String[] com = s.split(" ");
            //проверяем корректность введённых данных и, если всё хорошо, получаем реакцию из таблицы
            if(com.length==3)
            {
             if(com[0].equals("будильник") || com[0].equals("Будильник"))
             {

                     Integer minutes=null;
                     Integer hours=null;

                         try
                         {
                             hours = new Double(com[1]).intValue();
                             minutes = new Double(com[2]).intValue();


                         Calendar c=Calendar.getInstance();
                         int cur_hours = c.get(c.HOUR_OF_DAY);
                         //получаем интервал до будильника в часах
                         if(cur_hours<=hours)
                             h=hours.intValue()-cur_hours;
                         else
                             h=24-cur_hours+hours.intValue();

                         res = Alarm.getReactionOn(session, h);
                         session.beginTransaction();
                         session.getTransaction().commit();

                         }
                         catch (Exception e){}
                     }
            }
            else
            {
                if(com[1].equals("выключить") || com[1].equals("Выключить"))
                {
                    res = Alarm.getReactionOff(session);
                    session.beginTransaction();
                    session.getTransaction().commit();
                }
            }

////
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