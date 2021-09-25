package icon_types;

import java.io.File;
import java.io.IOException;

public class Desktop_Icon extends Icon {

    private final String imageName;
    private final String target;

    public Desktop_Icon(final String _imageName, final String _target) {
        super();
        imageName = _imageName;
        target = _target;

    }

    @Override
    public String getImageName() {
        return imageName;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public String getTargetPath() {
        return app_path + File.separator + target;
    }

    @Override
    public String getImagePath() {
        return image_path + File.separator + target;
    }

    @Override
    public void start() {

        System.out.println("Target: " + getTarget());
        try {
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + getTargetPath());
            //Process process = new ProcessBuilder(pathToApplication).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
