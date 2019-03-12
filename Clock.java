import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class Clock {
  Timer timer;

  public Clock(int seconds) {
    timer = new Timer();  //At this line a new Thread will be created
    timer.schedule(new RemindTask(), seconds * 1000); //delay in milliseconds
    SushiBar.write("THE SHOP IS NOW OPEN");
    SushiBar.OUT.clock("=================OPEN=================");
  }

  class RemindTask extends TimerTask {
    public void run() {
      SushiBar.isOpen = false; //prevents creating new customers
      timer.cancel();
      SushiBar.OUT.clock("=================CLOSED=================");
      SushiBar.write("NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW");
    }
  }

  public static String getTime() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
    return sdf.format(cal.getTime());
  }
}
