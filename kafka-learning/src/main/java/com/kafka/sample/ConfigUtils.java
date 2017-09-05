package com.kafka.sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

	public static Properties getConfiguration(String classpathFileName) throws IOException {
		InputStream configStream = SimpleConsumer.class.getClassLoader().getResourceAsStream(classpathFileName+".properties");
		Properties prop = new Properties();
		prop.load(configStream);
		configStream.close();
		return prop;
	}
	
}
