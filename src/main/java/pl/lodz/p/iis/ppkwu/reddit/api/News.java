package pl.lodz.p.iis.ppkwu.reddit.api;

import java.net.URL;
import java.util.Optional;

/**
 * Pojedyńczy wpis z Reddita.
 */
public interface News {

	/**
	 * @return Tytuł wpisu.
	 */
	public String title();

	/**
	 * @return Autor wpisu.
	 */
	public User author();

	/**
	 * Jeśli wpis posiada miniaturkę (obrazek) to zwraca URL do tej miniatury opakowany w Optional.
	 * Jeśli wpis nie posiada miniatury zwraca pusty Optional.
	 * @return Opcjonalny adres url miniaturki przy wpisie.
	 */
	public Optional<URL> thumbnailUrl();

}
