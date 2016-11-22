package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.User;

public class UserImpl implements User {

	private final String login;

	public UserImpl(String login) {
		this.login = Objects.requireNonNull(login, "login");
	}

	@Override
	public String login() {
		return login;
	}

}
