package application.blue.talkToBlue;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * BLUE LAYER = ENGINEERING TEAM
 * THe technical PRESENTER converts the payload data into the final HTTP RESPONSE
 */
@Component
@Scope("prototype")
public class HttpSerializer {

    public Mono<ServerResponse> serialize(ResultStash resultStash) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(resultStash.getPayloadDTO()));
    }

}
