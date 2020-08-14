package hibernate_json_exmaple.common.hibernate;

import hibernate_json_exmaple.common.utils.JsonUtils;
import javax.persistence.AttributeConverter;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = JsonUtils.toJson(customerInfo);
        } catch (Exception ex) {
            //logger.error("JSON writing error", e);
        }
        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {

        Map<String, Object> customerInfo = null;
        try {
//            customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
            customerInfo = JsonUtils.fromJsonToObject(customerInfoJSON, Map.class);
        } catch (Exception ex) {
            //logger.error("JSON reading error", e);
        }

        return customerInfo;
    }

}