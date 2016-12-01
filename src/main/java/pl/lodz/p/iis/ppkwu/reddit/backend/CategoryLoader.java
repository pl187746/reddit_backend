package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.CategoryBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.ImmutableListBuilder;

public class CategoryLoader extends AbstractLoader<List<Category>, ImmutableListBuilder<Category>> {

	private static Set<String> NON_CATEGORY_URLS = new HashSet<>();

	static {
		NON_CATEGORY_URLS.add("wiki");
		NON_CATEGORY_URLS.add("gilded");
	}

	public CategoryLoader() {
		super(UrlGenerator.baseUrl(), new ImmutableListBuilder<>());
		checkInvocation();
	}

	@Override
	protected void extract(Document document) throws StatusException {
		Elements categories = getCategoriesFromDocument(document);

		if (categories == null || categories.isEmpty()) {
			throw new StatusException(ResultStatus.DATA_ERROR, "no categories");
		}

		for (Element categoryElement : categories) {
			CategoryBuilder categoryBuilder = new CategoryBuilder();

			String name = getCategoryName(categoryElement);
			categoryBuilder.withName(name);

			String relativeUrl = getCategoryRelativeUrl(categoryElement);
			if (invalidCategoryReltiveUrl(relativeUrl)) {
				continue;
			}
			categoryBuilder.withRelativeUrl(relativeUrl);

			contentBuilder.addEntry(categoryBuilder.build());
		}
	}

	private boolean invalidCategoryReltiveUrl(String relativeUrl) {
		return relativeUrl == null || NON_CATEGORY_URLS.contains(relativeUrl);
	}

	private String getCategoryRelativeUrl(Element categoryElement) {
		String absoluteUrl = categoryElement.absUrl("href");
		int catIdx = indexOfNth(absoluteUrl, 3, '/');
		if (catIdx == -1) {
			return null;
		}
		String relativeUrl = absoluteUrl.substring(catIdx + 1);
		if (relativeUrl.endsWith("/")) {
			relativeUrl = relativeUrl.substring(0, relativeUrl.length() - 1);
		}
		return relativeUrl;
	}

	private int indexOfNth(String string, int n, char ch) {
		int idx = 0;
		for (int i = 0; i < n && idx != -1; ++i) {
			idx = string.indexOf(ch, idx + 1);
		}
		return idx;
	}

	private String getCategoryName(Element categoryElement) {
		return categoryElement.text();
	}

	private Elements getCategoriesFromDocument(Document document) {
		return document.select("ul.tabmenu a.choice");
	}

}
