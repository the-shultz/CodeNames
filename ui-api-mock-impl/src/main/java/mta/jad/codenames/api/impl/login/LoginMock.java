package mta.jad.codenames.api.impl.login;

import lombok.Builder;
import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.login.Login;

import java.util.function.Consumer;

@Builder
public class LoginMock implements Login {

    private double adminLoginFailureChance;

    private double userLoginFailureChance;

    @Override
    public void adminLogin(Runnable onLoginSuccess, Consumer<String> onLoginFail) {
        boolean isFailure = MockUtils.randomBoolean(adminLoginFailureChance);
        if (!isFailure) {
            System.out.println("Mock Login: @ adminLogin | Success !");
            onLoginSuccess.run();
        } else {
            System.out.println("Mock Login: @ adminLogin | failure");;
            onLoginFail.accept("Login failed (chance to fail: " + adminLoginFailureChance + ")");
        }
    }

    @Override
    public void playerLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail) {
        boolean isFailure = MockUtils.randomBoolean(userLoginFailureChance);
        if (!isFailure) {
            System.out.println("Mock Login: @ userLogin | Success !");;
            onLoginSuccess.run();
        } else {
            System.out.println("Mock Login: @ userLogin | failure");;
            onLoginFail.accept("Login failed (chance to fail: " + userLoginFailureChance + ")");
        }
    }
}
