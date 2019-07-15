package mc.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mc.core.event.interfaces.Disposable;

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
