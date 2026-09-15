package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class PW_Checker {

	private JFrame frame;
	private JPasswordField password;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PW_Checker window = new PW_Checker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PW_Checker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(179, 20, 69, 16);
		frame.getContentPane().add(lblNewLabel);
		
		password = new JPasswordField();
		password.setBounds(123, 51, 186, 26);
		frame.getContentPane().add(password);
		
		JButton btnNewButton = new JButton("Check Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Password pw = new Password(password.getPassword());
                textPane.setText(pw.testPassword());
			}
		});
		
		textPane = new JTextPane();
		textPane.setBounds(6, 128, 438, 138);
		frame.getContentPane().add(textPane);
		btnNewButton.setBounds(145, 89, 146, 29);
		frame.getContentPane().add(btnNewButton);
	}
}
