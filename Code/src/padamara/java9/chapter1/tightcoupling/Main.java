package padamara.java9.chapter1.tightcoupling;

public class Main{
	public static void main(String args[]){
		Customer firstCustomer=new Customer();
		firstCustomer.depositMoneyIntoCurrentAccount(50);

		Customer secondCustomer=new Customer();
		secondCustomer.depositMoneyIntoDepositAccount(100);

		Customer thridCustomer=new Customer();
		thridCustomer.depositMoneyIntoSavingsAccount(200);

		System.out.println("First Customer current account amount:"+firstCustomer.getCurrentAccount().getDeposit());
		System.out.println("Second Customer current account amount:"+secondCustomer.getDepositAccount().getDeposit());
		System.out.println("Third Customer current account amount:"+thridCustomer.getSavingsAccount().getDeposit());

	}
}
