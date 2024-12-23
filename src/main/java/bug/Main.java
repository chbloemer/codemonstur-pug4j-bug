package bug;

import de.neuland.pug4j.PugConfiguration;
import de.neuland.pug4j.template.FileTemplateLoader;

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
        final PugFile template = loadTemplate(rootPath.toString(), "websrc/skeleton.pug");
        System.out.println(template.render(new HashMap<>()));

//        final var filePath = Paths.get("src/main/websrc/skeleton.pug");
//        System.out.println(filePath.toAbsolutePath());
//        final String html = Pug4J.render(filePath.toAbsolutePath().toString(), new HashMap<>());
//        System.out.println(html);
    }

    private interface PugFile {
        String render(Map<String, Object> model);
    }

    private static PugFile loadTemplate(final String rootPath, final String filePath) throws IOException {
        final var templateFile = Paths.get(rootPath).resolve(filePath);
        if (!exists(templateFile)) throw new FileNotFoundException("No template found at " + templateFile);
        if (!isRegularFile(templateFile)) throw new IOException("Path to template is not a file " + templateFile);

        final var fileLoader = new FileTemplateLoader(rootPath);
        fileLoader.setBase(toParentPath(filePath));

        final var config = new PugConfiguration();
        config.setTemplateLoader(fileLoader);
        final var template = config.getTemplate(toBaseName(templateFile));

        return model -> config.renderTemplate(template, model);
    }

    private static String toParentPath(final String filePath) {
        return filePath.substring(0, filePath.lastIndexOf('/'));
    }

    private static String toBaseName(final Path file) {
        final String filename = file.getFileName().toString();
        return filename.substring(0, filename.indexOf('.'));
    }

}
