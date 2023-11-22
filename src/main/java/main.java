import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegos.json"));

            JsonNode schema = JsonLoader.fromFile(new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegosschema.json"));
            JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                    .setValidationConfiguration(ValidationConfiguration.newBuilder()
                            .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

            JsonSchema schemaJson = factory.getJsonSchema(schema);

            schemaJson.validate(jsonNode);

            System.out.println("Esta Validado");

            ObjectMapper objectMapper1 = new ObjectMapper();

            File f1 = new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegos.json");
            Root root = objectMapper1.readValue(f1, Root.class);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setSuffix(".html");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            Context context = new Context();

            List<Videojuegos> videojuegoss = root.getVideojuegos();


            context.setVariable("videojuegos", videojuegoss);


            String enlace="web/Desarrollador_1.html";
            context.setVariable("enlace",enlace);



            for (Videojuegos videojuegos : root.getVideojuegos()) {
                System.out.println("**************Videojuegos*****************");
                System.out.println("ID: " + videojuegos.getId());

                System.out.println("Título: " + videojuegos.getTitulo());
                System.out.println("Género: " + videojuegos.getGenero());
                System.out.println("Precio: " + videojuegos.getPrecio());
                System.out.println("Imagen: " + videojuegos.getImagen());


                context.setVariable("desarrolladores",videojuegos.getDesarrollador());
                String contenidoDesarrollador=templateEngine.process("plantilla2",context);
                System.out.println(contenidoDesarrollador);
                escriuHTML(contenidoDesarrollador,"src/main/resources/web/Desarrollador_"+videojuegos.getId()+".html");


                for (Desarrollador desarrollador : videojuegos.getDesarrollador()) {
                    System.out.println("********Datos dessarrollador: **************");
                    System.out.println("Desarrollador ID: " + desarrollador.getId());
                    System.out.println("Nombre Dev: " + desarrollador.getNombre());
                    System.out.println("Pais Dev: " + desarrollador.getPais());


                }
                System.out.println("");
                System.out.println("****************************");
                System.out.println("");
            }


            String contenidoIndex=templateEngine.process("plantilla1",context);
            escriuHTML(contenidoIndex,"src/main/resources/web/index.html");




















        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void escriuHTML(String contingutHTML, String nomFitxer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer))) {
            writer.write(contingutHTML);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
    }
}
