

public class Waitress implements Runnable {

  private WaitingArea waitingArea;
  private int orderWait = SushiBar.waitressWait;
  private int customerWait = SushiBar.customerWait;

  public Waitress(WaitingArea waitingArea) {
    this.waitingArea = waitingArea;
  }

  @Override
  public void run() {
    SushiBar.OUT.waitress(Thread.currentThread().getName() + ": Waitress is live");

    // This loop can't just have a check if the bar is open, since
    // there can be customers waiting after the bar closes its doors.
    while (SushiBar.isOpen || !waitingArea.isEmpty()) {

      // Get the next customer
      Customer current;
      synchronized(waitingArea) {
        current = waitingArea.next();
        SushiBar.OUT.waitress(Thread.currentThread().getName() + ": Waitress fetched customer " + current);
      }

      // Wait the required order time
      hold(orderWait);

      // Take the order
      current.order();

      // Wait the required eating time
      hold(customerWait);

    }
    SushiBar.OUT.waitress(Thread.currentThread().getName() + ": Waitress is dead");
  }

  private void hold(int hold) {
    try {
      SushiBar.OUT.waitress(Thread.currentThread().getName() + ": Waitress is serving");
      Thread.sleep(hold);
    } catch (InterruptedException ex) { }
  }
}

