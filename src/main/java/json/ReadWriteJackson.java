package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Tian on 2017/2/9.
 */
public class ReadWriteJackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Person father = new Person("Tian", "Xie");
        Person son = new Person("Tianqing", "Xie");

        mapper.writeValue(System.out, father);


    }
}

class Request {
    private String requestId;
    private String bucketName;
//    private FileStorage fileStorage;
}

class PdfRequest extends Request {
    private String callbackUrl;
}

class ImageRequest extends Request {

}
