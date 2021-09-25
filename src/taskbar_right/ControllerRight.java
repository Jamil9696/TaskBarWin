package taskbar_right;

import interfaces.Controllers;
import interfaces.Icons;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ControllerRight implements Controllers {

    private final ViewRight viewright;
    private final ModelRight model;

    public ControllerRight(final ViewRight _viewRight) {
        viewright = _viewRight;
        model = new ModelRight();

    }

    private void display_Icon(final Icons icon) {

        viewright.show_iconBtn(icon.getButton(),icon.getImageName(), icon.getButton().getId());
        connect_view_Buttons(icon.getButton());
    }

    private void connect_view_Buttons(final Button btn) {
        btn.setOnMouseEntered(e1 -> model.set_Btn_Hover_effect(btn, 2, 0));
        btn.setOnMouseExited(e1 -> model.set_Btn_Hover_effect(btn, 1, 0));
        model.set_btn_rotate_effect(btn);
    }


    @Override
    public void add_new_icon(final String imageName,final String target){
        final boolean isAdded = model.add_new_icon(target);
        if(isAdded) {
            final Icons icon = model.getList().get(model.getList().size() - 1);
            display_Icon(icon);
        }
    }
    @Override
    public void remove(final Button btn){

        if(btn.getId() != null) {
            viewright.remove(btn);
            model.remove(btn);
        }
    }
    @Override
    public Pane getLayoutContainer() {
        return (VBox)viewright.getScrollPane().getContent();
    }

    @Override
    public ScrollPane getScrollPane() {
       return viewright.getScrollPane();
    }

    @Override
    public void start() {
        viewright.setStyle();
    }
}