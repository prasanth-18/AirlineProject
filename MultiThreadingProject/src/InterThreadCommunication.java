
public class InterThreadCommunication{
	public static void main(String[] args) {
		FoodItem food = new FoodItem("Idli");
		Producer prod = new Producer(food);
		Consumer cons = new Consumer(food);
		prod.start();
		cons.start();
	}
}
class FoodItem
{
	String foodItemName;
	boolean isItemServed; //false
	
	FoodItem(String name) {
		foodItemName = name;
	}
	
	//remember that the wait/notify must be called from synchronized methods
	//else it will throw IllegalMonitorStateException
	
	public synchronized void produce() { //<-- Producer's run() is calling this method
		if(isItemServed==false) {
			System.out.println("Producer : Producing/serving food item..."+foodItemName);
			isItemServed=true;
			System.out.println("Producer : Food Item served...");
			notify(); //inherited method from Object 
		}
	}
	
	public synchronized void consume() { //<--Consumer's run() is calling this method
		if(isItemServed==true) {
			System.out.println("Consumer : Consuming..."+foodItemName);
		}
		else {
			System.out.println("Consumer: Waiting for food item to be Served...");
			try {
				wait(); // inherited method from Object class
				System.out.println("Consumer : Now Consuming..."+foodItemName);
			}
			catch(InterruptedException e) {
				System.out.println("Consumer : Some problem : "+e);
			}
		}
	}
}

class Producer extends Thread
{
	FoodItem foodItemRef;
	Producer (FoodItem ref) {
		foodItemRef = ref;
	}
	public void run() {
		foodItemRef.produce();
	}
}
class Consumer extends Thread
{
	FoodItem foodItemRef;
	Consumer (FoodItem ref) {
		foodItemRef = ref;
	}
	public void run() {
		foodItemRef.consume();
	}
}