package model;

public class PrivateBankTransfer extends BankTransfer{
	
	public PrivateBankTransfer() {}
	
	public PrivateBankTransfer(String fromIBAN, String toIBAN) {
		super.setFromIBAN(fromIBAN);
		super.setToIBAN(toIBAN);
	}

	@Override
	public boolean makeAuthorization(int code) {
		// TODO
		//make authorization - fingerprint
		if(code > 0) {
			return true;
		}
		return false;
	}
	
}
