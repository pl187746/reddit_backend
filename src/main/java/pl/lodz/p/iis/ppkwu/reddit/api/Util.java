package pl.lodz.p.iis.ppkwu.reddit.api;

/**
 * Zapewnia klientowi punkt wejścia do implementacji oferowanej przez bibliotekę.
 */
public class Util {

	/**
	 * @return Nową instancję obiektu implementującego RedditBuilder
	 */
	public static RedditBuilder redditBuilder() {
		//return new pl.lodz.p.iis.ppkwu.reddit.team42.backend.RedditBuilderImpl();
		throw new RuntimeException("Brak implementacji Util.redditBuilder()");
	}

	/**
	 * Prywatny konstruktor, dla zapewnienia, że obiekty tej klasy nie będą tworzone.
	 */
	private Util() {
		throw new UnsupportedOperationException("Nie mona tworzyć obiektów klasy Util.");
	}

}
