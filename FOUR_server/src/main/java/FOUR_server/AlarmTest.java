package FOUR_server;

//import junit.framework.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AlarmTest
{
    private List<Alarm> alarms;
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void init()
    {
        alarms = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

//    @After
//    public void end()
//    {
//        alarms = null;
//        session.close();
//        sessionFactory.close();
//    }


    @Test
    public void getQuoteTest()
    {
        //сессия, часов, вкл/выкл
        assertTrue(Alarm.getReactionOn(session,1).equals("damn!"));
        assertTrue(Alarm.getReactionOn(session,3).equals("not enough!"));
    }

    @Test
    public void  getNonExistQuoteTest()
    {
        assertTrue(Alarm.getReactionOn(session,10).equals("no comment yet"));
        assertTrue(Alarm.getReactionOff(session).equals("no comment yet"));
    }


    @Test
    public void tryWrongSession()
    {
        assertTrue(Alarm.getReactionOn(null,10).equals("Sorry, it seems some problem with Server, it'll be fixed (maybe)"));
    }



}

