package mta.jad.codenames.ui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mta.jad.codenames.ui.api.access.CodeNamesUIApi;
import mta.jad.codenames.ui.app.style.StyleManager;

import mta.jad.codenames.api.impl.factory.MockApiFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/*

### init from real implementation

        api impl location can be supplied externally via -DapiImplFolder=... or defaults to working directory
        String implLocation = Optional
                .ofNullable(System.getProperty("apiImplFolder"))
                .orElse(System.getProperty("user.dir"));

        initialize the API instances to be used by the UI. has to be called once upon startup.
        in case fails to locate API, throws informative exceptions.
        CodeNamesUIApi.INSTANCE.init(implLocation);

### init using mocks

        // call the init with an instance of the mock implementation:
        CodeNamesUIApi.INSTANCE.init(new LoginMock(), new GamesDashboardMock());

        for ease of use each mock implementation has a builder that allows to set the desired behavior of the mock.
        you can also use MockApiFactory to create a mock with a predefined behavior for either user or admin,
        e.g.
        Admin:
        CodeNamesUIApi.INSTANCE.init(MockApiFactory.createSuccessfulLogin(), MockApiFactory.createAdminGamesDashboard(), MockApiFactory.createAdminActiveGame());
        User:
        CodeNamesUIApi.INSTANCE.init(MockApiFactory.createSuccessfulLogin(), MockApiFactory.createUserGamesDashboard(), MockApiFactory.createPlayerActiveGame());

### using the API:

        once initialized successfully, simply gain access to the API...

        CodeNamesUIApi.INSTANCE.getGamesDashboard().getGameDetails("gameName", fullGameDetails -> {
            System.out.println("fullGameDetails = " + fullGameDetails);
        }, fullGameDetailsFailure -> {
            System.out.println("fullGameDetailsFailure = " + fullGameDetailsFailure);
        });

 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initializeFromExternalJar();

        initializeBasedOnMocksForAdmin();

        URL resource = getClass().getResource("/app/form/LoginForm.fxml");
        assert resource != null;

        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        StyleManager.getInstance().register(scene);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

    private static void initializeBasedOnMocksForAdmin() {
        CodeNamesUIApi.INSTANCE.init(MockApiFactory.createSuccessfulLogin(), MockApiFactory.createAdminGamesDashboard(), MockApiFactory.createAdminActiveGame());
    }

    private static void initializeFromExternalJar() throws IOException {
        // api impl location can be supplied externally via -DapiImplFolder=... or defaults to working directory
        String implLocation = Optional
                .ofNullable(System.getProperty("apiImplFolder"))
                .orElse(System.getProperty("user.dir"));

        // initialize the API instances to be used by the UI. has to be called once upon startup.
        // in case fails to locate API, throws informative exceptions.
        CodeNamesUIApi.INSTANCE.init(implLocation);
    }

    public static void main(String[] args) {

        launch(args);

        System.out.println("MainApp.main: Application has exited. Shutting down UI thread pools");
        CodeNamesUIApi.INSTANCE.shutdown();

        Runtime.getRuntime().exit(0);
    }

}
