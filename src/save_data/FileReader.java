package save_data;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FileReader {

    private final TreeMap<String, String> image_app_list;
    private final File data_file;


    public FileReader(){
        image_app_list = new TreeMap<>();
        data_file = new File(System.getProperty("user.home") + File.separator +
                                      "Documents" + File.separator +
                                      "MenuBar" + File.separator +
                                      "Data" + File.separator +
                                      "Data.txt");

    }

    public void read_file(){
        try (final BufferedReader buffRead = new BufferedReader(new java.io.FileReader(data_file))) {

            String key;
            String value;

            while((key = buffRead.readLine()) != null && (value = buffRead.readLine()) != null){
                  image_app_list.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getters
    public Map<String, String> getMap(){ return image_app_list; }

    public int getSize() {
        return image_app_list.size();
    }
}
