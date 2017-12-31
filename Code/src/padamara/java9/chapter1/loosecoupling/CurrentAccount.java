package padamara.java9.chapter1.loosecoupling;

public class CurrentAccount implements AccountInterface{
	long deposit;
	public CurrentAccount(){}

	@Override
	public long getDeposit(){
		return deposit;
	}
	@Override
	public void depositMoney(long amount){
		deposit=amount;
	}
}