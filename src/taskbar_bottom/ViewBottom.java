package taskbar_bottom;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ViewBottom {

    private final ScrollPane pane;
    private final HBox hBox;

    public ViewBottom() {
        pane = new ScrollPane();
        hBox = new HBox(new Button());


    }
    public void setStyle(){
        hBox.getChildren().get(0).setStyle("-fx-background-image:url(file:///C://Users//jamil//Documents//MenuBar//windows10.png);");
        setScrollPane(pane, hBox);
        hBox.getStyleClass().addAll("hbox_bottom" );
        pane.getStyleClass().add("scroll_pane_bottom");
    }
    private void setScrollPane(final ScrollPane sp,final Node node){
        sp.setContent(node);
        sp.setPannable(true);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.getStyleClass().add("scrollpane");
    }
    public Pane getLayout_Container(){ return hBox; }

    public void start() { pane.setVisible(true); }

    public ScrollPane getScrollPane(){
        return pane;
    }

    public void remove(final Button btn) {

        for (int i = 0; i < hBox.getChildren().size(); i++){

            if (hBox.getChildren().get(i).getStyle().equals(btn.getStyle())){
                hBox.getChildren().remove(i);
            }
        }

    }

    public void show_iconBtn(final Button btn,final String imageName){

        System.out.println("bottom: " + imageName);
        btn.setStyle("-fx-background-image:url(file:///C://Users//jamil//Documents//MenuBar//Icons//"+imageName+");");
        hBox.getChildren().add(btn);
    }
}
