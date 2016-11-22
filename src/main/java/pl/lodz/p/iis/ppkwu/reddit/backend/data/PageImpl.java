package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import java.util.List;

import pl.lodz.p.iis.ppkwu.reddit.api.Page;

public class PageImpl<C> implements Page<C> {

	private final List<C> content;

	public PageImpl(List<C> content) {
		this.content = content;
	}

	@Override
	public List<C> content() {
		return content;
	}

}