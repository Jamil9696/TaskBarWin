package taskbar_bottom;

import create_icons.Icon_template;
import interfaces.Icons;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class ModelBottom {

    private final List<Icons> iconList;

    public ModelBottom() {
        iconList = new ArrayList<>();
    }

    public boolean add_new_icon(final String imageName,final String target ){

         final Icon_template c = Icon_template.identifyComponents(imageName,target);
         if(c.check_template_type(Icon_template.FileIcon)) return false;
         iconList.add(c.createIcon(imageName,target));

         iconList.get(iconList.size()-1).getButton().setId(String.valueOf(iconList.size()-1));
         return true;

    }

    public void set_Btn_Hover_effect(final Button btn,final double scale1, final double yCoordinate) {
        final Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(25),
                new KeyValue(btn.scaleXProperty(), scale1),
                new KeyValue(btn.scaleYProperty(), scale1),
                new KeyValue(btn.translateYProperty(), yCoordinate)
        ));
        timeline.play();
    }

    public void set_btn_rotate_effect(final Button btn) {
        final RotateTransition rotation = new RotateTransition();
        rotation.setNode(btn);
        rotation.setByAngle(Math.random() * 7);
        rotation.play();
    }

    public void remove(final Button btn){

        for(int i = 0; i < iconList.size();i++){
            if(iconList.get(i).getButton().getStyle().equals(btn.getStyle())){
                iconList.remove(i);
                break;
            }
        }

        for(int i = 0; i <  iconList.size();i++){
            iconList.get(i).getButton().setId(String.valueOf(i));
        }
    }

    public void set_HBox_spacing_effect(final HBox hBox, final double spacing) {
        final Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(100),
                new KeyValue(hBox.spacingProperty(), spacing, Interpolator.EASE_OUT)
        ));
        timeline.play();
    }
    public List<Icons> getList(){
        return iconList;
    }
}