package bug;

import de.neuland.pug4j.Pug4J;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {

    public static void main(final String... args) throws IOException {
        final Path path = Paths.get("src/main/websrc/skeleton.pug");
        System.out.println(path.toAbsolutePath());
        final String html = Pug4J.render(path.toAbsolutePath().toString(), new HashMap<>());
        System.out.println(html);
    }

}
