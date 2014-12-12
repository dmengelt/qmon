package ch.filecloud.queuemonitor.common;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by domi on 02/10/14.
 */
public class QmonEnvironmentConfiguration {

    private static Log LOGGER = LogFactory.getLog(QmonEnvironmentConfiguration.class);

    private static final String ENCODING = "UTF-8";
    private static final String QMON_DEFAULT_CONFIG_JSON = "qmonDefaultConfig.json";

    private List<QmonEnvironment> environments; // used by Gson
    private QmonEnvironment currentEnvironment;

    public QmonEnvironment getCurrent() {
        return currentEnvironment;
    }

    public void setCurrent(String environmentName) {
        currentEnvironment = get(environmentName);
    }

    public List<QmonEnvironment> getAll() {
        return environments;
    }

    private void sort() {
        Collections.sort(environments);
        currentEnvironment = environments.get(0);
    }

    private QmonEnvironment get(String environmentName) {
        return environments.stream().filter(env -> env.getName().equals(environmentName))
                .findFirst()
                .orElse(getCurrent());
    }

    public static QmonEnvironmentConfiguration create(String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new QmonConfigurationFailedException("No qMon configuration specified! Please use -Dqmon.config=/your/config.json");
        }

        try {
            File configFile = new File(filename);
            String configJson = FileUtils.readFileToString(configFile, ENCODING);
            return createInternal(configJson);
        } catch (IOException e) {
            LOGGER.warn("An error occurred while trying to read the specified config file!");
            throw new QmonConfigurationFailedException("An error occurred while trying to read the specified config file " + filename, e);
        }
    }

    private static QmonEnvironmentConfiguration createInternal(String configJson) {
        Gson gson = new Gson();
        QmonEnvironmentConfiguration qmonEnvironmentConfiguration = gson.fromJson(configJson, QmonEnvironmentConfiguration.class);
        qmonEnvironmentConfiguration.sort();
        return qmonEnvironmentConfiguration;
    }
}
