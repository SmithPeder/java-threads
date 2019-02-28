

public class Door implements Runnable {

  private WaitingArea waitingArea;
  private int interval = SushiBar.doorWait;

  public Door(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  @Override
  public void run() {
    SushiBar.write(Thread.currentThread().getName() + ": Door is live");
    // Continue to create customers while the bar is open
    // When the bar closes the loop will end and the run
    // method will return, closing the Door thread
    while(SushiBar.isOpen) {

      Customer newCustomer = new Customer();
      SushiBar.write(Thread.currentThread().getName() + ": Door created customer " + newCustomer.getCustomerID());

      // Check if there is room in the waitingArea
      // Set the thread to wait if there is no room
      if (!waitingArea.isSpace()) {
        try {
          waitingArea.wait();
        } catch (InterruptedException ex) { }
      }

      // Send the customer to the waitingArea
      // The Thread should only come here if the waitingarea isn't full
      waitingArea.enter(newCustomer);

      // Wait the required time before producing a new customer
      hold(interval);
    }
  }

  private void hold(int hold) {
    try {
      Thread.sleep(hold);
    } catch (InterruptedException ex) { }
  }
}
