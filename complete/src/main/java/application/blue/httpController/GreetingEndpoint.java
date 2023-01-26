package application.blue.httpController;

import application.blue.talkToBlue.HttpSerializer;
import application.red.TalkToGreen;
import application.green.TalkToBlue;
import application.green.api.SayHelloController;
import application.green.api.SayHelloControllerApi;
import application.green.talkToGreen.GreetingsPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import application.red.api.UseCaseApi;
import application.red.api.UseCaseImpl;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingEndpoint {

    @Bean
    public RouterFunction<ServerResponse> route(HttpSerializer httpSerializer) {

        return RouterFunctions
            .route(
                GET("/say/hello/{n}").and(accept(MediaType.APPLICATION_JSON))
                , request -> {
                    String name = request.pathVariable("n");
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
