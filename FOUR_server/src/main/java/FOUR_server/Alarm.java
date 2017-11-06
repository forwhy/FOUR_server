package FOUR_server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

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



}
