package pl.lodz.p.iis.ppkwu.reddit.api;

import java.util.Optional;

/**
 * Rezultat operacji pobierania dancyh z Reddita.
 * @param R Typ pobieranych danych, np. Page
 */
public interface Result<R> {

	/**
	 * @return Czy operacja zakończyła się sukcesem.
	 * @see Result#status()
	 */
	public boolean succeeded();

	/**
	 * @return Status zakończonej operacji.
	 * @see ResultStatus
	 */
	public ResultStatus status();

	/**
	 * Jeśli operacja zakończyłą się sukcesem, zwraca żądane dane opakowane w Optional.
	 * <br>Jeśli operacja zakończyła się niepowodzeniem zwraca pusty Optional.
	 * @return Opcjonalna zawartość.
	 * @see Result#succeeded()
	 */
	public Optional<R> content();

}
