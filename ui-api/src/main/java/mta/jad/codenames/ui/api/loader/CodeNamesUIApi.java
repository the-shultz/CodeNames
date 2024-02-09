package mta.jad.codenames.ui.api.loader;

import lombok.Getter;
import mta.jad.codenames.ui.api.dashboard.GamesDashboard;

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

    public void init() throws IOException {
        String userDir = System.getProperty("user.dir");
        System.out.println("current working directory : " + userDir);
        init(userDir);
    }

    public void init(String workingDirectoryPath) throws IOException {
        File workingDirectory = new File(workingDirectoryPath);        
        File[] files = workingDirectory.listFiles((dir, name) -> name.endsWith(".jar"));

        if (files == null || files.length == 0) {
            System.out.println("No jar files found in the working directory (" + workingDirectoryPath + ")! Exiting...");
            throw new RuntimeException("No jar files found in the working directory [" + workingDirectoryPath + "]! Exiting...");
        } else {
            System.out.println("Found " + files.length + " jar files in the working directory (" + workingDirectoryPath + "):");
        }

        ClassesAndJars result = processJars(files);

        URLClassLoader urlClassLoader = new URLClassLoader(result.urls.toArray(new URL[0]));

        locateGameDashboardImpl(urlClassLoader, result);

        urlClassLoader.close();

    }

    private void locateGameDashboardImpl(URLClassLoader urlClassLoader, ClassesAndJars result) {

        Consumer<String> GameDashboardExtractor = findApiImplementationWrapper(urlClassLoader, GamesDashboard.class, apiImpl -> {
            gamesDashboard = apiImpl;
            System.out.println("GamesDashboard implementation found: " + apiImpl.getClass().getName());
        });

        result.classes.forEach(GameDashboardExtractor);

        if (gamesDashboard == null) {
            System.out.println("No GamesDashboard implementation found! Exiting...");
            throw new MissingResourceException("No GamesDashboard implementation found!", GamesDashboard.class.getName(), "");
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

        //classes.forEach(System.out::println);

        return new ClassesAndJars(urls, classes);
    }

    private static class ClassesAndJars {

        public final ArrayList<URL> urls;
        public final List<String> classes;

        public ClassesAndJars(ArrayList<URL> urls, List<String> classes) {
            this.urls = urls;
            this.classes = classes;
        }
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

    public static void main(String[] args) throws IOException {
        String workDir = "C:\\aviad\\courses\\Java Application Development\\exercises\\2024 - B - Code Names\\code-names\\ui-api-mock-impl\\target";
        CodeNamesUIApi.INSTANCE.init(workDir);
    }
}
