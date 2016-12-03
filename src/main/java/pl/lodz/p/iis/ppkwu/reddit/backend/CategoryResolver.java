package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;

public class CategoryResolver {

	private CategoryResolver() {
		throw new UnsupportedOperationException();
	}

	public static CategoryImpl resolve(Category category) {
		Objects.requireNonNull(category);
		return (CategoryImpl) category;
	}

}
