package start_menu;

import interfaces.Controllers;
import interfaces.Icons;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import taskbar_bottom.ControllerBottom;
import taskbar_bottom.ViewBottom;
import taskbar_center.ControllerCenter;
import taskbar_center.ViewCenter;
import taskbar_right.ControllerRight;
import taskbar_right.ViewRight;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StartMenuController {

    private enum VIEW_POSITION{ BOTTOM , TOP, LEFT, RIGHT, CENTER}

    private final StartMenuView mainView;
    private final StartMenuModel model;
    private final Controllers[] controller_list = { new ControllerBottom(new ViewBottom()),
                                                    new ControllerCenter(new ViewCenter()),
                                                    new ControllerRight(new ViewRight())};

    public StartMenuController(){
        mainView = new StartMenuView();
        model = new StartMenuModel();

    }
    public void initialize_controller_list(){

        for (final Controllers controllers : controller_list) {
            controllers.start();
        }
    }
    public void read_data(){
        model.readData();
    }

    public void updateData(){

        for(final Icons icon : model.getIconList()) {
            updateViews(icon);
        }
    }

    public void start(){


        mainView.start();
        mainView.setPosition(VIEW_POSITION.BOTTOM.ordinal(),controller_list[0].getScrollPane());
        mainView.setPosition(VIEW_POSITION.RIGHT.ordinal(),controller_list[2].getScrollPane());
        mainView.setPosition(VIEW_POSITION.CENTER.ordinal(),controller_list[1].getLayoutContainer());

    }
    private void updateViews(final Icons icon){

        controller_list[0].add_new_icon(icon.getImageName(), icon.getTarget());
        controller_list[1].add_new_icon(icon);
        controller_list[2].add_new_icon(icon.getTarget(),icon.getTarget());
        connect_delete_Buttons(icon.getButton());
    }
    public void connectViews(){


        final Button start_menu_btn = (Button)controller_list[0].getLayoutContainer().getChildren().get(0);
        start_menu_btn.setOnMouseClicked(e1 -> start_menu());

        // ESC -Button -> close startMenu and display View Bottom and View Right
        controller_list[1].getLayoutContainer().setOnKeyPressed(this::close_start_menu);

        // View Bottom Add Icons via Drag and Dropped
        controller_list[0].getLayoutContainer().setOnDragDropped(e1 -> { try { dragDropped(e1);
        } catch (IOException e) { e.printStackTrace(); } });

        controller_list[0].getLayoutContainer().setOnDragOver(event ->{

            if (event.getGestureSource() != this && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });



    }
    private void connect_delete_Buttons(final Button btn){

        final VBox mini_vBox = (VBox)btn.getParent();
        final Circle delete_icon = (Circle)mini_vBox.getChildren().get(2);
        delete_icon.setOnMouseClicked(e1 -> delete_icon(delete_icon));
    }
    private void delete_icon(final Circle btn){

        final VBox parent_box = (VBox)btn.getParent();
        final Button icon_button = (Button)parent_box.getChildren().get(0);
        model.remove(icon_button);

        for (final Controllers controller : controller_list){
            controller.remove(icon_button);
        }
        model.save_menu();
    }

    public void dragDropped(final DragEvent event) throws IOException {

        final List<File> list = event.getDragboard().getFiles();

        //
        final List<File> filteredList = list.stream()
                                      .filter(it -> !model.fileExists(it))
                                      .collect(Collectors.toList());


        if(filteredList.isEmpty()){ return; }

        final Thread thread1 = new Thread(() -> filteredList.forEach(model::copy_dropped_files));

        thread1.start();

        for (File file : filteredList) {
            model.add_new_icon(file.getName(),file.getName());
            updateViews(model.getIconList().get(model.getIconList().size()-1));

        }
        model.save_menu();
    }

    private void start_menu(){
        mainView.show_view_center();
    }
    private void close_start_menu(final KeyEvent e1){


        if(e1.getCode().equals(KeyCode.ESCAPE)){

            mainView.setPosition(VIEW_POSITION.RIGHT.ordinal(), controller_list[2].getScrollPane());
            mainView.setPosition(VIEW_POSITION.BOTTOM.ordinal(), controller_list[0].getScrollPane());
            mainView.setPosition(VIEW_POSITION.CENTER.ordinal(), controller_list[1].getLayoutContainer());
            mainView.show_view_bottom_right();
        }
      }
   }

