import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.util.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ApiException {
        // 初始化 ApiClient
        ApiClient client = Config.defaultClient();
        // 创建 API Stub
        CoreV1Api api = new CoreV1Api(client);
        // 调用 Kubernetes API
        V1NodeList nodeList = api.listNode(null, null, null, null, null, null, null, null, 10, false);
        nodeList.getItems()
                .forEach(System.out::println);

    }
}
