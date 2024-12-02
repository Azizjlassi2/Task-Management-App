package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseEnvVar {
    private static Properties properties;

    /**
     * 
     */
    public DatabaseEnvVar() {
        properties = new Properties();

        try (FileInputStream fis = new FileInputStream("src\\.env")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEnvVariable(String VariableName) {
        return properties.getProperty(VariableName);

    }

}
