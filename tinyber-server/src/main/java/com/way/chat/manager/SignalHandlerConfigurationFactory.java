package com.tiny.chat.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SignalHandlerConfigurationFactory {
	
    private static final Logger LOG = LoggerFactory.getLogger(SignalHandlerConfigurationFactory.class.getName());

    private static final String DEFAULT_CLASSPATH_CONFIGURATION_FILE = "signal.properties";
    
    /**
     * Constructor.
     */
    private SignalHandlerConfigurationFactory() {

    }

    /**
     * Configures a bean from an property file.
     */
    public static List<SignalHandlerConfiguration> parseConfiguration(final File file) throws Exception {
        if (file == null) {
            throw new Exception("Attempt to configure command from null file.");
        }
        LOG.debug("Configuring command from file: {}", file);
        List<SignalHandlerConfiguration> configurations  = null;
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            configurations = parseConfiguration(input);
        } catch (Exception e) {
            throw new Exception("Error configuring from " + file + ". Initial cause was " + e.getMessage(), e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                LOG.error("IOException while closing configuration input stream. Error was " + e.getMessage());
            }
        }
        return configurations;
    }
    /**
     * Configures a bean from an property file available as an URL.
     */
    public static List<SignalHandlerConfiguration> parseConfiguration(final URL url) throws Exception {
        LOG.debug("Configuring command from URL: {}", url);
        List<SignalHandlerConfiguration> configurations;
        InputStream input = null;
        try {
            input = url.openStream();
            configurations = parseConfiguration(input);
        } catch (Exception e) {
            throw new Exception("Error configuring from " + url + ". Initial cause was " + e.getMessage(), e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                LOG.error("IOException while closing configuration input stream. Error was " + e.getMessage());
            }
        }
        return configurations;
    }
    /**
     * Configures a bean from an property file in the classpath.
     */
    public static List<SignalHandlerConfiguration> parseConfiguration() throws Exception {
        ClassLoader standardClassloader = Thread.currentThread().getContextClassLoader();
        URL url = null;
        if (standardClassloader != null) {
            url = standardClassloader.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
        }
        if (url == null) {
        	url = SignalHandlerConfigurationFactory.class.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
        }
        if (url != null) {
            LOG.debug("Configuring command from signal.properties found in the classpath: " + url);
        } else {
            LOG.warn("No configuration found. Configuring command from signal.properties "
                    + " found in the classpath: {}", url);

        }
        List<SignalHandlerConfiguration> configurations = parseConfiguration(url);
        return configurations;
    }
    
    /**
     * Configures a bean from an property input stream.
     */
    public static List<SignalHandlerConfiguration> parseConfiguration(final InputStream inputStream) throws Exception {

        LOG.debug("Configuring command from InputStream");

        List<SignalHandlerConfiguration> configurations = new ArrayList<SignalHandlerConfiguration>();
        try {
            Properties props = new Properties();
            props.load(inputStream);
			for(String key : props.stringPropertyNames()){
    			configurations.add(new SignalHandlerConfiguration(key , props));
    		}
        } catch (Exception e) {
            throw new Exception("Error configuring from input stream. Initial cause was " + e.getMessage(), e);
        }
        return configurations;
    }
}