package madtest.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by qct on 2015/7/7.
 */
public class PropConfig {

    public static PropertiesConfiguration getConfig(String fileName) {
        try {
            return new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
