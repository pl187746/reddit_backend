package pl.lodz.p.iis.ppkwu.reddit.backend;

import pl.lodz.p.iis.ppkwu.reddit.api.User;

public class UserImpl implements User {
	
	private final String login;

	@Override
	public String login() {
		return login;
	}
	
	public UserImpl(String login) {
		this.login = login;
	}

}
