package mta.jad.codenames.ui.api.loader;

import mta.jad.codenames.ui.api.login.Login;

import java.util.function.Consumer;

public class LoginExecutorWrapper extends UIExecutorWrapper implements Login {

    private final Login delegate;

    public LoginExecutorWrapper(Login delegate) {
        super(1);
        this.delegate = delegate;
    }

    @Override
    public void adminLogin(Consumer<Boolean> onLoginSuccess, Consumer<String> onLoginFail) {
        execute(() -> delegate.adminLogin(onLoginSuccess, onLoginFail));
    }

    @Override
    public void userLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail) {
        execute(() -> delegate.userLogin(name, onLoginSuccess, onLoginFail));
    }
}
