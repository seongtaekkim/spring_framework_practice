package com.taek.calculator.originLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	
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
}
