package statsapp;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class StatsGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblFile;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsGUI frame = new StatsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StatsGUI() {
		setTitle("Mean and Standard Deviation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		btnChooseFile.setBounds(10, 11, 120, 25);
		contentPane.add(btnChooseFile);

		lblFile = new JLabel("No file selected");
		lblFile.setBounds(140, 11, 330, 25);
		contentPane.add(lblFile);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 464, 303);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
	}

	private void chooseFile() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);

		if (result != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = chooser.getSelectedFile();
		lblFile.setText(file.getName());

		NumberFileReader reader = new NumberFileReader();
		try {
			reader.readFile(file);
		} catch (FileNotFoundException ex) {
			textArea.setText("Error: couldn't open " + file.getName());
			return;
		}

		textArea.setText(makeResults(reader));
	}

	private String makeResults(NumberFileReader reader) {
		MyLinkedList numbers = reader.getNumbers();
		String output = "";

		if (reader.getTotalLines() == 0) {
			return "The file is empty.";
		}

		if (numbers.isEmpty()) {
			output += "No valid numbers were found in the file.\n";
		} else {
			Calculator calc = new Calculator();
			double mean = calc.getMean(numbers);

			output += "Numbers read: " + numbers.getSize() + "\n";
			output += String.format("Mean: %.4f\n", mean);

			if (numbers.getSize() < 2) {
				output += "Standard Deviation: N/A (need at least 2 numbers)\n";
			} else {
				double stdDev = calc.getStdDev(numbers, mean);
				output += String.format("Standard Deviation: %.4f\n", stdDev);
			}
		}

		output += "\nBlank lines skipped: " + reader.getBlankLines() + "\n";
		output += "Invalid lines skipped: " + reader.getBadLines().size() + "\n";
		for (String bad : reader.getBadLines()) {
			output += "   " + bad + "\n";
		}

		return output;
	}
}
