package calculator.templateCallbackLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback,T initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		}catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}	
	}

	public String concatenate(String filepath) throws IOException {
		LineCallback<String> concatenateCallback =
				new LineCallback<String>() {
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		return lineReadTemplate(filepath, concatenateCallback, "");		
	}
}
