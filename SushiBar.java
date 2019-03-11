import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SushiBar {

  //SushiBar settings
  private static int waitingAreaCapacity = 15;
  private static int waitressCount = 8;
  private static int duration = 4;
  public static int maxOrder = 10;
  public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
  public static int customerWait = 2000; // Used to calculate the time the customer spends eating
  public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
  public static boolean isOpen = true;

  //Creating log file
  private static File log;
  private static String path = "./";

  //Variables related to statistics
  public static SynchronizedInteger customerCounter;
  public static SynchronizedInteger totalOrders;
  public static SynchronizedInteger servedOrders;
  public static SynchronizedInteger takeawayOrders;

  // Custom color output
  public static Output OUT = new Output();


  public static void main(String[] args) {
    log = new File(path + "log.txt");

    //Initializing shared variables for counting number of orders
    customerCounter = new SynchronizedInteger(0);
    totalOrders = new SynchronizedInteger(0);
    servedOrders = new SynchronizedInteger(0);
    takeawayOrders = new SynchronizedInteger(0);

    // Create a new clock
    Clock c = new Clock(20);

    // Shared resource
    WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
    OUT.waiting(Thread.currentThread().getName() + ": WaitingArea is live");

    // Create waitresses and add them to the waitingArea
    for(int i = 0; i < waitressCount; i++) {
      Waitress waitress = new Waitress(waitingArea);
      (new Thread(waitress)).start();
      waitingArea.addWaitress(waitress);
    }

    // Create a door
    Door door = new Door(waitingArea);
    waitingArea.addDoor(door);
    (new Thread(door)).start();
  }

  //Writes actions in the log file and console
  public static void write(String str) {
    try {
      FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(Clock.getTime() + ", " + str + "\n");
      bw.close();
      System.out.println(Clock.getTime() + ", " + str);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
