package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import java.util.List;
import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.PageImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.ImmutableListBuilder;

public class PageBuilder<C> implements Builder<PageImpl<C>> {

	private ImmutableListBuilder<C> contentBuilder = new ImmutableListBuilder<>();

	public PageBuilder<C> withContent(List<C> content) {
		contentBuilder.withEditableList(content);
		return this;
	}

	public List<C> content() {
		return contentBuilder.editableList();
	}

	public PageBuilder<C> addEntry(C entry) {
		Objects.requireNonNull(entry);
		contentBuilder.addEntry(entry);
		return this;
	}

	@Override
	public PageImpl<C> build() {
		List<C> immutableContent = contentBuilder.build();
		return new PageImpl<>(immutableContent);
	}

}
