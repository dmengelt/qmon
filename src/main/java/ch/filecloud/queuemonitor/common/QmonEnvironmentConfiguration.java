package ch.filecloud.queuemonitor.common;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by medo on 02/10/14.
 */
public class QmonEnvironmentConfiguration {

    private static Log LOGGER = LogFactory.getLog(QmonEnvironmentConfiguration.class);

    private static final String ENCODING = "UTF-8";
    private static final String QMON_DEFAULT_CONFIG_JSON = "qmonDefaultConfig.json";

    private List<QmonEnvironment> environments;

    public static QmonEnvironmentConfiguration create(String filename) throws IOException {
        if (filename != null && !filename.isEmpty()) {
            File configFile = new File(filename);
            try {
                String configJson = FileUtils.readFileToString(configFile, ENCODING);
                return createInternal(configJson);
            } catch (IOException e) {
                LOGGER.warn("An error occurred while trying to read the specified config file. Falling back to default config.");
            }

        }
        return parseDefaultConfiguration();
    }

    private static QmonEnvironmentConfiguration parseDefaultConfiguration() throws IOException {
        InputStream defaultConfigJson = QmonEnvironmentConfiguration.class.getClassLoader().getResourceAsStream(QMON_DEFAULT_CONFIG_JSON);
        String defaultConfig = IOUtils.toString(defaultConfigJson, ENCODING);
        return createInternal(defaultConfig);
    }

    private static QmonEnvironmentConfiguration createInternal(String configJson) {
        Gson gson = new Gson();
        QmonEnvironmentConfiguration qmonEnvironmentConfiguration = gson.fromJson(configJson, QmonEnvironmentConfiguration.class);
        return qmonEnvironmentConfiguration;
    }

}
