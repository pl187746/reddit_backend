package pl.lodz.p.iis.ppkwu.reddit.backend;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;

public class CategoryImpl implements Category {

	private final String name;

	public CategoryImpl(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

}
