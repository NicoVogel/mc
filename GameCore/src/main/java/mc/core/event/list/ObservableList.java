package mc.core.event.list;

import java.util.Collection;
import java.util.List;

import mc.core.event.EventProvider;
import mc.core.event.OldNewEvent;

public interface ObservableList<E> extends List<E> {

	/**
	 * get the added element
	 * @return
	 */
	EventProvider<E> OnAdd();
	
	/**
	 * get the removed element
	 * @return
	 */
	EventProvider<E> OnRemove();
	
	/**
	 * get all added elements
	 * @return
	 */
	EventProvider<Collection<? extends E>> OnAddCollection();
	
	/**
	 * get all removed elements
	 * @return
	 */
	EventProvider<Collection<? extends E>> OnRemoveCollection();
	
	/**
	 * get the old and new element
	 * @return
	 */
	EventProvider<OldNewEvent<E>> OnSet();
	
}
