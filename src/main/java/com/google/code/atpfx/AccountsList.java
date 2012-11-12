package com.google.code.atpfx;

import java.util.ArrayList;

// list of accounts
public class AccountsList {

	private ArrayList<AccountLoginDetails> accountsList;

	public AccountsList(){
		accountsList = new ArrayList<AccountLoginDetails>();
		this.populateAccountsList();
	}
	
	// populate list of accounts
	private void populateAccountsList() {

		int count = 0; 
		AccountLoginDetails tempALD = new AccountLoginDetails(count);
		
		do {
			accountsList.add(tempALD);
			tempALD = new AccountLoginDetails(++count);
		} while ((null != tempALD.getPassword()) && (! "".equalsIgnoreCase(tempALD.getUserName())));
	}

	public ArrayList<AccountLoginDetails> getAccountsList() {
		return accountsList;
	}
}
