package com.google.code.atpfx;

import java.util.MissingResourceException;

//account details you would use to connect to the platform
public class AccountLoginDetails {
	
//
	private String userName = "";
	private String password = "";
	private String accountType = "Demo"; // an account is coupled with a signal provider
	private String signalProviderLabel = "FXCM-DEMO";
	
	//receive an index and search "username<index>" and "password<index>" in
	//GlobalProperties.FXCM_ACCOUNTS_PROPERTIES_FILE property file
	private void AccountLoginDetailsInit(int index){
		try {
			this.userName = GlobalProperties.getInstance().getAccountsProperty("username" + index);
			this.password = GlobalProperties.getInstance().getAccountsProperty("password" + index);
		} catch (MissingResourceException e) {
			//here when caller does not know if username<index>
			//exists in global property file
			//e.printStackTrace();
		} 
	}
	
	//the AccountLoginDetails default constructor takes the first account 
	//username and password available in a global properties file
	public AccountLoginDetails() {		
		//there must be at least one username/password in
		//GlobalProperties.FXCM_ACCOUNTS_PROPERTIES_FILE, 
		//with index zero these are considered 
		//the master username/password
		AccountLoginDetailsInit(0);
	}

	//TODO: more logins at http://www.forextrading.com.au/free-trading-account.jsp
	public AccountLoginDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;
		// TODO: take care of account type
	}

	public AccountLoginDetails(int index) {
		AccountLoginDetailsInit(index);		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getSignalProviderLabel() {
		return signalProviderLabel;
	}
}