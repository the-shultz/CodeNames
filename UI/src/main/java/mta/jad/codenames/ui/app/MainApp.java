package mta.jad.codenames.ui.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mta.jad.codenames.ui.api.loader.CodeNamesUIApi;

import java.net.URL;
import java.util.Optional;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // api impl location can be supplied externally via -DapiImplFolder=... or defaults to working directory
        String implLocation = Optional
                .ofNullable(System.getProperty("apiImplFolder"))
                .orElse(System.getProperty("user.dir"));

        // initialize the API instances to be used by the UI. has to be called once upon startup.
        // in case fails to locate API, throws informative exceptions.
        CodeNamesUIApi.INSTANCE.init(implLocation);

/*
        // once initialized successfully, simple gain access to the API...
        CodeNamesUIApi.INSTANCE.getGamesDashboard().getGameDetails("gameName", fullGameDetails -> {
            System.out.println("fullGameDetails = " + fullGameDetails);
        }, fullGameDetailsFailure -> {
            System.out.println("fullGameDetailsFailure = " + fullGameDetailsFailure);
        });
*/

        URL resource = getClass().getResource("/app/HelloWorld.fxml");
        assert resource != null;

        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
