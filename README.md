# Sushi Bar Simulation

## The Overall Purpose

In this assignment you are going to simulate a sushi bar. The implementation  will need to manage several threads that are using shared resources. These resources should be protected using the monitor concept. The implementation should use the producer/consumer model to solve the problem.

The Producer and Consumers should synchronize using the `𝑤𝑎𝑖𝑡( )`, `𝑛𝑜𝑡𝑖𝑓𝑦( )` and `𝑛𝑜𝑡𝑖𝑓𝑦𝐴𝑙𝑙( )`, and
mutual exclusion should be implemented using the Java monitor.

## The Problem

The sushi bar consists of a clock, a door, a waiting area, a number of waitresses and customers. The bar is open for only a specific time period, in which the customers can enter the waiting area. Because of the limited number of waitresses, customers may have to wait in the waiting area. When a waitress fetches a customer, he/she will order some sushi. Customers have the choice to eat some of their sushi in the bar and have the rest as takeaway.

When it is closing time, the bar cannot accept more customers and only those who have already entered the waiting area will be served (i.e. customers already in the waiting area are allowed to order).

## The Customer

When a customer is created it should be given a unique ID. Also, the customer should be able to order food, when he/she is fetched from the waiting area by a waitress. After ordering, the customer takes some time eating.

Note that the customer should have minimal functionality, leaving the adding and removing of customers in the waiting area to the producer (door) and consumers (waitresses).

## The Door

The Door will work as the producer.

The door creates customers at random intervals and tries to put them in the waiting area if there is room.If there is no more room in the waiting area the door should wait until notified that a customer left the waiting area.

## The Waiting Area

The waiting area is a common resource shared between the producer and consumers. Customers can only be added as long as there is room in the waiting area, dictated by `𝑤𝑎𝑖𝑡𝑖𝑛𝑔𝐴𝑟𝑒𝑎𝐶𝑎𝑝𝑎𝑐𝑖𝑡𝑦`.

The waiting area should have:
- A way of keeping track of all the waiting customers
- A max capacity

Functionality:
- Let customers enter when there is room
- A way for customers to be fetched by waitresses

## Waitresses
The waitresses will work as the consumers.

When a customer is fetched, the waitress uses some time before taking the customer’s order.

After the customer is finished ordering and done eating, the waitress can fetch a new customer from the waiting area.

**Note** that the customers should be fetched from the waiting area in a first come – first serve manner. This means that the earlier the customer enters the waiting area, the sooner he/she is fetched.

## The Clock

The clock in the bar serves two purposes:
- It will alert the door of the closing time, so the door will generate no more customers.
- The clock keeps track of the current time of simulation, which will be used for logging.

You will not have to implement the clock yourself; instead you must use the provided class: `Clock.java`. This clock sets the `isOpen` variable to false when the simulation time ends. The only thing you need to do is to initiate the clock `new Clock(duration)`, and use the `isOpen` variable properly.

## Logging
To see what is happening in the Sushi Bar you should print out the following messages at the time they happen:
- Customer `#ID` is now waiting.
- Customer `#ID` is now fetched.
- Customer `#ID` is now eating.
- Customer `#ID` is now leaving.
- _NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW_

In order to print these messages you are to use the provided write method in the `SushiBar` class. A call to the method may look something like this:
```java
𝑆𝑢𝑠ℎ𝑖𝐵𝑎𝑟.𝑤𝑟𝑖𝑡𝑒(𝑇ℎ𝑟𝑒𝑎𝑑.𝑐𝑢𝑟𝑟𝑒𝑛𝑡𝑇ℎ𝑟𝑒𝑎𝑑().𝑔𝑒𝑡𝑁𝑎𝑚𝑒() + “: 𝐶𝑢𝑠𝑡𝑜𝑚𝑒𝑟” + 𝑐𝑢𝑠𝑡𝑜𝑚𝑒𝑟𝑁𝑜 + “𝑖𝑠 𝑛𝑜𝑤 𝑐𝑟𝑒𝑎𝑡𝑒𝑑.”)`;
```

Please stick to this format and only print out statistics and the messages listed above.

**Note**: The last message should be used when all customers have left the Sushi Bar.

## Statistics

At the end of the simulation, you should print out some statistics.

The statistics should contain:
- Total number of orders
- Total number of takeaway orders
- Total number of orders that customers have eaten at the bar

## Note about orders
As mentioned, each customer has a choice to eat some orders in the bar and have the remaining as takeaway:

```java
𝑁𝑢𝑚𝑏𝑒𝑟𝑂𝑓𝑂𝑟𝑑𝑒𝑟𝑠 = 𝐸𝑎𝑡𝑒𝑛𝑂𝑟𝑑𝑒𝑟𝑠 + 𝑇𝑎𝑘𝑒𝑎𝑤𝑎𝑦𝑂𝑟𝑑𝑒𝑟𝑠
```

NumberOfOrders should be randomly generated for each customer, with the max being `SushiBar.maxOrder`.

## Requirements

Deliverables:

1. Your complete implementation of the Sushi bar (Source Code).
2. A document explaining your implementation. In addition, you have to address the following points:

    - What are the functionality of `𝑤𝑎𝑖𝑡()`, `𝑛𝑜𝑡𝑖𝑓𝑦()` and `𝑛𝑜𝑡𝑖𝑓𝑦𝐴𝑙𝑙()` and what is the difference between `𝑛𝑜𝑡𝑖𝑓𝑦()` and `𝑛𝑜𝑡𝑖𝑓𝑦𝐴𝑙𝑙()`?
    - Which variables are shared variables and what us your solution to manage them?
    - Which method or thread will report the final statistics and how will it recognize the proper time for writing these statistics?

> Note

- You should not kill a thread. Every thread should terminate normally, when it is finished with all of its tasks. So do not use any `𝑆𝑡𝑜𝑝` or `𝐸𝑥𝑖𝑡` methods.

- Try manipulating the settings of the sushi bar to make sure that your program works in all situations. These settings can be the number of seats, simulation time, frequency of generating customers and length of eating time.

- Correct statistics does not necessarily mean that your implementation is correct. You have to check all outputs of your program to make sure that everything works in your simulation.
