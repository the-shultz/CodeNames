package mta.jad.codenames.api.impl.login;

import lombok.Builder;
import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.login.Login;

import java.util.function.Consumer;

@Builder
public class LoginMock implements Login {

    private boolean adminLoginResult;
    private double adminLoginFailureChance;

    private double userLoginFailureChance;

    @Override
    public void adminLogin(Consumer<Boolean> onLoginSuccess, Consumer<String> onLoginFail) {
        boolean failure = MockUtils.randomBoolean(adminLoginFailureChance);
        if (failure) {
            System.out.println("Mock Login: @ adminLogin | Result: " + adminLoginResult);;
            onLoginSuccess.accept(adminLoginResult);
        } else {
            System.out.println("Mock Login: @ adminLogin | failure");;
            onLoginFail.accept("Login failed (chance to fail: " + adminLoginFailureChance + ")");
        }
    }

    @Override
    public void userLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail) {
        boolean failure = MockUtils.randomBoolean(userLoginFailureChance);
        if (failure) {
            System.out.println("Mock Login: @ userLogin | Success !");;
            onLoginSuccess.run();
        } else {
            System.out.println("Mock Login: @ userLogin | failure");;
            onLoginFail.accept("Login failed (chance to fail: " + userLoginFailureChance + ")");
        }
    }
}
