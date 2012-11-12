/**
 * 
 */
package com.google.code.atpfx.data;

/**
 * @author Andrei
 *
 */
public class SignalProvider {
	
	private long id;
	private String label;
	private String server;
	
	public SignalProvider() {}

	public long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	

}
