package hibernate_json_exmaple.entities.events;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AccountClosedEvent extends Event {

    public AccountClosedEvent(){
    }

    public AccountClosedEvent(String id){
        super(id, "accountClosed");
    }
}
