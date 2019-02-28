import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class WaitingArea {

  private int maxSize;

  // Keep track of other threads
  private List<Door> doorList;
  private List<Customer> waitingList;
  private List<Waitress> waitressList;

  public WaitingArea(int maxSize) {
    this.maxSize = maxSize;

    // Init synched lists
    doorList = new ArrayList<Door>();
    waitingList = new ArrayList<Customer>();
    waitressList = new ArrayList<Waitress>();
  }

  // Add the customer to the back of Queue
  // The Door has already checked that there is room
  public synchronized void enter(Customer customer) {
    // Add them to the end of the list
    this.waitingList.add(customer);
    SushiBar.write(Thread.currentThread().getName() + ": Customer " + customer + " is now waiting");

    System.out.println("Waiting area: " + Arrays.toString(waitingList.toArray()));

    // Notify all waiting waitresses that a new customer has arrived
    notifyAll();
  }

  // Get the customer at the front of line
  public synchronized Customer next() {
    Customer next = this.waitingList.remove(0);

    // Nofify all doors that there is space
    notifyAll();
    return next;
  }

  // Add waitress to list `on-call` waittresses
  // The waitress will then recive info regarding the waitingarea
  public void addWaitress(Waitress w) {
    waitressList.add(w);
  }

  // Remove waitress from list `on-call` waittresses
  // The waitress will no longer recive info regarding the waitingarea
  public void removeWaitress(Waitress w) {
    waitressList.remove(w);
  }

  // Add door to list of active doors
  // The door will then produce customers
  public void addDoor(Door d) {
    doorList.add(d);
  }

  // Remove door from list of acitve doors
  // The door will no longer create customers
  public void removeDoor(Door d) {
    doorList.remove(d);
  }

  // Returns true if there is room left in the waitingArea
  public boolean isSpace() {
    return this.waitingList.size() != this.maxSize;
  }

  // Returns true if there are no customers in the waitingArea
  public boolean isEmpty() {
    return this.waitingList.size() == 0;
  }
}
