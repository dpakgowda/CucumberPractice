package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    protected Properties properties = null;
    protected FileInputStream fis = null;
    public ReadConfig() {

        try {
            File src = new File(Constant.CONFIG_PROPERTIES_DIRECTORY);
            fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser(){
        if (properties.getProperty("Browser") == null)
            return "";
        return properties.getProperty("Browser");
    }
}
