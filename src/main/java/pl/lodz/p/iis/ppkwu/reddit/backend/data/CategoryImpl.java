package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;

public class CategoryImpl implements Category {

	private final String name;
	private final String relativeUrl;

	public CategoryImpl(String name, String relativeUrl) {
		this.name = name;
		this.relativeUrl = relativeUrl;
	}

	@Override
	public String name() {
		return name;
	}

	public String relativeUrl() {
		return relativeUrl;
	}

}
