package mta.jad.codenames.ui.api.login;

import java.util.function.Consumer;

public interface Login {

    void adminLogin(Consumer<Boolean> onLoginSuccess, Consumer<String> onLoginFail);
    void userLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail);
}
