public class AccountSyncTest {
	public static void main(String[] args) {
		BankAccount ba = new BankAccount(101,"Suresh",50000);
		System.out.println("Before deposits : ba : "+ba);//it invoked toString()
		
		Teller teller1 = new Teller("Medha",ba,5000);
		Teller teller2 = new Teller("\tSwetha",ba,7000);
		Teller teller3 = new Teller("\t\tMarina",ba,8000);
		
		teller1.start();
		teller2.start();
		teller3.start();
		try {
			teller1.join();
			teller2.join();
			teller3.join();
		}catch(InterruptedException e) { } 
		
		System.out.println("After deposits : ba : "+ba);//it invoked toString()
		
	}
}
class BankAccount {
	private int accNumber;
	private String accHolder;
	private double accBalance;
	
	BankAccount(int accNumber, String accHolder, double accBalance) {
		super();
		this.accNumber = accNumber;
		this.accHolder = accHolder;
		this.accBalance = accBalance;
	}
	public String toString() { //overridden method
	    return accNumber+" "+accHolder+" "+accBalance;
	}
	public void showBankAccount() {
		System.out.println("Bank Acc Num    : "+accNumber);
		System.out.println("Bank Acc Holder : "+accHolder);
		System.out.println("Bank Acc Balance: "+accBalance);
	}
	
	//means allow only one thread to enter in this method
	synchronized void deposit(String tn, double amt) {
		System.out.println(tn+" : Depositing...."+amt);
		double currentBalance = getBalance(tn);
		System.out.println(tn+" : Balance got : "+currentBalance);
		
		System.out.println(tn+" : Calculating...");
		double newBalance = currentBalance + amt;
		System.out.println(tn+" : Calculated...");
		
		setBalance(tn,newBalance);
		System.out.println(tn+" : Balance is set..."+accBalance);
	}
	
	//assume below methods are dealing with DATABASE
	private double getBalance(String t) {
		System.out.println(t+" : Trying to read the balance...");
		try { Thread.sleep(400); } catch(InterruptedException e) { }
		return accBalance;
	}
	private void setBalance(String t, double amt) {
		System.out.println(t+" : Trying to set the balance...");
		try { Thread.sleep(400); } catch(InterruptedException e) { }
		accBalance = amt;
	}
}

class Teller extends Thread //Method A
{
	String tellerName;
	BankAccount baRef; //hasA
	double amountToDeposit;
	
	Teller(String telName, BankAccount x, double amtToDepo) {
		tellerName = telName;
		baRef = x;
		amountToDeposit = amtToDepo;
	}
	public void run() {
		//System.out.println(tellerName+" : started depositing..."+amountToDeposit);
		baRef.deposit(tellerName, amountToDeposit);
	}
}