package pl.lodz.p.iis.ppkwu.reddit.backend;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Downloader;

public abstract class AbstractLoader<R, B extends Builder<? extends R>> {

	protected URL url;
	protected B contentBuilder;
	private ResultStatus resultStatus = ResultStatus.SUCCEEDED;

	protected AbstractLoader(URL url, B contentBuilder) {
		super();
		this.url = url;
		this.contentBuilder = contentBuilder;
		checkInvocation();
	}

	public Result<R> load() {
		try {
			perform();
		} catch (StatusException se) {
			resultStatus = se.getStatus();
		}
		return getResult();
	}

	public Result<R> getResult() {
		return new ResultBuilder<R>().withContent(contentBuilder.build()).withStatus(resultStatus).build();
	}

	private void perform() throws StatusException {
		byte[] data = download();
		Document document = parse(data);
		extract(document);
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

	protected abstract void extract(Document document) throws StatusException;

}