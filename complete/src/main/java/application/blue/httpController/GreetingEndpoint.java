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

/**
 * The technical ENDPOINT Controller manages all incoming HTTP Requests.
 */
@Configuration(proxyBeanMethods = false)
public class GreetingEndpoint {

    /**
     * The method, "route", sets up a routing function that maps incoming HTTP GET requests to the path "/say/hello/{n}" and
     * with an "Accept" header of "application/json" to a specific behavior.
     * Inside the method, it extracts the "n" variable from the path variable and uses it to call the "sayHelloTo" method
     * on an instance of the "SayHelloControllerApi" class.
     * The "httpSerializer.getHelloPayload()" method is called to get the response payload.
     *
     * @param httpSerializer
     * @return
     */
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

    /**
     * The method "getSayHelloController" is responsible for connecting the different layers of the application.
     * It takes in an implementation of the technical PRESENTER (HttpSerializer) which allows the GreetingsPresenter
     * in the green layer to communicate. The Use Case in the red layer is passed the Presenter of the green layer,
     * which implements the TalkToGreen api.
     * Finally, the Controller in the green layer receives the Use Case which implements the Use Case API.
     * The method returns an instance of the SayHelloControllerApi class.
     *
     * @param talkToBlue
     * @return
     */
    private static SayHelloControllerApi getSayHelloController(TalkToBlue talkToBlue) {
        TalkToGreen talkToGreen = new GreetingsPresenter(talkToBlue);
        UseCaseApi useCaseApi = new UseCaseImpl(talkToGreen);
        return new SayHelloController(useCaseApi);
    }
}
