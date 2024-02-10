package mta.jad.codenames.api.impl.login;

import mta.jad.codenames.ui.api.login.Login;

import java.util.function.Consumer;

public class LoginMock implements Login {
    @Override
    public void adminLogin(Consumer<Boolean> onLoginSuccess, Consumer<String> onLoginFail) {
        System.out.println("adminLogin");
    }

    @Override
    public void userLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail) {
        System.out.println("userLogin");
    }
}
