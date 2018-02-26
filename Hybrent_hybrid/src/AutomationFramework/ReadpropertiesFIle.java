package AutomationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadpropertiesFIle {

	public String readData(String key, String fileName) {
        String value = "";
        try {

                      Properties properties = new Properties();
                      File file  = new File("File.properties");
                      if (file.exists()) {
                                          properties.load(new FileInputStream(file));
                                          value = properties.getProperty(key);
                     }
         } catch (Exception e) {
                     System.out.println(e);
         }
        return value;
 }

}
