package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.URL;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pl.lodz.p.iis.ppkwu.reddit.api.Category;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.ImmutableListBuilder;

public class CategoryLoader extends AbstractLoader<List<Category>, ImmutableListBuilder<Category>> {

	protected CategoryLoader(URL url) {
		super(url, new ImmutableListBuilder<>());
	}

	@Override
	protected void extract(Document document) throws StatusException {
		Elements categories = getCategoriesFromDocument(document);
		
	}

	private Elements getCategoriesFromDocument(Document document) {
		return document.select("ul.tabmenu, a.choice");
	}

}
