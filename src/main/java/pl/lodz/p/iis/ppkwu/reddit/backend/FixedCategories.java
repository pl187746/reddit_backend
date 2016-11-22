package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.CategoryImpl;

public class FixedCategories {

	private static final CategoryImpl[] categories = {
		new CategoryImpl("gorące", ""),
		new CategoryImpl("najnowsze", "new"),
		new CategoryImpl("wschodzące", "rising"),
		new CategoryImpl("kontrowersyjne", "controversial"),
		new CategoryImpl("najwięcej punktów", "top")
	};

	private static final List<CategoryImpl> implList = Collections.unmodifiableList(Arrays.asList((CategoryImpl[])categories));

	private static final List<Category> list = Collections.unmodifiableList(Arrays.asList((CategoryImpl[])categories));

	public static List<CategoryImpl> getImplList() {
		return implList;
	}

	public static List<Category> getList() {
		return list;
	}

}
