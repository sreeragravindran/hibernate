package hibernate_json_exmaple.entities.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.http.annotation.Get;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public abstract class Event {

    private String id;
    private String type;
    private LocalDateTime timestamp;

    public Event(){
        this.timestamp = LocalDateTime.now();
    };

    public Event(String id, String type) {
        this();
        this.id = id;
        this.type = type;
    }

}
