package nate.master.com.interfaces;

import java.util.ArrayList;

public interface iManager<T> {
	public int Add(T in);
	public T get(int i);
	
	public void Rem(T in);
	public void Rem(int i);
	
	public void proccess();
	public void iterateAll();
}
