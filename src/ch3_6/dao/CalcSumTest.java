package ch3_6.dao;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilepath;
	
	@Before
	public void setUp(){
		this.calculator = new Calculator();
		this.numFilepath = getClass().getResource("numbers.txt").getPath();
	}
	@Test
	public void sumOfNumbers() {
		
	}
}
