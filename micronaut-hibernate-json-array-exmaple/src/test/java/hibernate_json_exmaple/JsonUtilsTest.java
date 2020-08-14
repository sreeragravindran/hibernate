package hibernate_json_exmaple;

import com.fasterxml.jackson.databind.JsonNode;
import hibernate_json_exmaple.common.utils.JsonUtils;
import hibernate_json_exmaple.entities.Customer;
import hibernate_json_exmaple.entities.events.AccountClosedEvent;
import hibernate_json_exmaple.entities.events.AccountOpenedEvent;
import hibernate_json_exmaple.entities.events.Event;
import io.micronaut.test.annotation.MicronautTest;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@MicronautTest
public class JsonUtilsTest {

    @Test
    public void should_give_json_node_from_json(){
        String json =
                "[{\n" +
                "  \"line1\" : \"line 1\",\n" +
                "  \"line2\" : \"line 2\",\n" +
                "  \"pincode\" : \"1231\"\n" +
                "},{\n" +
                "  \"line1\" : \"line 1\",\n" +
                "  \"line2\" : \"line 2\",\n" +
                "  \"pincode\" : \"1231\"\n" +
                "}]";

        JsonNode jsonNode = JsonUtils.fromJsonStringToJsonNode(json);

        Assertions.assertTrue(jsonNode != null);
        Assertions.assertTrue(jsonNode.isArray());

    }


    @Test
    public void should_convert_list_of_objects_to_json_list() throws Exception{

        List<Event> events = new ArrayList<>();
        // set events
        events.add(new AccountOpenedEvent("1"));
        events.add(new AccountClosedEvent("2"));
        events.add(new AccountOpenedEvent("3"));
        events.add(new AccountClosedEvent("4"));

        String json = JsonUtils.getObjectMapper().writeValueAsString(events);

        JsonNode jsonNode = JsonUtils.fromJsonStringToJsonNode(json);

        Assertions.assertTrue(jsonNode != null);
        Assertions.assertTrue(jsonNode.isArray());

    }

}
