import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * Created by Tian on 2017/2/20.
 */
public class JSONObjectTest {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("newFileStorage", "{}");
        System.out.println(json.has("newFileStorage"));
        System.out.println(JSONUtils.mayBeJSON(json.getString("newFileStorage")));
//        final Object o = JSONObject.toBean(JSONObject.fromObject(json.get("newFileStorage")), Person.class);
    }
}