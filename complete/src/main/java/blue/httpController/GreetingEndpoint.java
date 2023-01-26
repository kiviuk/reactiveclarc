package blue.httpController;

import blue.talkToBlue.HttpSerializer;
import green.TalkToBlue;
import green.api.SayHelloController;
import green.api.SayHelloControllerApi;
import green.talkToGreen.GreetingsPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import red.TalkToGreen;
import red.api.UseCaseApi;
import red.api.UseCaseImpl;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingEndpoint {

    @Bean
    public RouterFunction<ServerResponse> route(HttpSerializer httpSerializer) {

        return RouterFunctions
            .route(
                GET("/say/hello/{name}").and(accept(MediaType.TEXT_PLAIN))
                , request -> {
                    String name = request.pathVariable("name");
                    getSayHelloController(httpSerializer).sayHelloTo(name);
                    return httpSerializer.getHelloPayload();
                }
            );
    }

    private static SayHelloControllerApi getSayHelloController(TalkToBlue talkToBlue) {
        TalkToGreen talkToGreen = new GreetingsPresenter(talkToBlue);
        UseCaseApi useCaseApi = new UseCaseImpl(talkToGreen);
        return new SayHelloController(useCaseApi);
    }
}
