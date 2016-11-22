/**
 * <p>Przykładowe API.</p>
 * 
 * <p>Klient używa <code>Reddit reddit = Util.redditBuilder().build();</code>
 * do uzyskania obiektu implemetującego interfejs Reddit.</p>
 * 
 * <p>Klient może podać własny executor dla callbacków, jeśli np. chce by callbacki wykonywały się na wątku GUI.</p>
 * <br>Np. dla Swinga:
 * <pre>Reddit reddit = Util.redditBuilder()
 * 	.withCallbackExecutor(new Executor() {
 * 		&#64;Override
 * 		public void execute(Runnable runnable) {
 * 			SwingUtilities.invokeLater(runnable);
 * 		})
 * 	.build();</pre>
 * <br>Np. dla JavaFX:
 * <pre>Reddit reddit = Util.redditBuilder()
 * 	.withCallbackExecutor(new Executor() {
 * 		&#64;Override
 * 		public void execute(Runnable runnable) {
 * 			Platform.runLater(runnable);
 * 		})
 * 	.build();</pre>
 * 
 * <p>Operacje pobrania danych z Reddita są asynchroniczne,
 * tzn. po wywołaniu metody od pobierania danych, wraca ona od razu,
 * operacje wykonywane są asynchronicznie (np. na innym wątku),
 * po zakończeniu operacji wywoływany jest callback podany przez klienta przy rozpoczynaniu operacji.</p>
 * 
 * <p>Obiekty reprezentujące dane pobrane z Reddita są niemutowalne.
 * <br>Kolejne operacje pobrania danych tworzą nowe obiekty, nie zmieniają stanu istniejących.</p>
 * 
 * <p>Interfejsy, klasy, enumy, itd., itp. opisane w tej dokumentacji muszą być w implemenatcji backendu umieszczone w pakiecie pl.lodz.p.iis.ppkwu.reddit.api (inne rzeczy z implementacji backendu mogą być w innych pakietach).</p>
 * 
 * @see Util
 * @see Reddit
 */
package pl.lodz.p.iis.ppkwu.reddit.api;
