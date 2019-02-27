

public class Waitress implements Runnable {

  private WaitingArea waitingArea;
  private int orderWait = SushiBar.waitressWait;
  private int customerWait = SushiBar.customerWait;

  public Waitress(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  @Override
  public void run() {

    // This loop can't have a check if the bar is open, since there
    // can be customers waiting after the bar closes its doors.
    while (true) {
      // Keep getting new cutomers if there are some waiting
      // If there are none the waiter will wait()
      if (waitingArea.isEmpty()) {
        try {
          wait();
        } catch (InterruptedException ex) { }
      }

      // Get the next customer
      Customer current = waitingArea.next();

      // Wait the required order time
      hold(orderWait);

      // Take the order
      current.order();

      // Wait the required eating time
      hold(customerWait);
    }
  }

  private void hold(int hold) {
    try {
      Thread.sleep(hold);
    } catch (InterruptedException ex) { }
  }
}

