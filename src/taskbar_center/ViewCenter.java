package taskbar_center;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;


public class ViewCenter {

    private final ScrollPane scrollbar_top;
    private final ScrollPane scrollbar_bottom;
    private final VBox vBox;
    private boolean checkVisibility = false;

    public ViewCenter(){
        scrollbar_top = new ScrollPane(new FlowPane());
        scrollbar_bottom = new ScrollPane(new FlowPane());
        vBox = new VBox(scrollbar_top, scrollbar_bottom);
    }
    public void setStyle(){

        ((FlowPane)scrollbar_top.getContent()).getChildren().add(set_mini_vbox(false,"delete icon","delete.png",new Button()));

        vBox.getStyleClass().add("view_center_vbox");
        setScrollPane(scrollbar_top);
        setScrollPane(scrollbar_bottom);

        setFlowPane((FlowPane)scrollbar_top.getContent());
        setFlowPane((FlowPane)scrollbar_bottom.getContent());

    }
    private void setScrollPane(final ScrollPane sp){
        sp.setPannable(true);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.getStyleClass().add("scrollpane");
    }

    private void setCircle(final Circle circle){
        circle.getStyleClass().add("circle");
        circle.setStyle("-fx-background-image:url(file:///C://Users//jamil//Documents//MenuBar//delete_icon.png);");

        final TranslateTransition translate = new TranslateTransition();
        translate.setNode(circle);
        translate.setByX(18);
        translate.setByY(-125);
        translate.play();
    }

    private void setFlowPane(final FlowPane pane){
        pane.setHgap(50);
        pane.setVgap(50);
        pane.getStyleClass().add("flowpane");
        pane.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        pane.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * 2);
    }

    private VBox set_mini_vbox(final Boolean deleteIcon,final String textLabel,final String imageBtn,final Button btn){
        final Circle circle = new Circle(10,10,10, Color.TRANSPARENT);
        if (deleteIcon)
            circle.setFill(new ImagePattern(new Image("file:///C:/Users/jamil/Documents/MenuBar/delete_icon.png")));

        setCircle(circle);
        circle.setVisible(false);

        final VBox mini_vBox = new VBox(btn, new Label(textLabel),circle);
        mini_vBox.getStyleClass().add("mini_vBox_center");
        String style = "-fx-background-image:url(file:///C://Users//jamil//Documents//MenuBar//"+ imageBtn + ");";
        mini_vBox.getChildren().get(0).setStyle(style);
        return mini_vBox;
    }

    public void show_iconBtn(final Button btn,final String imageName){

        final FlowPane pane = (FlowPane) scrollbar_top.getContent();
        pane.getChildren().add(set_mini_vbox(true,imageName.substring(0, imageName.length()-4),"Icons//"+imageName,btn));
    }
    public void show_iconBtn_view_bottom(final Button btn, final String imageName,final String targetName){


        final FlowPane flowPane = (FlowPane) scrollbar_bottom.getContent();
        flowPane.getChildren().add(set_mini_vbox(true, targetName, "TextIcons//" + imageName, btn));
    }

    public void remove(final VBox box) {
        final boolean hasFound = deleteContent(scrollbar_bottom, box);
        if(!hasFound) {
            deleteContent(scrollbar_top, box);
        }
    }

    private boolean deleteContent(final ScrollPane pane,final VBox box){
        final FlowPane paneBottom = (FlowPane)pane.getContent();
        for(int i = 0; i < paneBottom.getChildren().size(); i++){
            if(paneBottom.getChildren().get(i).equals(box)){
                paneBottom.getChildren().remove(i);
                return true;
            }
        }
        return false;
    }
    public void display_mini_deleteBtn(){
        setVisibility_delete_icon((FlowPane)scrollbar_top.getContent());
        setVisibility_delete_icon((FlowPane)scrollbar_bottom.getContent());
    }

    private void setVisibility_delete_icon(final FlowPane pane){
        for (int i = 0; i < pane.getChildren().size(); i++) {

            final VBox mini_vBox = (VBox) pane.getChildren().get(i);
            if (mini_vBox.getChildren().get(2).isVisible()) {

                mini_vBox.getChildren().get(2).setVisible(false);
                checkVisibility = false;
            } else {
                mini_vBox.getChildren().get(2).setVisible(true);
                checkVisibility = true;
            }
        }
    }
    public boolean deleteBtnVisibility(){
        return checkVisibility;
    }


    public Pane getLayout_Container() {
        return vBox;
    }

    public void close(){vBox.setVisible(false);
    }

    public void start(){
        vBox.setVisible(true);
    }
}

