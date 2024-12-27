package bug;

import de.neuland.pug4j.PugConfiguration;
import de.neuland.pug4j.template.ClasspathTemplateLoader;
import de.neuland.pug4j.template.PugTemplate;

import java.io.IOException;
import java.util.HashMap;

//This is my preferred way. Works also in a jar.
public class ExampleWithClasspathTemplateLoader {

    public static final String BASE_PATH = "websrc/";
    public static final String TEMPLATE = "/skeleton.pug";
    public static final String TEMPLATE_LOADER_PATH = "templates";

    public static void main(final String... args) throws IOException {
        final var fileLoader = new ClasspathTemplateLoader(TEMPLATE_LOADER_PATH); // If templates are in resources folder they belong to the classpath
        fileLoader.setBase(BASE_PATH);

        final var config = new PugConfiguration();
        config.setTemplateLoader(fileLoader);

        final PugTemplate template = config.getTemplate(TEMPLATE);
        final String html = config.renderTemplate(template, new HashMap<>());

        System.out.println(html);;
    }

}
