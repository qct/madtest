package madtest.common.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by alex on 2016/10/19.
 */
public class OkHttpMain {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(30, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://www.baidu.com/").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
