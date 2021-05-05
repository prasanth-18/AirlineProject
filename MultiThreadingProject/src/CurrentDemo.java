
public class CurrentDemo implements Runnable{
	public void run() {
		Thread t = Thread.currentThread();      //to find current executing thread information and current thread is a static method 
	    System.out.println(t);
	}

	public static void main(String[] args) {
		CurrentDemo rd = new CurrentDemo();
		Thread r = new Thread(rd);
		r.start();
		Thread t = Thread.currentThread();
		 System.out.println(t);
	

	}

}
