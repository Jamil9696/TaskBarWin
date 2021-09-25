package taskbar_right;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ViewRight {

    private final ScrollPane pane;
    private final VBox vBox;

    public ViewRight() {
        pane = new ScrollPane();
        vBox = new VBox();
    }
    public void setStyle(){

        setScrollPane(pane);
        vBox.getStyleClass().add("vbox_right");
    }

    private void setScrollPane(final ScrollPane sp) {
        sp.setContent(vBox);
        sp.setPannable(true);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.getStyleClass().add("scrollpaneRight");
    }

    public ScrollPane getScrollPane(){ return pane; }

    public void show_iconBtn(final Button btn, final String imageName,final String targetName){

        btn.setStyle("-fx-background-image:url(file:///C://Users//jamil//Documents//MenuBar//TextIcons//" + imageName + ");");
        final VBox child_vBox = new VBox(btn, new Label(targetName));

        child_vBox.getStyleClass().add("views");
        vBox.getChildren().add(child_vBox);

    }

    public void remove(final Button btn) {

        for (int i = 0; i < vBox.getChildren().size(); i++){
            final VBox mini_vbox = (VBox) vBox.getChildren().get(i);

            if (mini_vbox.getChildren().get(0).getId().equals(btn.getId())){
                vBox.getChildren().remove(i);
            }
        }
    }
}
