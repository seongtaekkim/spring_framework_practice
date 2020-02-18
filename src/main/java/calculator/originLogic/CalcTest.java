package calculator.originLogic;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


public class CalcTest {
	private Calculator calculator;
	private String numFilepath;
	
	@Before
	public void setUp() {
		calculator = new Calculator();
		this.numFilepath = getClass().getResource("../data/numbers.txt").getPath();
	}
	@Test
	public void sumOfNumbers() throws IOException {
		System.out.println(numFilepath);
		System.out.println(calculator.sumCal(numFilepath));
	}
}
