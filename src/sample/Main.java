package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import start_menu.StartMenuController;

import java.io.File;

public class Main extends Application {

    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        System.out.println("Has been modified: " + checkFolder());
        final StartMenuController c_main_controller = new StartMenuController();
        c_main_controller.initialize_controller_list();
        c_main_controller.read_data();
        c_main_controller.updateData();
        c_main_controller.start();
        c_main_controller.connectViews();
    }
    private boolean checkFolder() {

        final String project_folder = System.getProperty("user.home") + File.separator +
                                                         "Documents" + File.separator +
                                                         "MenuBar";
        boolean isCreated = false;
        final File newDirMenu = new File(project_folder);
        if (!newDirMenu.exists()){ isCreated = newDirMenu.mkdirs(); }

        final File newDirPng = new File(project_folder + File.separator + "Icons");
        if (!newDirPng.exists()){isCreated =  newDirPng.mkdirs();}

        final File newDirApp = new File(project_folder + File.separator + "Apps");
        if (!newDirApp.exists()){isCreated = newDirApp.mkdirs();}

        final File newDirTextIcons = new File(project_folder + File.separator + "TextIcons");
        if (!newDirTextIcons.exists()){ isCreated = newDirTextIcons.mkdirs();}

        return isCreated;


    }
}
