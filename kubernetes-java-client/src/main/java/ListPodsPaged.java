import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ListPodsPaged {

    private static final Logger log = LoggerFactory.getLogger(ListPodsPaged.class);

    public static void main(String[] args) throws Exception {
        ApiClient client = Config.defaultClient();
        CoreV1Api api = new CoreV1Api(client);
        String continuationToken = null;
        int limit = 2;
        Long remaining = null;
        do {
            log.info("=======================================================");
            log.info("Retrieving data: continuationToken={}, remaining={}", continuationToken, remaining);
            V1PodList items = api.listPodForAllNamespaces(null, continuationToken, null, null, limit, null, null, null, 10, false);
            continuationToken = items.getMetadata().getContinue();
            remaining = items.getMetadata().getRemainingItemCount();
            items.getItems()
                    .forEach(node -> System.out.println(node.getMetadata()));
        } while (continuationToken != null);
    }
}
