package mta.jad.codenames.ui.app.style;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StyleManager {

    private static StyleManager instance;
    private final List<Scene> scenes = new ArrayList<>();
    private final String MAIN = "main.css";
    private final String DARK = "dark.css";
    private final String COLOR = "color.css";
    private final String TEAM = "team.css";
    private final String PATH = "/style/";

    private StyleManager(){}

    public static StyleManager getInstance() {
        if(instance == null){
            instance = new StyleManager();
        }
        return instance;
    }

    public void register(Scene scene) {
        scenes.add(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(StyleManager.class.getResource(PATH + MAIN)).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(StyleManager.class.getResource(PATH + DARK)).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(StyleManager.class.getResource(PATH + COLOR)).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(StyleManager.class.getResource(PATH + TEAM)).toExternalForm());
    }

    public void unregister(Scene scene) {
        scenes.remove(scene);
    }
}
