package GUI_admin;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.awt.*;

public class BasicComponents {
    public static Parent window;
    public static Scene mainParent;
    public static Group root;
    public static void initWindow( Parent window){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        root = new Group();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(root,width,height);
        BasicComponents.window = window;
        BasicComponents.mainParent = scene;

    }
}
