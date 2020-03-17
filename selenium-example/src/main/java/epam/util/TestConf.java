package epam.util;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestConf{
    private static final Config BASE_CONFIG = ConfigFactory.load();
    private static final ObjectMapper MAPPER = buildMapper();
    private static final TestConf TEST_CONF = buildTestConfSingleton(BASE_CONFIG);

    private String searchUrl;

    public static final TestConf getTestConf(){
        return TEST_CONF;
    }

    /**
     * @return the searchUrl
     */
    public String getSearchUrl() {
        return searchUrl;
    }

    private static ObjectMapper buildMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    private static TestConf buildTestConfSingleton(Config baseConfig) {
        Map<String, Object> unwrapped = baseConfig.root().unwrapped();
        TestConf testConf = MAPPER.convertValue(unwrapped, TestConf.class);
        return testConf;
    }


}