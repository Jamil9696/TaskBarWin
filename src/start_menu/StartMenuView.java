package start_menu;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class StartMenuView {

    private final Stage stage;
    private final BorderPane bPane;

    public StartMenuView(){
        stage = new Stage();
        bPane = new BorderPane();

    }

    public void start(){
      
        
        final Scene scene = new Scene(bPane);


       scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("view.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }
    public void setPosition(final int position,final Node node){
        switch(position){
            case 0: bPane.setBottom(node);
            break;
            case 1: bPane.setTop(node);
            break;
            case 2: bPane.setLeft(node);
            break;
            case 3: bPane.setRight(node);
            break;
            case 4: bPane.setCenter(node);
            default:

        }
    }
    public void show_view_bottom_right(){
       bPane.getBottom().setVisible(true);
       bPane.getCenter().setVisible(false);
       bPane.getRight().setVisible(true);
    }

    public void show_view_center(){
        bPane.setRight(null);
        bPane.getCenter().setVisible(true);
        bPane.setBottom(null);
    }
}
