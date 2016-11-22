package pl.lodz.p.iis.ppkwu.reddit.api;

import java.util.List;

/**
 * Strona danych z Reddita.
 * @param C Typ elementów na stronie, np. News
 */
public interface Page<C> {

	/**
	 * Niemutowalana lista elementów na stronie.
	 * @return Listę elementów na danej stronie.
	 */
	public List<C> content();


}
