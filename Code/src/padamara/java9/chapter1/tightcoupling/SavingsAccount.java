package padamara.java9.chapter1.tightcoupling;

public class SavingsAccount{
	long deposit;

	public void depositMoney(long amount){
		deposit=amount;
	}
	public long getDeposit(){
		return deposit;
	}
}