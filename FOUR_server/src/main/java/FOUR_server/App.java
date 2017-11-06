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

        sessionFactory.close();
        System.out.println( "Bye World!" );
    }
}
