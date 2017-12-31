package padamara.java9.chapter1.loosecoupling;

public class SavingsAccount implements AccountInterface{
	long deposit;
	public SavingsAccount(){}

	@Override
	public long getDeposit(){
		return deposit;
	}
	@Override
	public void depositMoney(long amount){
		deposit=amount;
	}
}