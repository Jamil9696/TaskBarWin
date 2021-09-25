package taskbar_center;

import interfaces.Controllers;
import interfaces.Icons;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerCenter implements Controllers {

    private final ModelCenter model;
    private final ViewCenter view_center;

    public ControllerCenter(final ViewCenter _view_center){
        model = new ModelCenter();
        view_center = _view_center;

    }
    public void start(){
        view_center.setStyle();
        view_center.close();

        // View Center: delete bin button
        final ScrollPane scrollPaneTop = (ScrollPane) view_center.getLayout_Container().getChildren().get(0);
        FlowPane flowPane = (FlowPane) scrollPaneTop.getContent();
        VBox miniVbox = (VBox) flowPane.getChildren().get(0);
        miniVbox.getChildren().get(0).setOnMouseClicked(e1 -> display_delete_buttons());

    }

    private void display_btn(final Icons icon){

       final boolean is_file_icon = model.add_new_icon(icon.getTarget());

        if(is_file_icon){
            view_center.show_iconBtn_view_bottom(icon.getButton(), icon.getImageName(), icon.getButton().getId());
            return;
        }

        view_center.show_iconBtn(icon.getButton(), icon.getImageName());
    }

    public void display_delete_buttons() {
        view_center.display_mini_deleteBtn();
    }

    @Override
    public void remove(final Button icon_btn){

        final VBox parentBox = (VBox)icon_btn.getParent();
        final Thread thread = new Thread(()-> model.remove_icon(icon_btn));

        thread.start();
        view_center.remove(parentBox);
    }
    @Override
    public void add_new_icon(final Icons icon){

        model.getList().add(icon);
        display_btn(icon);
    }

    @Override
    public void add_new_icon(final String imageName,final String target) {

    }

    @Override
    public Pane getLayoutContainer() {
        if(view_center.deleteBtnVisibility()){
            display_delete_buttons();
        }

        return view_center.getLayout_Container();
    }

    @Override
    public ScrollPane getScrollPane() {

        return (ScrollPane)view_center.getLayout_Container().getChildren().get(0);
    }
}
