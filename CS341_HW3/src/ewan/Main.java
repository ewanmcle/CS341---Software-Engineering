package ewan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Main {

	private JFrame frame;
	private JTextField itemTextField;
	private JTextField costTextField;
	private JTextField quantityTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
		
		JLabel itemLabel = new JLabel("Item:");
		itemLabel.setBounds(51, 29, 61, 16);
		frame.getContentPane().add(itemLabel);
		
		itemTextField = new JTextField();
		itemTextField.setBounds(124, 24, 225, 26);
		frame.getContentPane().add(itemTextField);
		itemTextField.setColumns(10);
		
		JLabel costLabel = new JLabel("Cost: $");
		costLabel.setBounds(39, 57, 61, 16);
		frame.getContentPane().add(costLabel);
		
		costTextField = new JTextField();
		costTextField.setBounds(124, 52, 130, 26);
		frame.getContentPane().add(costTextField);
		costTextField.setColumns(10);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setBounds(39, 85, 61, 16);
		frame.getContentPane().add(quantityLabel);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(124, 80, 130, 26);
		frame.getContentPane().add(quantityTextField);
		quantityTextField.setColumns(10);
		
		SalesSlip salesSlip = new SalesSlip();
		JButton addItemBtn = new JButton("Add Item to Sales List");
		addItemBtn.setBounds(95, 113, 254, 29);
		frame.getContentPane().add(addItemBtn);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(51, 152, 345, 98);
		frame.getContentPane().add(textArea);

		addItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String name = itemTextField.getText();
                double price = Double.valueOf(costTextField.getText());
                int quantity = Integer.valueOf(quantityTextField.getText());
                salesSlip.add(name, quantity, price);
                textArea.cut();
                textArea.setText(salesSlip.toString());
			}
		});
		
	}
}
