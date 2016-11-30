package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.CategoryBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.ImmutableListBuilder;

public class CategoryLoader extends AbstractLoader<List<Category>, ImmutableListBuilder<Category>> {

	private static final String PROMOTED_CATEGORY = "promoted";
	private static final String WIKI_CATEGORY = "wiki";
	private static final String GILDED_CATEGORY = "gilded";
	private static final String HOT_CATEGORY = "hot";

	protected CategoryLoader() {
		super(UrlGenerator.baseUrl(), new ImmutableListBuilder<>());
		checkInvocation();
	}

	@Override
	protected void extract(Document document) throws StatusException {
		Elements categories = getCategoriesFromDocument(document);

		if (null != categories) {
			for (Element categoryElement : categories) {

				CategoryBuilder categoryBuilder = new CategoryBuilder();
				String url = getURL(categoryElement);

				String categoryName = "";
				boolean validCategory = true;

				String[] parts = url.split("//");
				String[] subparts = parts[1].split("/");

				int subpartsLength = subparts.length;

				switch (subpartsLength) {
				case 1:
					categoryName = HOT_CATEGORY;
					break;
				case 2:
					categoryName = subparts[subpartsLength - 1];
					validCategory = isValidCategory(categoryName);
					break;
				}

				if (subpartsLength > 2) {
					if (subparts[3].isEmpty()) {
						categoryName = HOT_CATEGORY;
					} else {
						categoryName = subparts[3];
						validCategory = isValidCategory(categoryName);
					}
				}

				if (validCategory) {
					String relativeUrl = getRelativeUrl(subparts, subpartsLength);
					categoryBuilder.withName(categoryName).withRelativeUrl(relativeUrl);
					contentBuilder.addEntry(categoryBuilder.build());
				}
			}
		}
	}

	private String getRelativeUrl(String[] subparts, int subpartsLength) {
		String relativeUrl = "/";
		for (int i = 1; i < subpartsLength; i++) {
			relativeUrl = relativeUrl + subparts[i] + "/";
		}
		return relativeUrl;
	}

	private boolean isValidCategory(String categoryName) {
		return !GILDED_CATEGORY.equals(categoryName) && !WIKI_CATEGORY.equals(categoryName)
				&& !PROMOTED_CATEGORY.equals(categoryName);
	}

	private String getURL(Element categoryElement) throws StatusException {
		Elements categoryElements = categoryElement.select("a.[href]");
		if (categoryElements.isEmpty()) {
			throw new StatusException(ResultStatus.DATA_ERROR, "no category");
		}
		return categoryElements.first().text();
	}

	private Elements getCategoriesFromDocument(Document document) {
		return document.select("ul.tabmenu, a.choice");
	}

}
