// use this class to store the details of data.
public class BondTrade {
	private double yeild = 0.0;
	private int days = 0;
	private int amount = 0;

	public BondTrade() {

	}
	// flexible constructor
	public BondTrade(double yeild, int days, int amount) {
		setYeild(yeild);
		setDays(days);
		setAmount(amount);
	}

	public void setYeild(double yeild) {
		this.yeild = yeild;
	}

	public double getYeild() {
		return yeild;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
