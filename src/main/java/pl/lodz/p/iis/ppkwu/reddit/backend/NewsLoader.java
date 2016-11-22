package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.net.URL;

import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;

public class NewsLoader {

	private URL url;

	private PageBuilder<News> pageBuilder = new PageBuilder<>();

	private ResultStatus resultStatus = ResultStatus.SUCCEEDED;

	public NewsLoader(URL url) {
		this.url = url;
	}

	public Result<Page<News>> loadNews() {
		return getResult();
	}

	public Result<Page<News>> getResult() {
		return new ResultBuilder<Page<News>>().withContent(pageBuilder.build()).withStatus(resultStatus).build();
	}
}
