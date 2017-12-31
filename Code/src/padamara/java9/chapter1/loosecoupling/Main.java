package padamara.java9.chapter1.loosecoupling;

public class Main{
	public static void main(String args[]){
		CurrentAccount currentAccount=new CurrentAccount();
		Customer firstCustomer=new Customer(currentAccount);
		firstCustomer.deposit(10);

		DepositAccount depositAccount=new DepositAccount();
		Customer secondCustomer=new Customer(depositAccount);
		secondCustomer.deposit(100);

		SavingsAccount savingsAccount=new SavingsAccount();
		Customer thridCustomer=new Customer(savingsAccount);
		thridCustomer.deposit(200);

		System.out.println("First Customer current account amount:"+firstCustomer.getAccount().getDeposit());
		System.out.println("Second Customer current account amount:"+secondCustomer.getAccount().getDeposit());
		System.out.println("Third Customer current account amount:"+thridCustomer.getAccount().getDeposit());
	}
}