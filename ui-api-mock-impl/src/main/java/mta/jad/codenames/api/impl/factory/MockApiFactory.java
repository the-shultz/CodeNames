package mta.jad.codenames.api.impl.factory;

import mta.jad.codenames.api.impl.login.LoginMock;
import mta.jad.codenames.ui.api.login.Login;

public class MockApiFactory {

    public static Login CreateSuccessfulLogin() {
        return
            LoginMock
                .builder()
                .adminLoginFailureChance(0.0)
                .userLoginFailureChance(0.0)
                .adminLoginResult(true)
                .build();

    }

    public static Login CreateFailedLogin() {
        return
            LoginMock
                .builder()
                .adminLoginFailureChance(1.0)
                .userLoginFailureChance(1.0)
                .adminLoginResult(false)
                .build();
    }
}
