package com.xincao.common.configuration;

import com.xincao.common.util.tool.PropertiesReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author caoxin
 */
class ConfigAboutProperties {

    private static final Logger log = LoggerFactory.getLogger(ConfigAboutProperties.class);
    private String path;
    private Properties[] props;

    /**
     * 加载配置文件（.properties）
     */
    public void initConfigAboutPropertiesFromDirectory() {
        try {
            Util.printSection("Config");
            this.props = PropertiesReader.loadAllFromDirectory(path);
            log.info("Can't load .properties file from path = {" + path + "}");
        } catch (Exception e) {
            log.error("Can't load .properties file from path = {" + path + "}", e);
            throw new Error("Can't load .properties file from path = {" + path + "}", e);
        }
    }

    public <T extends Object> void load (T t) {
        ConfigurableProcessor.process(t, props);
    }

    public void setPath (String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        ConfigAboutProperties c  = new ConfigAboutProperties();
        c.setPath("./config/");
        c.initConfigAboutPropertiesFromDirectory();
        c.load(Config.class);
    }
}