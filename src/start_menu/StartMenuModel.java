package start_menu;

import create_icons.Icon_template;
import interfaces.Icons;
import javafx.scene.control.Button;
import save_data.FileReader;
import save_data.FileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


public class StartMenuModel {

    private final ArrayList<Icons> iconList;
    private final FileReader reader;
    private final FileWriter writer;


    public StartMenuModel() {
        iconList = new ArrayList<>();
        reader = new FileReader();
        writer = new FileWriter();
    }
    public void readData(){

        final String data_path = System.getProperty("user.home") + File.separator +
                "Documents" + File.separator +
                "MenuBar" + File.separator +
                "Data" + File.separator +
                "Data.txt";


        if (Objects.requireNonNull(new File(data_path).getParentFile().list()).length == 0) {
            writer.read_folder();
            writer.write_file();
        }
        reader.read_file();

        for (final Map.Entry<String, String> entry : reader.getMap().entrySet()) {
            add_new_icon(entry.getKey(), entry.getValue());
        }
    }
    public List<Icons> getIconList(){
        return iconList;
    }


    public void add_new_icon(final String imageName,final String target){

       Icons new_icon = Icon_template.identifyComponents(imageName, target)
                                     .createIcon(imageName, target);
       iconList.add(new_icon);

    }

    public void save_menu() {

        final Thread newThread = new Thread(writer::save_menu);
        newThread.start();
    }
    public void copy_dropped_files(final File file){
        final Path from = Paths.get(file.toURI());
        Icon_template template = Icon_template.identifyComponents(file.getName());

        final String appPath = System.getProperty("user.home") + File.separator + "Documents" +
                File.separator + "MenuBar" + File.separator + "Apps" +
                File.separator + file.getName();

        final String imagePath = System.getProperty("user.home") + File.separator + "Documents" +
                File.separator + "MenuBar" + File.separator + "Icons" +
                File.separator + file.getName();


        Path target;
        if(template.check_template_type(Icon_template.DesktopIcon)) {
            target = Paths.get(appPath);
        }else{
            target = Paths.get(imagePath);
        }

        try {
            Files.copy(from, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileExists(final File file) {

        final String fileName = writer.delete_extension(file.getName()).toLowerCase(Locale.ROOT);
        return iconList.stream()
                       .anyMatch(it -> it.getImageName().contains(fileName)
                             || (it.getButton().getId() != null
                             && it.getButton().getId().contains(file.getName())));
    }

    public void remove(final Button btn) {
        for ( int i = 0; i < iconList.size(); i++) {
            if (iconList.get(i).getButton().equals(btn)) {
                iconList.remove(i);
                break;
            }
        }

    }
}
