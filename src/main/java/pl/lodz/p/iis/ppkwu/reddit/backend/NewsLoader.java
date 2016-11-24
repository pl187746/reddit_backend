package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.io.IOException;
import java.net.URL;

import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Downloader;

public class NewsLoader {

	private URL url;

	private PageBuilder<News> pageBuilder = new PageBuilder<>();

	private ResultStatus resultStatus = ResultStatus.SUCCEEDED;

	public NewsLoader(URL url) {
		this.url = url;
	}

	public Result<Page<News>> loadNews() {
		try {
			perform();
		} catch (StatusException se) {
			resultStatus = se.getStatus();
		}
		return getResult();
	}

	public Result<Page<News>> getResult() {
		return new ResultBuilder<Page<News>>().withContent(pageBuilder.build()).withStatus(resultStatus).build();
	}

	private void perform() throws StatusException {
		byte[] data = download();
	}

	private byte[] download() throws StatusException {
		try {
			return Downloader.download(url);
		} catch (IOException ex) {
			throw new StatusException(ResultStatus.CONNECTION_ERROR, "downloading", ex);
		}
	}
}
