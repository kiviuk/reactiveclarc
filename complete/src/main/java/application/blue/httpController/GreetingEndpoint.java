package application.blue.httpController;

import application.blue.talkToBlue.HttpSerializer;
import application.blue.talkToBlue.ResultStash;
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
 * BLUE LAYER = ENGINEERING TEAM are the "IO-Workers", they are the experts on how to "IO"
 * The technical ENDPOINT Controller manages all incoming HTTP Requests.
 */
@Configuration(proxyBeanMethods = false)
public class GreetingEndpoint {

    /**
     * The method, "route", sets up a routing function that maps incoming HTTP GET requests to the path "/say/hello/{n}".
     * Inside the method, it extracts the "n" variable from the path variable and uses it to call the "sayHelloTo" method
     * on an instance of the "SayHelloControllerApi" class.
     * The "httpSerializer.serialize()" method is called to get the response payload.
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
                    ResultStash resultStash = new ResultStash();
                    getSayHelloController(resultStash).sayHelloTo(name);
                    return httpSerializer.serialize(resultStash);
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
     * @param resultStash
     * @return
     */
    private static SayHelloControllerApi getSayHelloController(TalkToBlue resultStash) {

        // poor man's factory method which can be further
        // abstracted through the abstract factory pattern

        TalkToGreen talkToGreen = new GreetingsPresenter(resultStash);
        UseCaseApi talkToUseCase = new UseCaseImpl(talkToGreen);

        // new SayHelloController(new UseCaseImpl(new GreetingsPresenter(resultStash)));

        return new SayHelloController(talkToUseCase);
    }
}
