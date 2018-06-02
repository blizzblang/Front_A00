package nate.master.com.abstracts;

import java.util.ArrayList;

public abstract class ObjectStack<T> {
	protected ArrayList<T> store;
	protected ArrayList<T> ad;
	protected ArrayList<T> re;
	
	public ObjectStack(){
		store = new ArrayList<T>();
		ad=new ArrayList<T>();
		re=new ArrayList<T>();
	}
	public void sync() {
		if(ad != null) {
			store.addAll(ad);
			ad.clear();
		}
			if(re!=null) {
			store.removeAll(re);
			re.clear();
		}
	}

	public void add(T a) {ad.add(a);}
	public void rem(T a) {re.add(a);}
	public abstract void update();
	public ArrayList<T> getArray() {return store;}
}
