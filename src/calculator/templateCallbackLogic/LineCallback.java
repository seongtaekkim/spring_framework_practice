package calculator.templateCallbackLogic;

public interface LineCallback<T> {
	public T doSomethingWithLine(String line, String value);
}
