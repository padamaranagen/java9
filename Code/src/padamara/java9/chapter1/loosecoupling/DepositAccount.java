package padamara.java9.chapter1.loosecoupling;

public class DepositAccount implements AccountInterface{
	long deposit;
	public DepositAccount(){}

	@Override
	public long getDeposit(){
		return deposit;
	}
	@Override
	public void depositMoney(long amount){
		deposit=amount;
	}
}