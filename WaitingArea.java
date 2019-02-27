import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class WaitingArea {

  private int maxSize;

  // Keep track of other threads
  private List<Door> synchedDoorList;
  private List<Customer> synchedWaitingList;
  private List<Waitress> synchedWaitressList;

  public WaitingArea(int maxSize) {
    this.maxSize = maxSize;

    // Init synched lists
    synchedDoorList = Collections.synchronizedList(new LinkedList<Door>());
    synchedWaitingList = Collections.synchronizedList(new LinkedList<Customer>());
    synchedWaitressList = Collections.synchronizedList(new LinkedList<Waitress>());
  }

  // Add the customer to the back of Queue
  // The Door has already checked that there is room
  public synchronized void enter(Customer customer) {
    if(!isSpace()) {
      System.out.println("Error: WaitingArea.enter() called on full area");
    }
    // Add them to the end of the list
    this.synchedWaitingList.add(customer);

    // Notify all waiting waitresses that a new customer has arrived
    this.synchedWaitingList.notifyAll();
  }

  // Get the customer at the front of line
  public synchronized Customer next() {
    Customer next = this.synchedWaitingList.get(0);
    // Nofify all doors that there is space
    synchedDoorList.notifyAll();
    return next;
  }

  // Add waitress to list `on-call` waittresses
  // The waitress will then recive info regarding the waitingarea
  public void addWaitress(Waitress w) {
    synchedWaitressList.add(w);
  }

  // Remove waitress from list `on-call` waittresses
  // The waitress will no longer recive info regarding the waitingarea
  public void removeWaitress(Waitress w) {
    synchedWaitressList.remove(w);
  }

  // Returns true if there is room left in the waitingArea
  public boolean isSpace() {
    return this.synchedWaitingList.size() != this.maxSize;
  }

  // Returns true if there are no customers in the waitingArea
  public boolean isEmpty() {
    return this.synchedWaitingList.size() == 0;
  }
}
