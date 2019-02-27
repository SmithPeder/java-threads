

public class Customer {

  // Unique id for each customer
  private int id;

  // Keep track of all orders
  private int totalOrders;
  private int eatenOrders;
  private int takeawayOrders;

  public Customer() {
    // Each customer gets a unique id from the sync counter
    this.id = SushiBar.customerCounter.get();
    SushiBar.customerCounter.increment();
  }

  public synchronized void order(){
    int max = SushiBar.maxOrder;

    int x = (int)(Math.random() * max + 1);
    int y = (int)(Math.random() * x);
    int z = x - y;

    this.totalOrders = x;
    this.eatenOrders = y;
    this.takeawayOrders = z;
  }

  public int getCustomerID() {
    return this.id;
  }

  // Return this customers orders in arrayform
  // This is to be used for statistics
  public int[] getNumberOfOrders() {
    return new int[] {totalOrders, eatenOrders, takeawayOrders};
  }
}
