package mc.core.event.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.event.OldNewValueEvent;

public class ObservableListImpl<E> implements ObservableList<E> {

	private List<E> list;

	private Event<E> onAdd;
	private Event<E> onRemove;
	private Event<Collection<? extends E>> onAddAll;
	private Event<Collection<? extends E>> onRemoveAll;
	private Event<OldNewValueEvent<E>> onSet;

	public ObservableListImpl(List<E> list) {
		this.list = list;
		this.onAdd = new Event<>();
		this.onRemove = new Event<>();
		this.onAddAll = new Event<>();
		this.onRemoveAll = new Event<>();
		this.onSet = new Event<>();
	}

	public ObservableListImpl() {
		this(new ArrayList<>());
	}

	@Override
	public boolean add(E arg0) {
		if (this.list.add(arg0)) {
			this.onAdd.invoke(this, arg0);
			return true;
		}
		return false;
	}

	@Override
	public void add(int arg0, E arg1) {
		this.list.add(arg0, arg1);
		this.onAdd.invoke(this, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		if (this.addAll(arg0)) {
			this.onAddAll.invoke(this, arg0);
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		if (this.list.addAll(arg0, arg1)) {
			this.onAddAll.invoke(this, arg1);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		Collection<? extends E> elements = this.list;
		this.list.clear();
		if (elements.isEmpty() == false) {
			this.onRemoveAll.invoke(this, elements);
		}
	}

	@Override
	public boolean contains(Object arg0) {
		return this.list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.list.containsAll(arg0);
	}

	@Override
	public E get(int arg0) {
		return this.list.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return this.list.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return this.list.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<E> listIterator() {
		return this.list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		return this.list.listIterator(arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object arg0) {
		if (this.list.remove(arg0)) {
			this.onRemove.invoke(this, (E) arg0);
			return true;
		}
		return false;
	}

	@Override
	public E remove(int arg0) {
		E element = this.list.remove(arg0);
		this.onRemove.invoke(this, element);
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> arg0) {
		ArrayList<E> removed = new ArrayList<>();
		boolean hasChanged = false;
		for (Object obj : arg0) {
			if (this.list.remove(obj)) {
				removed.add((E) obj);
				hasChanged = true;
			}
		}
		this.onRemoveAll.invoke(this, removed);
		return hasChanged;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.list.retainAll(arg0);
	}

	@Override
	public E set(int arg0, E arg1) {
		E value = this.list.set(arg0, arg1);
		this.onSet.invoke(this, new OldNewValueEvent<E>(value, arg1));
		return value;
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		return this.list.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.list.toArray(arg0);
	}

	@Override
	public EventProvider<E> OnAdd() {
		return this.onAdd.getProvider();
	}

	@Override
	public EventProvider<E> OnRemove() {
		return this.onRemove.getProvider();
	}

	@Override
	public EventProvider<Collection<? extends E>> OnAddCollection() {
		return this.onAddAll.getProvider();
	}

	@Override
	public EventProvider<Collection<? extends E>> OnRemoveCollection() {
		return this.onRemoveAll.getProvider();
	}

	@Override
	public EventProvider<OldNewValueEvent<E>> OnSet() {
		return this.onSet.getProvider();
	}

}
