package mc.core.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Disposer implements Disposable {

	private List<Disposable> references; 
	
	public Disposer() {
		this.references = new ArrayList<>();
	}
	
	public Disposer(Disposable... references) {
		this.references = Arrays.asList(references);
	}
	
	public Disposer Add(Disposable disposable) {
		this.references.add(disposable);
		return this;
	}
	
	@Override
	public void dispose() {
		for (Disposable disposable : references) {
			disposable.dispose();
		}
		this.references.clear();
	}

}
