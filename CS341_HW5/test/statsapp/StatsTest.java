package statsapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StatsTest {
	Calculator calc = new Calculator();

	private NumberFileReader read(String fileName) throws FileNotFoundException {
		NumberFileReader reader = new NumberFileReader();
		reader.readFile(new File("testdata/" + fileName));
		return reader;
	}

	@Test
	void testLinkedListAdd() {
		MyLinkedList list = new MyLinkedList();
		assertTrue(list.isEmpty());

		list.add(1);
		list.add(2.5);
		list.add(-3);

		assertEquals(3, list.getSize());
		assertEquals(0.5, list.getSum(), 0.0001);
		assertEquals("[1.0, 2.5, -3.0]", list.toString());
	}

	@Test
	void testMeanAndStdDev() throws FileNotFoundException {
		NumberFileReader reader = read("valid_numbers.txt");
		MyLinkedList nums = reader.getNumbers();

		double mean = calc.getMean(nums);
		assertEquals(8, nums.getSize());
		assertEquals(5.0, mean, 0.0001);
		assertEquals(2.1381, calc.getStdDev(nums, mean), 0.0001);
	}

	@Test
	void testInvalidInput() throws FileNotFoundException {
		NumberFileReader reader = read("invalid_input.txt");

		assertEquals(3, reader.getNumbers().getSize());
		assertEquals(1, reader.getBadLines().size());
		assertEquals("Line 2: 3b4", reader.getBadLines().get(0));
		assertEquals(2.0, calc.getMean(reader.getNumbers()), 0.0001);
	}

	@Test
	void testEmptyFile() throws FileNotFoundException {
		NumberFileReader reader = read("empty_file.txt");

		assertEquals(0, reader.getTotalLines());
		assertTrue(reader.getNumbers().isEmpty());
		assertEquals(0, calc.getMean(reader.getNumbers()), 0.0001);
	}

	@Test
	void testOneLine() throws FileNotFoundException {
		NumberFileReader reader = read("one_line.txt");
		MyLinkedList nums = reader.getNumbers();

		assertEquals(1, nums.getSize());
		assertEquals(12.5, calc.getMean(nums), 0.0001);
		assertEquals(0, calc.getStdDev(nums, 12.5), 0.0001);
	}

	@Test
	void testBlankLinesInMiddle() throws FileNotFoundException {
		NumberFileReader reader = read("blank_lines_middle.txt");
		MyLinkedList nums = reader.getNumbers();

		assertEquals(8, nums.getSize());
		assertEquals(3, reader.getBlankLines());
		assertEquals(0, reader.getBadLines().size());

		double mean = calc.getMean(nums);
		assertEquals(5.0, mean, 0.0001);
		assertEquals(2.1381, calc.getStdDev(nums, mean), 0.0001);
	}

	@Test
	void testMixedFile() throws FileNotFoundException {
		NumberFileReader reader = read("mixed_input.txt");
		MyLinkedList nums = reader.getNumbers();

		assertEquals(5, nums.getSize());
		assertEquals(5, reader.getBadLines().size());
		assertEquals(1, reader.getBlankLines());
		assertEquals(39.95, calc.getMean(nums), 0.0001);
	}

	@Test
	void testFileNotFound() {
		assertThrows(FileNotFoundException.class, () -> read("not_a_real_file.txt"));
	}
}
