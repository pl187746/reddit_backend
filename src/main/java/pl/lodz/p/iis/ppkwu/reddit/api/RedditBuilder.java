package pl.lodz.p.iis.ppkwu.reddit.api;

import java.util.concurrent.Executor;

/**
 * Budowniczy obiektu implementującego interfejs Reddit
 * @see Reddit
 */
public interface RedditBuilder {

	/**
	 * Konstruuje nowy obiekt implementujący Reddit.
	 * @return Nowy obiekt implementujący Reddit.
	 */
	public Reddit build();

	/**
	 * Ustawia executor, na którym mają być uruchamiane podane przez klienta callbacki.
	 * <br>Jeśli klient nie ustawi swojego executora, implementacja biblioteki zapewnia własny executor.
	 * @param callbackExecutor executor, na którym mają być uruchamiane podane przez klienta callbacki.
	 * @return this
	 */
	public RedditBuilder withCallbackExecutor(Executor callbackExecutor);

}
