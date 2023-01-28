package application.blue.httpController;

import application.blue.talkToBlueImpl.ResultStash;
import application.red.TalkToGreen;
import application.green.TalkToBlue;
import application.green.api.SayHelloControllerImpl;
import application.green.api.ControllerApi;
import application.green.talkToGreenImpl.Greeter;
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
 * BLUE LAYER = ENGINEERING TEAM, they are the "IO-Workers", the experts on "how to do IO"
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
                    buildSayHelloController(resultStash).sayHelloTo(name);
                    return httpSerializer.serialize(resultStash);
                }
            );
    }


    private static ControllerApi buildSayHelloController(TalkToBlue resultStash) {

        // poor man's factory method that can be improved by using the abstract factory pattern

        TalkToGreen talkToGreen = new Greeter(resultStash);
        UseCaseApi talkToUseCase = new UseCaseImpl(talkToGreen);

        // new SayHelloController(new UseCaseImpl(new Greeter(resultStash)));

        return new SayHelloControllerImpl(talkToUseCase);
    }
}
