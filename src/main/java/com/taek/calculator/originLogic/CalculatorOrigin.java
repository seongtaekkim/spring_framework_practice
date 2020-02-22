package com.taek.calculator.originLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculatorOrigin {
	
	/*
	 * 1. buffer 에 파일을 담는다.
	 * 2. 한줄 씩 읽으며 숫자를 더한다
	 * 3. 예외처리 소스를 추가한다.
	 * 4. 로직 종료 후 자원을 종료한다.
	 */
	public Integer sumCal(String filepath) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			Integer sum = 0;
			String line = null;
			while ((line = br.readLine()) != null ) {
				sum += Integer.valueOf(line);
			}
			return sum;
		} catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			try {
				br.close();
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public Integer sumCal(String a, String b, String operator) {
		Integer sum = 0;
		try {
			sum = Integer.valueOf(a) + Integer.valueOf(b);
			return sum;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public Integer multiCal(String a, String b, String operator) {
		Integer multi = 0;
		try {
			multi = Integer.valueOf(a) * Integer.valueOf(b);
			return multi;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public Integer subCal(String a, String b, String operator) {
		Integer sub = 0;
		try {
			sub = Integer.valueOf(a) - Integer.valueOf(b);
			return sub;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public Integer divCal(String a, String b, String operator) throws Exception {
		Integer div = 0;
		try {
			div = Integer.valueOf(a) / Integer.valueOf(b);
			return div;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	public Double calculate(String a, String b, String operator) throws Exception {
		Double res = 0.0;
		try {
			if("+".equals(operator)) {
				res = Double.valueOf(a) + Double.valueOf(b);
			} else if("-".equals(operator)) {
				res = Double.valueOf(a) - Double.valueOf(b);
			}  else if("*".equals(operator)) {
				res = Double.valueOf(a) * Double.valueOf(b);
			}  else if("/".equals(operator)) {
				res = Double.valueOf(a) / Double.valueOf(b);
			} else {
				
			}
			//System.out.println("res : " + res);
			return res;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
