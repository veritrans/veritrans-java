package id.co.veritrans.mdk.v1.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import id.co.veritrans.mdk.v1.exception.JsonDeserializeException;
import org.apache.http.HttpResponse;

/**
 * Created by andes on 5/8/15.
 */
public class JsonUtil {

    private static final ObjectMapper jsonMapper = new VtJsonObjectMapper();

    public static <T> T fromJson(HttpResponse httpResponse, Class<T> clazz) throws JsonDeserializeException {
        try {
            return jsonMapper.readValue(httpResponse.getEntity().getContent(), clazz);
        } catch (Exception e) {
            throw new JsonDeserializeException(e);
        }
    }

    public static String toJson(Object o) {
        try {
            return jsonMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return o.toString();
        }
    }

    private static final class VtJsonObjectMapper extends ObjectMapper {
        public VtJsonObjectMapper() {
            _serializationConfig = getSerializationConfig()
                    .withSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .with(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                    .with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .with(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

            _deserializationConfig = getDeserializationConfig()
                    .with(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        }
    }
}
