package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyApp {

	private JFrame frame;
	private JTextField letters;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyApp window = new MyApp();
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
	public MyApp() {
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
		
		JLabel instructions = new JLabel("Enter 7 Scrabble letters:");
		instructions.setBounds(147, 29, 149, 16);
		frame.getContentPane().add(instructions);
		
		letters = new JTextField();
		letters.setBounds(157, 57, 130, 26);
		frame.getContentPane().add(letters);
		letters.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(94, 171, 254, 64);
		frame.getContentPane().add(scrollPane);
		
		JTextPane outputArea = new JTextPane();
		outputArea.setEditable(false);
		scrollPane.setViewportView(outputArea);
		
		JButton findCombos = new JButton("FIND COMBOS");
		findCombos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scrabbler scrabbler = new Scrabbler(letters.getText());
				outputArea.setText(scrabbler.scrabblinator());
			}
		});
		findCombos.setBounds(167, 106, 117, 29);
		frame.getContentPane().add(findCombos);
	}
}
