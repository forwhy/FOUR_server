package FOUR_server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Alarm al = new Alarm();
        al.setOff_on(true);
        al.setQuote("just sleep");
        al.setTimeToSleep(7);
        session.save(al);

        session.getTransaction().commit();
        sessionFactory.close();
        System.out.println( "Bye World!" );
    }
}
