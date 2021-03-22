import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.util.Config;

import java.util.concurrent.CompletableFuture;

public class ListNodesAsync {

    public static void main(String[] args) throws Exception {
        ApiClient client = Config.defaultClient();
        CoreV1Api api = new CoreV1Api(client);

        // Start async call
        CompletableFuture<V1NodeList> p = AsyncHelper.doAsync(api, (capi, cb) ->
                capi.listNodeAsync(null, null, null, null, null, null, null, null, 10, false, cb)
        );
        p.thenAcceptAsync((nodeList) -> {
            nodeList.getItems()
                    .forEach((node) -> System.out.println(node.getMetadata()));
        });
    }
}
