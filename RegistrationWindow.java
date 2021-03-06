/*
 * Author: Fahad Abunayyan
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class RegistrationWindow extends JFrame implements ActionListener
{
	
	JLabel lblIntro = new JLabel("Please enter a new Username and Password:");
	
	JLabel lblUsername = new JLabel("New Username:");
	JTextField txtUsername = new JTextField(10);
	
	JLabel lblPassword = new JLabel("New Password:");
	JPasswordField txtPassword = new JPasswordField(10);
	
	JLabel lblRepeatPassword = new JLabel("Re-enter Password:");
	JPasswordField txtPasswordAgain = new JPasswordField(10);
	
	JButton btnCancel = new JButton("Cancel");
	JButton btnOK = new JButton("OK");
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p0 = new JPanel();
	
	RegistrationWindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400, 200);
		this.setLayout(new FlowLayout());
		this.setSize(340, 410);
		this.setResizable(false);
		
		lblIntro.setFont(new Font("Tahoma", Font.PLAIN, 16)); // change the font and size 
		
//		add(new JLabel(new ImageIcon("cm.png"))); // for adding an image
		
		
		this.getContentPane().setBackground(Color.WHITE);
		
		p1.setBackground(Color.WHITE);
		p1.add(lblIntro);
		
		p2.setLayout(new GridLayout(3,2));
		p3.setLayout(new GridLayout(1,2));
		
		p2.setBackground(Color.WHITE);
		p3.setBackground(Color.WHITE);
		
		p2.add(lblUsername);
		p2.add(txtUsername);
		p2.add(lblPassword);
		p2.add(txtPassword);
		p2.add(lblRepeatPassword);
		p2.add(txtPasswordAgain);		
		p3.add(btnCancel);
		p3.add(btnOK);
		
		
		this.btnCancel.addActionListener(this);
		this.btnOK.addActionListener(this);
		add(p1);
		add(p2);
		add(p3);
		
		
		
//		add(p4);
		
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		switch(ae.getActionCommand())
		{
		case "Cancel":
			this.dispose();	// kill window
			this.setVisible(false); // set it to invisible 
		break;
		
		case "OK":
			char[] password = txtPassword.getPassword();
			char[] verifyPassword = txtPasswordAgain.getPassword();
			String passwordString = new String(password);
			String verifyPasswordString = new String(verifyPassword);
			if(passwordString.equals(verifyPasswordString)){
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("USERLIST.txt", true)));
					out.print(txtUsername.getText() + ";");
					out.print(passwordString);
					out.println();
					out.close();
            	}
		        catch (FileNotFoundException e) {
		            System.out.print("FileNotFoundException: ");
		            System.out.println(e.getMessage());
        		}
        		catch (IOException ie) {
        			System.out.print("IOException: ");
		            System.out.println(ie.getMessage());
        		}
        		LoginWindow x = new LoginWindow(); 	// open the Login window 
        		x.setVisible(true);
				this.dispose();
				this.setVisible(false);	// set the current frame to invisible 
				System.out.println("OK");
			}else{
				JOptionPane.showMessageDialog(null, "Passwords do not match.");
			}
		}
	}
}
