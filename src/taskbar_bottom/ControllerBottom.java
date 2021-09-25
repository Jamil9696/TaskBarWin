package taskbar_bottom;

import create_icons.Icon_template;
import interfaces.Controllers;
import interfaces.Icons;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class ControllerBottom implements Controllers {

    private final ViewBottom view_bottom;
    private final ModelBottom model;

    public ControllerBottom(final ViewBottom _viewBottom) {
        model = new ModelBottom();
        view_bottom = _viewBottom;
    }

    public void start() {
        view_bottom.setStyle();
        // View bottom: HBox
        final HBox hBox = (HBox) view_bottom.getLayout_Container();
        hBox.setOnMouseMoved(e1 -> hover_effect_hBox(hBox, 20));
        hBox.setOnMouseExited(e1 -> hover_effect_hBox(hBox, 0));
        hBox.setOnDragOver(this::dragOver);

    }
    private void connect_view_Buttons(final int listIndex) {
        final Button btn = model.getList().get(listIndex).getButton();
        btn.setOnMouseEntered(e1 -> hover_effect(btn, 2,0));
        btn.setOnMouseExited(e1 -> hover_effect(btn, 1,0));
        model.set_btn_rotate_effect(btn);
    }

    private void add_grow_effects(final int listIndex) {
        final Button btn = model.getList().get(listIndex).getButton();
        btn.setOnMouseMoved(e1 -> grow(btn, 1.60, 1.35, 10));
        btn.setOnMouseExited(e1 -> grow(btn, 1.0, 1.0, -0.5));
    }

    private void grow(final Button btn, final double scale1, final double scale2, final double yCoordinate) {

        final int id = Integer.parseInt(btn.getId());
        model.set_Btn_Hover_effect(btn, scale1, -(yCoordinate) * 2);

        if (id + 1 < model.getList().size())
            model.set_Btn_Hover_effect(model.getList().get(id + 1).getButton(), scale2, -(yCoordinate));
        if (id - 1 >= 0)
            model.set_Btn_Hover_effect(model.getList().get(id - 1).getButton(), scale2, -(yCoordinate));

    }
    private void dragOver(final DragEvent event) {
        if (event.getGestureSource() != this && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void display_icon(final Icons icon) {
        // Apps and web Icons -> bottomView and centerView
        view_bottom.show_iconBtn(icon.getButton(),icon.getImageName());
        connect_view_Buttons(model.getList().size()-1);
        add_grow_effects( model.getList().size()-1);

    }

    // Hover-Effect
    private void hover_effect_hBox(final HBox hBox,final double scale) {
        model.set_HBox_spacing_effect(hBox, scale);
    }

    private void hover_effect(final Button btn,final double scale1,final double yCoordinate) {

        model.set_Btn_Hover_effect(btn, scale1, yCoordinate);
    }

    @Override
    public void remove(final Button btn){

        if(btn.getId() == null) {
            view_bottom.remove(btn);
            model.remove(btn);
        }
    }
    @Override
    public void add_new_icon(final String imageName,final String target){

       final boolean isAdded = model.add_new_icon(imageName, target);

        if(isAdded) {
            Icons icon = model.getList().get(model.getList().size() - 1);
            display_icon(icon);
        }

    }

    @Override
    public Pane getLayoutContainer() {
        return view_bottom.getLayout_Container();
    }

    @Override
    public ScrollPane getScrollPane() {
        return view_bottom.getScrollPane();
    }
}