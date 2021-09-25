package save_data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


public class FileWriter {
    private final TreeMap<String, String> image_app_list;
    private final File data_file;

    public FileWriter(){
        image_app_list = new TreeMap<>();
        data_file = new File(System.getProperty("user.home") + File.separator +
                                      "Documents" + File.separator +
                                      "MenuBar" + File.separator +
                                      "Data" + File.separator +
                                      "Data.txt");
    }
    // ===================================== read Data ==========================================================
    public void read_folder() {
        final String project_folder = System.getProperty("user.home") + File.separator +
                                                          "Documents" + File.separator +
                                                          "MenuBar";

        final String AppPath = project_folder + File.separator +"Apps";
        final String ImagePath = project_folder + File.separator + "Icons";

        final String[] appNames = new File(AppPath).list();
        final String[] imageNames = new File(ImagePath).list();

        // read image folder
        if(appNames == null || imageNames == null) return;

        Arrays.stream(imageNames)
                .forEach(imageName -> fillList(imageName, appNames));
    }
    private void fillList(final String imageName,final String... appNames){

        final String name_without_format = delete_extension(imageName).toLowerCase(Locale.ROOT);

        if (imageName.contains(".xlsx") || imageName.contains(".pdf") || imageName.contains(".docx") || imageName.contains(".txt")) {
            // source of a file_Icon
            image_app_list.put(imageName, imageName);
            return;
        }

        if (name_without_format.contains(".")) { // source of a web Icon
            image_app_list.put(name_without_format, name_without_format);
            return;
        }

          Arrays.stream(appNames)
                .filter(appName-> appName.toLowerCase(Locale.ROOT).contains(name_without_format))
                .forEach( appName -> image_app_list.put(name_without_format, appName));
    }

    public String delete_extension(final String fileName) {
        final StringBuilder read_string = new StringBuilder(fileName);

        int length = read_string.length() - 1;
        for (char c = read_string.charAt(length); c != '.'; c = read_string.charAt(--length)) {
            read_string.deleteCharAt(length);
        }
        read_string.deleteCharAt(length);
        return read_string.toString();
    }
    // ========================================= write Data =====================================================
    public void write_file(){
        try{
            final java.io.FileWriter fileWriter = new java.io.FileWriter(data_file, true);
            final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(final Map.Entry<String, String> entry : image_app_list.entrySet()){
                bufferedWriter.write(entry.getKey() + ".png" + System.lineSeparator() + entry.getValue() + System.lineSeparator());
            }
            bufferedWriter.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // ========================================== Save Data ==================================================
    public void save_menu(){
        image_app_list.clear();
        data_file.delete();
        read_folder();
        write_file();
    }
}