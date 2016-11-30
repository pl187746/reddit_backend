package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;

public class CategoryBuilder implements Builder<CategoryImpl> {

	private String name = "";
	private String relativeUrl = "";

	public CategoryBuilder() {
		checkInvocation();
	}

	public CategoryBuilder withName(String name) {
		this.name = Objects.requireNonNull(name);
		return this;
	}

	public CategoryBuilder withRelativeUrl(String relativeUrl) {
		this.relativeUrl = Objects.requireNonNull(relativeUrl);
		return this;
	}

	@Override
	public CategoryImpl build() {
		return new CategoryImpl(name, relativeUrl);
	}

}
