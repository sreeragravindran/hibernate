package hibernate_json_exmaple.entities.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AccountOpenedEvent extends Event {

    public AccountOpenedEvent(){
    }

    public AccountOpenedEvent(String id){
        super(id, "accountOpened");
    }
}
