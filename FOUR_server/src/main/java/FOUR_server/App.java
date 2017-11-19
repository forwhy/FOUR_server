package FOUR_server;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;



public class App
{
    public static void main( String[] args )throws Exception
    {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(AlarmTest.class);
        System.out.println("run tests: " + result.getRunCount());
        System.out.println("failed tests: " + result.getFailureCount());
        System.out.println("ignored tests: " + result.getIgnoreCount());
        System.out.println("success: " + result.wasSuccessful());
    }
}


