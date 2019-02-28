

public class Waitress implements Runnable {

  private WaitingArea waitingArea;
  private int orderWait = SushiBar.waitressWait;
  private int customerWait = SushiBar.customerWait;

  public Waitress(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  @Override
  public void run() {
    SushiBar.write(Thread.currentThread().getName() + ": Waitress is live");

    // This loop can't just have a check if the bar is open, since
    // there can be customers waiting after the bar closes its doors.
    while (SushiBar.isOpen || !waitingArea.isEmpty()) {

      // Keep getting new cutomers if there are some waiting
      // If there are none the waiter will wait()
      synchronized(waitingArea) {
        if (waitingArea.isEmpty()) {
          try {
            waitingArea.wait();
          } catch (InterruptedException ex) { }
        }
      // Get the next customer
      Customer current = waitingArea.next();
      SushiBar.write(Thread.currentThread().getName() + ": Waitress fetched customer " + current);

      // Wait the required order time
      hold(orderWait);

      // Take the order
      current.order();

      // Wait the required eating time
      hold(customerWait);
      }

    }
    SushiBar.write(Thread.currentThread().getName() + ": Waitress is dead");
  }

  private void hold(int hold) {
    try {
      Thread.sleep(hold);
    } catch (InterruptedException ex) { }
  }
}

