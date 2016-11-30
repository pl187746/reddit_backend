package pl.lodz.p.iis.ppkwu.reddit.backend.data;

import static pl.lodz.p.iis.ppkwu.reddit.backend.utils.InvocationChecker.checkInvocation;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.api.User;

public class UserImpl implements User {

	private final String login;

	public UserImpl(String login) {
		this.login = Objects.requireNonNull(login, "login");
		checkInvocation();
	}

	@Override
	public String login() {
		return login;
	}

}
