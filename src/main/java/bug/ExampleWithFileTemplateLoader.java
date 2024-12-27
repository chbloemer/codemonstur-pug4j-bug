package bug;

import de.neuland.pug4j.PugConfiguration;
import de.neuland.pug4j.template.ClasspathTemplateLoader;
import de.neuland.pug4j.template.FileTemplateLoader;
import de.neuland.pug4j.template.PugTemplate;

import java.io.IOException;
import java.util.HashMap;


public class ExampleWithFileTemplateLoader {

    public static final String BASE_PATH = "websrc/";
    public static final String TEMPLATE = "/skeleton.pug";
    public static final String TEMPLATE_LOADER_PATH = "src/main";

    public static void main(final String... args) throws IOException {
        final var fileLoader = new FileTemplateLoader(TEMPLATE_LOADER_PATH);
        fileLoader.setBase(BASE_PATH);

        final var config = new PugConfiguration();
        config.setTemplateLoader(fileLoader);

        final PugTemplate template = config.getTemplate(TEMPLATE);
        final String html = config.renderTemplate(template, new HashMap<>());

        System.out.println(html);;
    }

}
