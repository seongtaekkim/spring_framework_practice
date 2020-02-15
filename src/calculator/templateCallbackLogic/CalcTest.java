package calculator.templateCallbackLogic;

import org.junit.Before;
import org.junit.Test;


public class CalcTest {
	private String numFilepath;
	
	@Before
	public void setUp() {
		this.numFilepath = getClass().getResource("../data/numbers.txt").getPath();
	}
	@Test
	public void sumOfNumbers() {
		System.out.println(numFilepath);
	}
}
