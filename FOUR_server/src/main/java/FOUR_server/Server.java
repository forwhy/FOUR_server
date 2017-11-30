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
            if(com.length==2)
            {
             if(com[0].equals("alarm"))
             {
                 //if(com[2].equals("on"))
                 //{
                     String [] time = com[1].split(":");
                     Double minutes=null;
                     Double hours=null;
                     if(time.length==2) {
                         try {
                             hours = new Double(time[0]);
                             minutes = new Double(time[1]);

                            if(hours.intValue()==hours.doubleValue() && minutes.intValue()==minutes.doubleValue()) {


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
                         }catch (Exception e){}
                     }

                     else
                     {
                         if(com[1].equals(("off"))) {
                             res = Alarm.getReactionOff(session);
                             session.beginTransaction();
                             session.getTransaction().commit();
                         }
                     }
                    // }
                 }
            }
////            else
////            {
////                if(com.length==2)
////                {
////                    if(com[0].equals("alarm"))
////                    {
////                        if(com[1].equals("off"))
////                        {
//                            res = Alarm.getReactionOff(session);
//                            session.beginTransaction();
//                            session.getTransaction().commit();
////                        }
////                    }
////                }
////            }
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