package pl.lodz.p.iis.ppkwu.reddit.api;

import java.util.List;

/**
 * Interfejs fasady, za którą będzie stał system pobierania i parsowania treści z Reddita.
 */
public interface Reddit {

	/**
	 * Rozpoczyna pobieranie listy kategorii.
	 * @param callback Callback uruchamiany po zakończeniu operacji pobierania listy kategorii.
	 * @throws NullPointerException Jeśli callback jest nullem. Nie rozpoczyna operacji.
	 * @see Category
	 * @see Callback
	 */
	public void loadCategoriesList(Callback<List<Category>> callback) throws NullPointerException;

	/**
	 * Rozpoczyna operację pobierania pierwszej strony wpisów z danego subreddita pod daną kategorią.
	 * @param subreddit subreddit
	 * @param category kategoria
	 * @param callback Callback uruchamiany po zakończeniu operacji pobierania pierwszej strony wpisów.
	 * @throws NullPointerException Jeśli subreddit, category lub callback jest nullem. Nie rozpoczyna operacji.
	 * @see Subreddit
	 * @see Category
	 * @see Callback
	 */
	public void loadSubredditNews(Subreddit subreddit, Category category, Callback<Page<News>> callback) throws NullPointerException;

	/**
	 * Rozpoczyna operację pobierania pierwszej strony wpisów, których autorem jest podany użytkownik.
	 * @param user użytkownik, którego wpisy chcemy pobrać
	 * @param callback Callback uruchamiany po zakończeniu operacji pobierania pierwszej strony wpisów.
	 * @throws NullPointerException Jeśli user lub callback jest nullem. Nie rozpoczyna operacji.
	 * @see User
	 * @see Callback
	 */
	public void loadUserNews(User user, Callback<Page<News>> callback) throws NullPointerException;

	/**
	 * Rozpoczyna operację pobierania pierwszej strony wpisów wyszukanych na podstawie podanych słów kluczowych.
	 * @param keywords Lista słów kluczowych.
	 * @param callback Callback uruchamiany po zakończeniu operacji pobierania pierwszej strony wpisów.
	 * @throws NullPointerException Jeśli keywords lub callback jest nullem. Nie rozpoczyna operacji.
	 * @see Callback
	 */
	public void loadNewsByKeywords(List<String> keywords, Callback<Page<News>> callback) throws NullPointerException;

	/**
	 * Tworzy obiekt implementujący User o danym loginie.
	 * <br>Nie sprawdza czy istnieje konto na Reddicie o takim loginie.
	 * @param login login
	 * @return Obiekt implementujący User o danym loginie.
	 */
	public User userWithLogin(String login);
	
	/**
	 * Tworzy obiekt reprezentujący subreddit o danej nazwie.
	 * Nie sprawdza czy istnieje subreddit na Reddicie o takim tytule
	 * @param name - nazwa subreddita
	 * @return Obiekt reprezentujący subreddit
	 */
	public Subreddit subredditWithName(String name);

}
