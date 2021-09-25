package icon_types;

import interfaces.Icons;
import javafx.scene.control.Button;

import java.io.File;

public abstract class Icon implements Icons {

    private final Button iconBtn;

    protected final String project_path;
    protected final String app_path;
    protected final String image_path;
    protected final String textIcon_path;



    public Icon(){
        iconBtn = new Button();

        project_path = System.getProperty("user.home") + File.separator +
                                          "Documents" + File.separator +
                                          "MenuBar";

        app_path = project_path + File.separator + "Apps";
        image_path = project_path + File.separator + "Icons";
        textIcon_path = project_path + File.separator + "TextIcons";


    }

    public Button getButton() {
        return iconBtn;
    }

     protected String delete_extension(final String fileName) {
        final StringBuilder read_string = new StringBuilder(fileName);

        int length = read_string.length() - 1;
        for (char c = read_string.charAt(length); c != '.'; c = read_string.charAt(--length))
            read_string.deleteCharAt(length);

        read_string.deleteCharAt(length);
        return read_string.toString();
    }

}
