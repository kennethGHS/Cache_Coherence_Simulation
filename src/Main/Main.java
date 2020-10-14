package Main;

import Components.*;
import GUI_admin.BasicComponents;
import GUI_admin.BitsetToHex;
import GUI_admin.ComponentFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.BitSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        //Setting the properties of the rectangle
        System.out.println(Font.getFamilies());
        BasicComponents.initWindow(root);
        primaryStage.setScene(BasicComponents.mainParent);

//        CacheSet set = new CacheSet(50,300,16);
//        CacheBlock cacheBlock = new CacheBlock(16,400,200);
        primaryStage.show();
        Memory memory = new Memory(400,900,16,16);
        CPU cpu = new CPU(200, 200, 16);
        CPU cpu2 = new CPU(600, 200, 16);
        CPU cpu3 = new CPU(1000, 200, 16);
        CPU cpu4 = new CPU(1400, 200, 16);
        Bus.initBusHandlers(4,200,700);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
