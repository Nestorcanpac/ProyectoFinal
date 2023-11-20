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

import java.io.File;

public class main {
    public static void main(String[] args) {
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode jsonNode= objectMapper.readTree(new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegos.json"));

            JsonNode schema= JsonLoader.fromFile(new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegosschema.json"));
            JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                    .setValidationConfiguration(ValidationConfiguration.newBuilder()
                            .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

            JsonSchema schemaJson = factory.getJsonSchema(schema);

            schemaJson.validate(jsonNode);

            System.out.println("Esta Validado");

            ObjectMapper objectMapper1=new ObjectMapper();

            File f1=new File("/home/nescanpac/IdeaProjects/ProyectoFinal/src/main/resources/videojuegos.json");
            Root root=objectMapper1.readValue(f1,Root.class);

            for (Videojuegos videojuegos: root.getVideojuegos()){
                System.out.println("**************Videojuegos*****************");
                System.out.println("ID: "+ videojuegos.getId());
                System.out.println("Título: "+videojuegos.getTitulo());
                System.out.println("Género: "+videojuegos.getGenero());
                System.out.println("Precio: "+videojuegos.getPrecio());
                System.out.println("Imagen: "+videojuegos.getImagen());

                for (Desarrollador desarrollador: videojuegos.getDesarrollador()){
                    System.out.println("********Datos dessarrollador: **************");
                    System.out.println("Desarrollador ID: "+desarrollador.getId());
                    System.out.println("Nombre Dev: "+desarrollador.getNombre());
                    System.out.println("Pais Dev: "+desarrollador.getPais());


                }
                System.out.println("");
                System.out.println("****************************");
                System.out.println("");
            }

            ClassLoaderTemplateResolver templateResolver=new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("carpetaHtml/");
            templateResolver.setSuffix(".html");

            TemplateEngine templateEngine=new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context=new Context();

            context.setVariable("Videojuegos",root);

            String contingutHTML= templateEngine.process("index",context);

            System.out.println(contingutHTML);





        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
