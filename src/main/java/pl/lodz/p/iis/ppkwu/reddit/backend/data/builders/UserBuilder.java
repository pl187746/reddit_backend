package pl.lodz.p.iis.ppkwu.reddit.backend.data.builders;

import java.util.Objects;

import pl.lodz.p.iis.ppkwu.reddit.backend.data.UserImpl;
import pl.lodz.p.iis.ppkwu.reddit.backend.utils.Builder;

public class UserBuilder implements Builder<UserImpl> {

	private String login;

	public UserBuilder withLogin(String login) {
		this.login = Objects.requireNonNull(login);
		return this;
	}

	@Override
	public UserImpl build() {
		return new UserImpl(login);
	}

}
