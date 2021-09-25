package taskbar_right;

import create_icons.Icon_template;
import interfaces.Icons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ModelRight {

    private final List<Icons> iconList = new ArrayList<>();

    public boolean add_new_icon(final String target){

        final Icon_template c = Icon_template.identifyComponents(target);

        // no Desktop Icons and Web Icons
        if(c.check_template_type(Icon_template.DesktopIcon) || c.check_template_type(Icon_template.WebIcon)) return false;


        iconList.add(c.createIcon(target,target));
        return true;
    }

    public void set_Btn_Hover_effect(final Button btn,final double scale1,final double yCoordinate) {
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
        RotateTransition rotation = new RotateTransition();
        rotation.setNode(btn);
        rotation.setByAngle(Math.random() * 7);

        rotation.play();
    }

    public void remove(final Button btn){

         for (int i = 0; i < iconList.size(); i++){
             if(iconList.get(i).getButton().getId().equals(btn.getId())){
                 iconList.remove(i);
                 return;
             }
         }
    }


    public List<Icons> getList(){
        return iconList;
    }

}
