package interfaces;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;




public interface Controllers {

    Pane getLayoutContainer();
    ScrollPane getScrollPane();
    void start();
    void remove(Button icon_btn);
    void add_new_icon(String imageName , String target);
    default void add_new_icon(Icons icon){}



}
