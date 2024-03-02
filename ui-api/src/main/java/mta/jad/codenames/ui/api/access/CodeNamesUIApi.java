package mta.jad.codenames.ui.api.access;

import lombok.Getter;
import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.game.ActiveGame;
import mta.jad.codenames.ui.api.access.wrapper.ActiveGameExecutorWrapper;
import mta.jad.codenames.ui.api.access.wrapper.GamesDashboardExecutorWrapper;
import mta.jad.codenames.ui.api.access.wrapper.LoginExecutorWrapper;
import mta.jad.codenames.ui.api.login.Login;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.function.Consumer;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

@Getter
public enum CodeNamesUIApi {
    INSTANCE;

    private GamesDashboard gamesDashboard;
    private Login login;
    private ActiveGame activeGame;

    public void init(String workingDirectoryPath) throws IOException {
        System.out.println("Searching implementations in " + workingDirectoryPath);
        File workingDirectory = new File(workingDirectoryPath);        
        File[] files = workingDirectory.listFiles((dir, name) ->
                name.endsWith(".jar") &&
                !name.contains("CodeNamesApp")); // don't treat the UI jar as it holds the api declarations

        if (files == null || files.length == 0) {
            System.out.println("No jar files found in the working directory (" + workingDirectoryPath + ") Exiting...");
            throw new RuntimeException("No jar files found in the working directory [" + workingDirectoryPath + "] Exiting...");
        } else {
            System.out.println("Found " + files.length + " jar files in the working directory (" + workingDirectoryPath + "):");
        }

        ClassesAndJars result = processJars(files);

        URLClassLoader urlClassLoader = new URLClassLoader(result.urls.toArray(new URL[0]));

        locateLoginImpl(urlClassLoader, result);
        locateGameDashboardImpl(urlClassLoader, result);
        locateActiveGameImpl(urlClassLoader, result);

        urlClassLoader.close();
    }

    public void init(Login loginApi, GamesDashboard gamesDashboardApi, ActiveGame activeGameApi) {
        login = new LoginExecutorWrapper(loginApi);
        gamesDashboard = new GamesDashboardExecutorWrapper(gamesDashboardApi);
        activeGame = new ActiveGameExecutorWrapper(activeGameApi);
    }

    private void locateActiveGameImpl(URLClassLoader urlClassLoader, ClassesAndJars jarsData) {

        Consumer<String> GameDashboardExtractor = findApiImplementationWrapper(urlClassLoader, ActiveGame.class, apiImpl -> {
            activeGame = new ActiveGameExecutorWrapper(apiImpl);
            System.out.println("ActiveGame implementation found: " + apiImpl.getClass().getName());
        });

        jarsData.classes.forEach(GameDashboardExtractor);

        if (activeGame == null) {
            System.out.println("No ActiveGame implementation found! Exiting...");
            throw new MissingResourceException("No ActiveGame implementation found!", ActiveGame.class.getName(), "");
        }
    }

    private void locateGameDashboardImpl(URLClassLoader urlClassLoader, ClassesAndJars jarsData) {

        Consumer<String> GameDashboardExtractor = findApiImplementationWrapper(urlClassLoader, GamesDashboard.class, apiImpl -> {
            gamesDashboard = new GamesDashboardExecutorWrapper(apiImpl);
            System.out.println("GamesDashboard implementation found: " + apiImpl.getClass().getName());
        });

        jarsData.classes.forEach(GameDashboardExtractor);

        if (gamesDashboard == null) {
            System.out.println("No GamesDashboard implementation found! Exiting...");
            throw new MissingResourceException("No GamesDashboard implementation found!", GamesDashboard.class.getName(), "");
        }
    }

    private void locateLoginImpl(URLClassLoader urlClassLoader, ClassesAndJars jarsData) {

        Consumer<String> loginExtractor = findApiImplementationWrapper(urlClassLoader, Login.class, apiImpl -> {
            login = new LoginExecutorWrapper(apiImpl);
            System.out.println("Login implementation found: " + apiImpl.getClass().getName());
        });

        jarsData.classes.forEach(loginExtractor);

        if (login == null) {
            System.out.println("No Login implementation found! Exiting...");
            throw new MissingResourceException("No Login implementation found!", Login.class.getName(), "");
        }
    }

    private static ClassesAndJars processJars(File[] files) {
        ArrayList<URL> urls = new ArrayList<>(files.length);
        List<String> classes =
            Arrays
                .stream(files)
                .map(file -> {
                    System.out.println("Processing: " + file.getName());
                    try (JarFile jar = new JarFile(file)) {
                        URL url=file.toURI().toURL();
                        urls.add(url);

                        return jar
                                .stream()
                                .filter(jarEntry -> jarEntry.getName().endsWith(".class"))
                                .map(jarEntry -> jarEntry.getName().replace("/", ".").replace(".class", ""))
                                .collect(Collectors.toList());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return new ClassesAndJars(urls, classes);
    }

    private <T> Consumer<String> findApiImplementationWrapper(URLClassLoader urlClassLoader, Class<T> api, Consumer<T> onSuccess) {
        return className -> findImplementingInterface(urlClassLoader, className, api, onSuccess);
    }

    private <T> void findImplementingInterface(URLClassLoader urlClassLoader, String className, Class<T> api, Consumer<T> onSuccess) {
        try {
            Class<?> clazz = urlClassLoader.loadClass(className);
            if (api.isAssignableFrom(clazz)) {
                T apiInstance = (T)clazz.newInstance();
                onSuccess.accept(apiInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static class ClassesAndJars {

        public final ArrayList<URL> urls;
        public final List<String> classes;

        public ClassesAndJars(ArrayList<URL> urls, List<String> classes) {
            this.urls = urls;
            this.classes = classes;
        }
    }

    public static void main(String[] args) throws IOException {
        String workDir = "C:\\aviad\\courses\\Java Application Development\\exercises\\2024 - B - Code Names\\code-names\\ui-api-mock-impl\\target";
        CodeNamesUIApi.INSTANCE.init(workDir);
    }
}
