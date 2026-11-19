package statsapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NumberFileReader {
	private MyLinkedList numbers;
	private ArrayList<String> badLines;
	private int blankLines;
	private int totalLines;

	public NumberFileReader() {
		numbers = new MyLinkedList();
		badLines = new ArrayList<String>();
		blankLines = 0;
		totalLines = 0;
	}

	public void readFile(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);

		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			totalLines++;

			if (line.isEmpty()) {
				blankLines++;
				continue;
			}

			try {
				double num = Double.parseDouble(line);

				if (Double.isNaN(num) || Double.isInfinite(num)) {
					badLines.add("Line " + totalLines + ": " + line);
				} else {
					numbers.add(num);
				}
			} catch (NumberFormatException e) {
				badLines.add("Line " + totalLines + ": " + line);
			}
		}

		scan.close();
	}

	public MyLinkedList getNumbers() {
		return numbers;
	}

	public ArrayList<String> getBadLines() {
		return badLines;
	}

	public int getBlankLines() {
		return blankLines;
	}

	public int getTotalLines() {
		return totalLines;
	}
}
