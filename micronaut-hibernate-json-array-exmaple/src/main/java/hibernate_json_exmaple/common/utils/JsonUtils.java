package hibernate_json_exmaple.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.commons.lang3.StringUtils;


import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper OBJECT_MAPPER = null;

    public static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new StringTrimModule());
            OBJECT_MAPPER = mapper;
        }
        return OBJECT_MAPPER;

    }

    /**
     * Converts an object to its json string
     *
     * @param data Object
     * @return JSON string
     */
    public static <T> String toJson(T data) {
        if (data == null) {
            return StringUtils.EMPTY;
        }
        try {
            return getObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Converts a json string to its POJO
     *
     * @param json  JSON String
     * @param clazz Class type of the POJO
     * @return POJO
     */
    public static <T> T fromJsonToObject(String json, Class<T> clazz)
    {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts String to Object
     *
     * @param json          String
     * @param typeReference Type reference
     * @return
     */
    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference)
    {
        try {
            return getObjectMapper().readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to convert object to byte[]
     *
     * @param object Object
     * @return byte[]
     * @throws IOException Exception
     */
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = getObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    /**
     *
     * @param json string
     * @return JsonNode
     */
    public static JsonNode fromJsonStringToJsonNode(String json) {
        try {
            return getObjectMapper().readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJsonNodeToObject(JsonNode node, Class<T> clazz)
    {
        try {
            return getObjectMapper().treeToValue(node, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}