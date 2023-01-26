package application.blue.talkToBlue;

import application.green.talkToGreen.Payload;
import application.green.TalkToBlue;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Scope("prototype")
public class HttpSerializer implements TalkToBlue {

    private Payload payloadGreets;

    public Mono<ServerResponse> getHelloPayload() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                             .body(BodyInserters.fromValue(payloadGreets));
    }

    @Override
    public void setGreeting(Payload greets) {
        this.payloadGreets = greets;
    }

}
