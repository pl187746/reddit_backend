package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ImmutableListBuilder<E> implements Builder<List<E>> {

	private List<E> editableList = new ArrayList<>();
	private List<E> cachedImmutableList = null;

	private void invalidateCachedList() {
		cachedImmutableList = null;
	}

	public ImmutableListBuilder<E> withEditableList(List<E> editableList) {
		this.editableList = Objects.requireNonNull(editableList);
		invalidateCachedList();
		return this;
	}

	public List<E> editableList() {
		invalidateCachedList();
		return editableList;
	}

	public ImmutableListBuilder<E> addEntry(E entry) {
		editableList().add(entry);
		return this;
	}

	@Override
	public List<E> build() {
		return getImmutableList();
	}

	private List<E> getImmutableList() {
		if(cachedImmutableList == null) {
			cachedImmutableList = createImmutableList();
		}
		return cachedImmutableList;
	}

	private List<E> createImmutableList() {
		return editableList.isEmpty() ? Collections.emptyList() : convertListToImmutable();
	}

	private List<E> convertListToImmutable() {
		List<E> listCopy = new ArrayList<>(editableList);
		return Collections.unmodifiableList(listCopy);
	}

}
