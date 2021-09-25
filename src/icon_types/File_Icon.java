package icon_types;


import java.io.File;
import java.io.IOException;

public class File_Icon extends Icon {

    private String imageName;
    private final String target;

    public File_Icon(final String _target) {
        super();
        // CSS. Converter doesn't manage to handle File.Seperator
        target = _target;

    }
    public void identify_file_type(final String type){
        if(type.contains(".xlsx")){
            imageName = textIcon_path + File.separator + "excel.png";
        }
        if(type.contains(".docx")){
            imageName = textIcon_path + File.separator + "word.png";
        }
        if(type.contains(".pdf")){
            imageName = textIcon_path + File.separator + "pdf.png";
        }
        if(type.contains(".txt")){
            imageName = textIcon_path + File.separator + "txt.png";
        }

        getButton().setId(delete_extension(target));
    }


    @Override
    public String getImageName() {
        return imageName.substring(43);
    }

    @Override
    public String getTarget() {
        return  target;
    }

    @Override
    public String getTargetPath() {
        return image_path + File.separator + target;
    }

    @Override
    public String getImagePath() {
        return imageName;
    }

    @Override
    public void start() {

        try {
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + getTargetPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
