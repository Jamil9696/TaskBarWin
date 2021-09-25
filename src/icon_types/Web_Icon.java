package icon_types;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Web_Icon extends Icon {

    private final String imageName;
    private final String url;


    public Web_Icon(final String _imageName,final String _url) {
        super();

        url = _url;
        imageName = _imageName;

    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public String getTarget() {
        return url;
    }

    @Override
    public String getTargetPath() {
        return url;
    }

    @Override
    public String getImagePath() {
        return image_path + File.separator + imageName;
    }

    @Override
    public void start() {
        try {
            Desktop.getDesktop().browse(new URI( "www." + url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
