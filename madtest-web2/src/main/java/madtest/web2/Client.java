package madtest.web2;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * <p>Created by Damon.Q on 2017/2/10.
 */
public class Client {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        ListenableFuture<ResponseEntity<User>> future = template
            .getForEntity("http://127.0.0.1:8080/madtest-web2/api", User.class);

        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("===========client failure: " + ex);
            }

            @Override
            public void onSuccess(ResponseEntity<User> result) {
                System.out.println("===========client get result: " + result.getBody());
            }
        });
        System.out.println("==no wait");

        WebClient webClient = WebClient.create("http://127.0.0.1:8080/madtest-web2/api");
        Mono<User> userMono = webClient.get().exchange().flatMap(response -> response.bodyToMono(User.class));
        System.out.println(userMono.block());
    }
}
