package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Desculpe, não foi possível encontrar o arquivo config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Erro ao carregar o arquivo de configuração: " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
