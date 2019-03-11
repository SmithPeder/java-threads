

public class Customer {

  // Unique id for each customer
  private int id;

  public Customer() {
    // Each customer gets a unique id from the sync counter
    this.id = SushiBar.customerCounter.get();
    SushiBar.customerCounter.increment();
  }

  // As there only one Customer object, this need to be a shared resource
  public synchronized void order(){
    // Set max
    int max = SushiBar.maxOrder;

    // Generate random values for orders
    int x = (int)(Math.random() * max + 1);
    int y = (int)(Math.random() * x);
    int z = x - y;

    // Increment the shared variables
    SushiBar.totalOrders.add(x);
    SushiBar.servedOrders.add(x);
    SushiBar.takeawayOrders.add(x);
  }

  public int getCustomerID() {
    return this.id;
  }

  public String toString() {
    return String.valueOf(this.id);
  }
}
