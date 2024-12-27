package bug;

import de.neuland.pug4j.Pug4J;
import de.neuland.pug4j.PugConfiguration;
import de.neuland.pug4j.template.FileTemplateLoader;
import de.neuland.pug4j.template.PugTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.isRegularFile;

public class Main {

    public static void main(final String... args) throws IOException {
        final var rootPath = Paths.get("src/main").toAbsolutePath();
        final var template = loadTemplate(rootPath.toString(), "websrc/skeleton.pug");
        System.out.println(Pug4J.render(template, new HashMap<>()));

//        final var filePath = Paths.get("src/main/websrc/index.pug");
//        System.out.println(filePath.toAbsolutePath());
//        final String html = Pug4J.render(filePath.toAbsolutePath().toString(), new HashMap<>());
//        System.out.println(html);
    }

    private static PugTemplate loadTemplate(final String rootPath, final String filePath) throws IOException {
        final var templateFile = Paths.get(rootPath).resolve(filePath);
        if (!exists(templateFile)) throw new FileNotFoundException("No template found at " + templateFile);
        if (!isRegularFile(templateFile)) throw new IOException("Path to template is not a file " + templateFile);

        final var fileLoader = new FileTemplateLoader(rootPath);
        fileLoader.setBase(toParentPath(filePath));

        final var config = new PugConfiguration();
        config.setTemplateLoader(fileLoader);

        return config.getTemplate(toBaseName(templateFile));
    }

    private static String toParentPath(final String filePath) {
        return filePath.substring(0, filePath.lastIndexOf('/'));
    }

    private static String toBaseName(final Path file) {
        final String filename = file.getFileName().toString();
        return "/"+filename;
    }

}
