package pl.lodz.p.iis.ppkwu.reddit.api;

/**
 * Możliwe statusy zakończonej operacji
 */
public enum ResultStatus {

	/**
	 * Operacja zakończona sukcesem.
	 */
	SUCCEEDED,

	/**
	 * Błąd połączenia. Niepowodzenie.
	 */
	CONNECTION_ERROR,

	/**
	 * Błąd danych. Niepowodzenie.
	 * <br>Np. inna struktora dokumentu niż spodziewana.
	 */
	DATA_ERROR,

}
