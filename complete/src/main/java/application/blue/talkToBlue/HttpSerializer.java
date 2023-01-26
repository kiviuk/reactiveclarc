package application.blue.talkToBlue;

import application.green.talkToGreen.Content;
import application.green.TalkToBlue;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * BLUE LAYER = technical LAYER
 * THe technical PRESENTER converts the payload data into the final HTTP RESPONSE
 */
@Component
@Scope("prototype")
public class HttpSerializer implements TalkToBlue {

    private PayloadDTO payloadGreets;

    public Mono<ServerResponse> getHelloPayload() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(payloadGreets));
    }

    @Override
    public void setContent(Content greets) {
        this.payloadGreets = new PayloadDTO(greets.getContent());
    }

}
