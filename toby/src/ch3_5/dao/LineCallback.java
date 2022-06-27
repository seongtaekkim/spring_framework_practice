package ch3_5.dao;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}
