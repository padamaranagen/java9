package padamara.java9.chapter1.loosecoupling;

public class Customer{
	private AccountInterface account;
	public Customer(AccountInterface account){
		this.account=account;
	}
	public void deposit(long amount){
		account.depositMoney(amount);
	}
	public AccountInterface getAccount(){
		return account;
	}
}