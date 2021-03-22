import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.openapi.models.V1Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class K8sTest {
    private ObjectMapper mapper;
    private Path deploymentOutput;

    @Before
    public void setup() {
        mapper = new ObjectMapper(
                new YAMLFactory()
                        .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                        .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        deploymentOutput = Paths.get("src/test/resources/yaml/deployment.yml");
    }

    @After
    public void cleanup() {

    }

    @Test
    public void test() throws IOException {
        try (Stream<String> lines = Files.lines(deploymentOutput, StandardCharsets.UTF_8)) {
            String data = lines.collect(Collectors.joining("\n"));
            String[] split = data.split("---");
            for (String s : split) {
                if (s.contains("kind: Deployment")) {
                    V1Deployment v1Deployment = mapper.readValue(s, V1Deployment.class);
                    String deploymentYaml = mapper.writeValueAsString(v1Deployment);
                    System.out.println(deploymentYaml);
                } else if (s.contains("kind: ConfigMap")) {
                    V1ConfigMap v1ConfigMap = mapper.readValue(s, V1ConfigMap.class);
                    String configMapYaml = mapper.writeValueAsString(v1ConfigMap);
                    System.out.println(configMapYaml);
                }
            }
        }
    }
}
