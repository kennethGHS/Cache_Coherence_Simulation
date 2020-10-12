package Main;
import Components.Cache;
import Components.CacheBlock;
import Components.CacheSet;
import Components.MemoryBlock;
import GUI_admin.BasicComponents;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        //Setting the properties of the rectangle
        System.out.println(Font.getFamilies());
        BasicComponents.initWindow(root);
        primaryStage.setScene(BasicComponents.mainParent);
        CacheSet cache= new CacheSet(50,50,16);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
