package model;

public class ProtectedBankTransfer extends BankTransfer {

	public  ProtectedBankTransfer() {
	}
	
	public  ProtectedBankTransfer(String fromIBAN, String toIBAN) {
		super.setFromIBAN(fromIBAN);
		super.setToIBAN(toIBAN);
	}
	
	@Override
	public boolean makeAuthorization(int code) {
		// TODO
		// make authorization - pin code :
		if(code > 0) {
			return true;
		}
		
		return false;
	}
	
}
