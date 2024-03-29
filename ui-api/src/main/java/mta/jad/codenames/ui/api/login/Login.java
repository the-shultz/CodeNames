package mta.jad.codenames.ui.api.login;

import java.util.function.Consumer;

public interface Login {

    /**
     * Login as admin
     * @param onLoginSuccess consumer of Boolean value, to be called when login operation went successfully.
     *                       The value will be true if the login was successful, false in case an admin is already connected.
     * @param onLoginFail consumer of String value, to be called when login operation failed due to logical exception (like admin already connected, or user-name already exist)
     *                    or an unplanned error. The value will be an informative error message that will be displayed to the user.
     */
    void adminLogin(Runnable onLoginSuccess, Consumer<String> onLoginFail);

    /**
     * Login as player
     * @param name the player name. each user has a unique name.
     * @param onLoginSuccess runnable to be called when user login operation went successfully.
     * @param onLoginFail consumer of String value, to be called when login operation failed due to exception or an unplanned error
     *                    The value will be an informative error message that will be displayed to the user.
     */
    void playerLogin(String name, Runnable onLoginSuccess, Consumer<String> onLoginFail);
}
