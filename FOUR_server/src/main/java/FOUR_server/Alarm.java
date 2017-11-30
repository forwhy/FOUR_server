package FOUR_server;

import org.hibernate.Session;
import org.hibernate.Query;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Entity
public class Alarm implements Serializable{

    private static final long serialVersionUID = -8909674733237859865L;
    @Id
    @GeneratedValue
    private long id;
    private boolean off_on;
    private int timeToSleep;
    private String quote;

    public Alarm(){}

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getTimeToSleep() {
        return timeToSleep;
    }

    public void setTimeToSleep(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOff_on(boolean off_on) {
        this.off_on = off_on;
    }

    public boolean isOff_on() {
        return off_on;
    }

    //////////////////////////////////////

    public static String getReactionOn(Session s, int timetosleep)
    {
        try {
            Query q = s.createQuery("FROM Alarm WHERE timeToSleep=" + Integer.toString(timetosleep) + " AND off_on=true");
            List<Alarm> alarms = q.list();
            int max = alarms.size() - 1;
            if (max < 0)
                return "no comment yet";
            int numOfQuote = (int) (Math.random() * max);
            return alarms.get(numOfQuote).getQuote();
        }
        catch(Exception e)
        {
            return "Sorry, it seems some problem with Server, it'll be fixed (maybe)";
        }
    }


    public static String getReactionOff(Session s) {
        try {
            Query q = s.createQuery("FROM Alarm WHERE off_on=false");
            List<Alarm> alarms = q.list();
            int max = alarms.size() - 1;
            if (max < 0)
                return "no comment yet";
            int numOfQuote = (int) (Math.random() * max);
            return alarms.get(numOfQuote).getQuote();
        } catch (Exception e) {
            return "Sorry, it seems some problem with Server, it'll be fixed (maybe)";
        }
    }
}
