package com.google.code.atpfx.swing;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.google.code.atpfx.FXCMConnection;
import com.google.code.atpfx.IConnection;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private String localSigProviderType = "FXCM-DEMO";
	private IConnection fxcmConnection;
	private JLabel l1;
	private JTextField t1;
	private JButton b1;

	public Main(){
		//open a connection of FXCM-DEMO type
		//save the data to the database
		//put everything on a swing interface
		//with a button to close signal provider connection
		//and to end the program when the data from the
		//signal provider queue has been all put in the database
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.out.println("Closing "+ localSigProviderType +" Connection");
				if (fxcmConnection != null){
					if (fxcmConnection.isOpened()){
						fxcmConnection.close();
						System.out.println(localSigProviderType+" connection closed.");
					}
				}
			}
		});
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		l1 = new JLabel("Signal Provider Type:");
		cp.add(l1);
		t1 = new JTextField("FXCM-DEMO",20);
		cp.add(t1);
		b1 = new JButton("Open Signal Provider Connection");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( t1.getText().equalsIgnoreCase(localSigProviderType)){
					System.out.println("Opening "+localSigProviderType+" Connection");
					fxcmConnection = new FXCMConnection();
					fxcmConnection.open();
				} else {
					System.out.println("Only " + localSigProviderType
							+ " accepted currently.");;
				}
			}
		});
		cp.add(b1);
	}
	
	public static void main(String[] args){
		Starter.run(new Main(), 300, 300);
	}
}
