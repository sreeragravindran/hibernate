package hibernate_json_exmaple.common.hibernate;

import hibernate_json_exmaple.common.utils.JsonUtils;
import hibernate_json_exmaple.entities.Address;

import javax.persistence.AttributeConverter;

public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address pojo) {

        String jsonString = null;
        try {
            jsonString = JsonUtils.toJson(pojo);
        } catch (Exception ex) {
            //logger.error("JSON writing error", e);
        }
        return jsonString;
    }

    @Override
    public Address convertToEntityAttribute(String jsonString) {

        Address pojo = null;
        try {
//            customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
            pojo = JsonUtils.fromJsonToObject(jsonString, Address.class);
        } catch (Exception ex) {
            //logger.error("JSON reading error", e);
        }

        return pojo;
    }
}
