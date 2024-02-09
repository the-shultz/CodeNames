package mta.jad.codenames.ui.api.loader;

import mta.jad.codenames.ui.api.dashboard.GamesDashboard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CodeNamesUIApi {
    INSTANCE;

    private GamesDashboard gamesDashboard;

    public void init() throws IOException {
        String userDir = System.getProperty("user.dir");
        System.out.println("current working directory : " + userDir);
        File workingDirectory=new File("C:\\aviad\\courses\\Java Application Development\\exercises\\2024 - B - Code Names\\code-names\\ui-api-mock-impl\\target");
        File[] files = workingDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        ArrayList<URL> urls = new ArrayList<>(files.length);
        List<String> classes = Arrays
                .stream(files)
                .map(file -> {
                    System.out.println("Processing: " + file.getName());
                    try (JarFile jar = new JarFile(file)) {
                        URL url=file.toURI().toURL();
                        urls.add(url);

                        return
                                jar
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

        classes.forEach(System.out::println);

        URLClassLoader urlClassLoader = new URLClassLoader(urls.toArray(new URL[0]));

        Consumer<String> GameDashboardExtractor = findApiImplementationWrapper(urlClassLoader, GamesDashboard.class, apiImpl -> {
            gamesDashboard = apiImpl;
            System.out.println("GamesDashboard implementation found: " + apiImpl.getClass().getName());
        });

        classes.forEach(GameDashboardExtractor);
        if (gamesDashboard == null) {
            System.out.println("No GamesDashboard implementation found! Exiting...");
        }

        urlClassLoader.close();

    }

    private <T> Consumer<String> findApiImplementationWrapper(URLClassLoader urlClassLoader, Class<T> api, Consumer<T> onSuccess) {
        return className -> findImplementingInterface(urlClassLoader, className, api, onSuccess);
    }

    private <T> void findImplementingInterface(URLClassLoader urlClassLoader, String className, Class<T> api, Consumer<T> onSuccess) {
        try {
            Class<?> clazz = urlClassLoader.loadClass(className);
            //Class[] interfaces=clazz.getInterfaces();
            if (api.isAssignableFrom(clazz)) {
                T apiInstance = (T)clazz.newInstance();
                onSuccess.accept(apiInstance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public GamesDashboard getGamesDashboard() {
        return gamesDashboard;
    }

    public static void main(String[] args) throws IOException {
        CodeNamesUIApi.INSTANCE.init();
    }
}
