package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}

	@Test
	public void test1() {
		// test for input that does not contain any ignore words
		inputLineStorage.addLine("A Beautiful Day");
		assertEquals(3, afterShiftLineStorage.size());
		assertEquals("A Beautiful Day", afterShiftLineStorage.get(0).toString());
		assertEquals("Beautiful Day A", afterShiftLineStorage.get(1).toString());
		assertEquals("Day A Beautiful", afterShiftLineStorage.get(2).toString());
	}

	@Test
	public void test2() {
		// test for input that contains all ignore words
		inputLineStorage.addLine("After The Rain");
		assertEquals(1, afterShiftLineStorage.size());
		assertEquals("Rain after the", afterShiftLineStorage.get(0).toString());
	}

}
