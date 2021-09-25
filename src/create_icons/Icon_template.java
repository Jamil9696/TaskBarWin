package create_icons;

import icon_types.Desktop_Icon;
import icon_types.File_Icon;
import icon_types.Web_Icon;
import interfaces.Icons;

public enum Icon_template {


    DesktopIcon{
        boolean match(final String condition) {
            return condition.contains(".lnk");
        }

        public boolean check_template_type(final Icon_template iconType){

            return iconType == DesktopIcon;
        }

        public Icons createIcon(final String imageName,final String target) {

            final Icons deskTopIcon = new Desktop_Icon(imageName,target);
            deskTopIcon.getButton().setOnMouseClicked(e -> deskTopIcon.start());
           return deskTopIcon;

        }
    },


    FileIcon{
        boolean match(final String condition) {
            return condition.contains(".xlsx") ||
                    condition.contains(".pdf") ||
                    condition.contains(".docx") ||
                    condition.contains(".txt");
        }
        public boolean check_template_type(final Icon_template iconType){

            return iconType == FileIcon;
        }

        public Icons createIcon(final String imageName,final String target) {

                final File_Icon fileIcon = new File_Icon(target);
                fileIcon.identify_file_type(target);
                fileIcon.getButton().setOnMouseClicked(e -> fileIcon.start());
                return fileIcon;
        }
    },



     WebIcon{
        boolean match(String condition) {
            return condition.contains(".");
        }

         public boolean check_template_type(final Icon_template iconType){

             return iconType == WebIcon;
         }

        public Icons createIcon(final String imageName, String target) {

            if(target.contains(".png")) target = target.substring(0,target.length()-4);

            final Icons webIcon = new Web_Icon(imageName,target);
            webIcon.getButton().setOnMouseClicked(e -> webIcon.start());
            return webIcon;
        }
    };


    public static Icon_template identifyComponents(final String condition1){
        return identifyComponents(condition1, "");
    }
    public static Icon_template identifyComponents(final String condition1, final String condition2) {
        for (final Icon_template c : values()) {
            if(c.match(condition1) || c.match(condition2)) {
                return c;
            }

        }
        System.out.println("web");
        return Icon_template.WebIcon;
    }

     abstract boolean match(final String condition);
     public abstract Icons createIcon(final String imageName,final String target);
     public abstract boolean check_template_type(final Icon_template iconType);



}
