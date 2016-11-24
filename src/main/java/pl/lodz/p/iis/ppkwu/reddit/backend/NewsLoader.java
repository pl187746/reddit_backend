package pl.lodz.p.iis.ppkwu.reddit.backend;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
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
		Document document = parse(data);
		extractNews(document);
	}

	private byte[] download() throws StatusException {
		try {
			return Downloader.download(url);
		} catch (IOException ex) {
			throw new StatusException(ResultStatus.CONNECTION_ERROR, "downloading", ex);
		}
	}

	private Document parse(byte[] data) throws StatusException {
		try (ByteArrayInputStream input = new ByteArrayInputStream(data)) {
			return Jsoup.parse(input, "UTF-8", url.toString());
		} catch (Exception ex) {
			throw new StatusException(ResultStatus.DATA_ERROR, "parsing", ex);
		}
	}

	private void extractNews(Document document) {
	}

}
