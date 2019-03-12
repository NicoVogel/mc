package mc.core.event.list;

import java.util.List;

import mc.core.event.EventProvider;

public interface ObservableList<E> extends List<E> {

	EventProvider<E> OnAdd();
	
	EventProvider<E> OnRemove();
	
	EventProvider<E> OnAddCollection();
	
	EventProvider<E> OnRemoveCollection();
	
	EventProvider<E> OnSet();
	
}
