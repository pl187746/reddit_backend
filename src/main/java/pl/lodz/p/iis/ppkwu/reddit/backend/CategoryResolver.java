package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.InvalidNameException;

class CategoryResolver {

	private CategoryResolver() {
		throw new UnsupportedOperationException();
	}

	static CategoryImpl resolve(Category category) throws InvalidNameException {
		Objects.requireNonNull(category);
		checkInvocation();
		if (category instanceof CategoryImpl) {
			return (CategoryImpl) category;
		} else {
			return findByName(category.name());
		}
	}

	static CategoryImpl findByName(String name) throws InvalidNameException {
		Objects.requireNonNull(name);
		checkInvocation();
		List<Category> list = getCategoriesList();
		Category category = getByName(list, name);
		return asImpl(category);
	}

	private static CategoryImpl asImpl(Category category) {
		return (CategoryImpl) category;
	}

	private static Category getByName(List<Category> list, String name) throws InvalidNameException {
		for (Category category : list) {
			if (name.equalsIgnoreCase(category.name())) {
				return category;
			}
		}
		throw new InvalidNameException("category with name '" + name + "' not found");
	}

	private static List<Category> getCategoriesList() {
		Result<List<Category>> result = loadCategoriesList();
		return result.content().orElse(Collections.emptyList());
	}

	private static Result<List<Category>> loadCategoriesList() {
		CategoryLoader categoryLoader = new CategoryLoader();
		return categoryLoader.load();
	}

}
