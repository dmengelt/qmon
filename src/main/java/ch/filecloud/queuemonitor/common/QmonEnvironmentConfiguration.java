package ch.filecloud.queuemonitor.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medo on 02/10/14.
 */
public class QmonEnvironmentConfiguration {

    private List<QmonEnvironment> qmonEnvironments;

    public void parseConfiguration(String filename) {
        if(filename == null || filename.isEmpty()) {
            // parse qmonDefaultConfig
        } else {
            qmonEnvironments = new ArrayList<QmonEnvironment>();
        }

    }


}
