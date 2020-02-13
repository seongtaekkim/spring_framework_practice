package ch3_6.dao;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}
