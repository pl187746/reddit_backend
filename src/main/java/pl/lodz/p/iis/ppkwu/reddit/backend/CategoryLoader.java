package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.URL;
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

	protected CategoryLoader(URL url) {
		super(url, new ImmutableListBuilder<>());
	}

	@Override
	protected void extract(Document document) throws StatusException {
		Elements categories = getCategoriesFromDocument(document);
		
		if(null != categories) {
			for(Element categoryElement : categories) {
				
				CategoryBuilder categoryBuilder = new CategoryBuilder();
				String url = getURL(categoryElement);
				
				String categoryName = "";
				
				String[] parts = url.split("//");
				String[] subparts = parts[1].split("/");
				
				switch(subparts.length){
				case 1:
					categoryName = "hot";
					categoryBuilder.withName(categoryName);
					break;
				case 2:
					categoryName = subparts[(subparts.length)-1];
					if(!categoryName.equals("gilded") && !categoryName.equals("wiki") && !categoryName.equals("promoted")){
						categoryBuilder.withName(categoryName);
					}
					break;
				}
				
			}
		}
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
