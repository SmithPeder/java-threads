import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class WaitingArea {

  private int maxSize;

  // Keep track of other threads
  private ArrayList<Door> doorList;
  private ArrayList<Customer> waitingList;
  private ArrayList<Thread> waitressList;

  public WaitingArea(int maxSize) {
    this.maxSize = maxSize;

    // Init synched lists
    doorList = new ArrayList<Door>();
    waitingList = new ArrayList<Customer>();
    waitressList = new ArrayList<Thread>();
  }

  // Add the customer to the back of Queue
  public synchronized void enter(Customer customer) {
    if(!isSpace()) {
      try {
        SushiBar.OUT.door(Thread.currentThread().getName() + ": Door is waiting");
        wait();
      } catch (InterruptedException ex) { }
    }

    // Add them to the end of the list
    this.waitingList.add(customer);
    SushiBar.write(Thread.currentThread().getName() + ": Customer " + customer.getCustomerID() + " is waiting.");
    SushiBar.OUT.waiting(Thread.currentThread().getName() + ": Customer " + customer + " is now waiting");

    SushiBar.OUT.red("Waiting area: " + Arrays.toString(waitingList.toArray()));

    // Wake up everything
    notifyAll();
  }

  // Get the customer at the front of line
  public synchronized Customer next() {
    while (isEmpty()) {
      try {
        SushiBar.OUT.waitress(Thread.currentThread().getName() + ": Waitress is waiting");
        wait();
      } catch (InterruptedException ex) { }
    }

    Customer next = this.waitingList.remove(0);
    SushiBar.write(Thread.currentThread().getName() + ": Customer " + next.getCustomerID() + " is fetched.");

    // Wake up everything
    this.notifyAll();
    return next;
  }

  // Add waitress to list `on-call` waittresses
  // The waitress will then recive info regarding the waitingarea
  public void addWaitress(Thread w) {
    waitressList.add(w);
  }

  // Remove waitress from list `on-call` waittresses
  // The waitress will no longer recive info regarding the waitingarea
  public void removeWaitress(Thread w) {
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

  public ArrayList<Thread> getWList() {
    return waitressList;
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
