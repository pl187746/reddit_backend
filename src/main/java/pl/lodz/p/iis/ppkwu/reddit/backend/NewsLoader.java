package pl.lodz.p.iis.ppkwu.reddit.backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.lodz.p.iis.ppkwu.reddit.api.News;
import pl.lodz.p.iis.ppkwu.reddit.api.Page;
import pl.lodz.p.iis.ppkwu.reddit.api.Result;
import pl.lodz.p.iis.ppkwu.reddit.api.ResultStatus;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.NewsBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.PageBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.ResultBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.data.builders.UserBuilder;
import pl.lodz.p.iis.ppkwu.reddit.backend.exceptions.StatusException;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Downloader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class NewsLoader {

    private URL url;

    private PageBuilder<News> pageBuilder = new PageBuilder<>();

    private ResultStatus resultStatus = ResultStatus.SUCCEEDED;

    public NewsLoader(URL url) {
        this.url = url;
    }

    public Result<Page<News>> load() {
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

    private void extract(Document document) throws StatusException {
        Elements news = getNewsFromDocument(document);
        if (null != news)
            for (Element newsElement : news) {

                NewsBuilder newsBuilder = new NewsBuilder();

                String title = getTitle(newsElement);
                newsBuilder.withTitle(title);

                UserImpl author = getAuthor(newsElement);
                newsBuilder.withAuthor(author);

                Optional<URL> url = getThumbnailUrl(newsElement);
                newsBuilder.withThumbnailUrl(url);

                pageBuilder.addEntry(newsBuilder.build());
            }

    }

    private Elements getNewsFromDocument(Document document) {
        return document.select("div.thing, div.search-result-link");
    }

    private Optional<URL> getThumbnailUrl(Element newsElement) {
        Optional<URL> thumbnailUrl = Optional.empty();
        Elements thumbnailElements = newsElement.select("a.thumbnail img");
        if(!thumbnailElements.isEmpty()) {
            try {
                URL url = new URL(thumbnailElements.first().absUrl("src"));
                thumbnailUrl = Optional.of(url);
            } catch (MalformedURLException e) {
            }
        }
        return thumbnailUrl;
    }

    private String getTitle(Element newsElement) throws StatusException {
        Elements titleElements = newsElement.select("a.title, a.search-title");
        if(titleElements.isEmpty()) {
        	throw new StatusException(ResultStatus.DATA_ERROR, "no title");
        }
        return titleElements.first().text();
    }

    private UserImpl getAuthor(Element newsElement) throws StatusException {
        Elements authorElements = newsElement.select("a.author");
        if(authorElements.isEmpty()) {
        	throw new StatusException(ResultStatus.DATA_ERROR, "no author");
        }
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.withLogin(authorElements.first().text());
        return userBuilder.build();
    }

}
