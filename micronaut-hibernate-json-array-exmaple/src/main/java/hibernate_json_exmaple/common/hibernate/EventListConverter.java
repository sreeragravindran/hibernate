package hibernate_json_exmaple.common.hibernate;

import com.fasterxml.jackson.databind.JsonNode;
import hibernate_json_exmaple.common.utils.JsonUtils;
import hibernate_json_exmaple.entities.events.AccountClosedEvent;
import hibernate_json_exmaple.entities.events.AccountOpenedEvent;
import hibernate_json_exmaple.entities.events.Event;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class EventListConverter implements AttributeConverter<List<Event>, String> {

    @Override
    public String convertToDatabaseColumn(List<Event> events) {
        return JsonUtils.toJson(events);
    }

    @Override
    public List<Event> convertToEntityAttribute(String jsonString) {
        List<Event> eventList = new ArrayList<>();
        JsonNode jsonNode = JsonUtils.fromJsonStringToJsonNode(jsonString);

        for(int i =0; i < jsonNode.size(); i++){
           JsonNode node = jsonNode.get(i);
            if(node.get("type").asText().equals("accountOpened")) {
                eventList.add(JsonUtils.fromJsonNodeToObject(node, AccountOpenedEvent.class));
            } else if(node.get("type").asText().equals("accountClosed")) {
                eventList.add(JsonUtils.fromJsonNodeToObject(node, AccountClosedEvent.class));
            }
        }

        return eventList;
    }
}