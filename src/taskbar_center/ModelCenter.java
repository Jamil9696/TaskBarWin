package taskbar_center;

import create_icons.Icon_template;
import interfaces.Icons;
import javafx.scene.control.Button;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ModelCenter {

    private final List<Icons> iconList;

    public ModelCenter() {
        iconList = new ArrayList<>();
    }

    public void remove_icon(final Button btn) {


      final List<Icons> filteredList = iconList.stream()
                                           .filter(it -> it.getButton().equals(btn))
                                           .peek(this::deleteFile)
                                           .collect(Collectors.toList());

        filteredList.forEach(iconList::remove);

    }

    boolean add_new_icon(final String target){

        final Icon_template c = Icon_template.identifyComponents(target);
        return c.check_template_type(Icon_template.FileIcon);

    }

    private void deleteFile(final Icons icon) {

         String target = icon.getImagePath();

        if(Icon_template.identifyComponents(icon.getTargetPath()).check_template_type(Icon_template.FileIcon)){
            target = icon.getTargetPath();
        }

        try {
            Files.deleteIfExists(Paths.get(target));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public List<Icons>getList(){
        return iconList;
    }


}
