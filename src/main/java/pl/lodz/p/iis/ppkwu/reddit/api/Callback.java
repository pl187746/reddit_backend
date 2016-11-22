package pl.lodz.p.iis.ppkwu.reddit.api;

/**
 * Interfejs callbacku używany przez metody pobierające dane z Reddita.
 * Implementowany przez klienta.
 * @param R Typ pobieranych danych, np. Page
 */
public interface Callback<R> {

	/**
	 * Wywoływana po zakończeniu operacji.
	 * @param result Resultat wykonanej operacji. Nigdy nie jest nullem.
	 */
	public void finished(Result<R> result);
}
