package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;

public class CategoryImpl implements Category {

	private final String name;
	private final String relativeUrl;

	public CategoryImpl(String name, String relativeUrl) {
		this.name = Objects.requireNonNull(name, "name");
		this.relativeUrl = Objects.requireNonNull(relativeUrl, "relativeUrl");
		checkInvocation();
	}

	@Override
	public String name() {
		return name;
	}

	public String relativeUrl() {
		return relativeUrl;
	}

}
