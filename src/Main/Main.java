package Main;
import Components.Cache;
import Components.CacheBlock;
import Components.CacheSet;
import Components.MemoryBlock;
import GUI_admin.BasicComponents;
import GUI_admin.BitsetToHex;
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
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        //Setting the properties of the rectangle
        System.out.println(Font.getFamilies());
        BasicComponents.initWindow(root);
        primaryStage.setScene(BasicComponents.mainParent);
//        CacheSet cache= new CacheSet(50,50,16);
        Cache cache = new Cache(16,50,50);
        cache.changeBlockState("01",0,2);
        BitSet bits = new BitSet(16);
        bits.set(0);
        bits.set(15);
        cache.setValue("01",1,bits);
        System.out.print(BitsetToHex.bitToHex(cache.readValue("00",1),16));
//        CacheSet set = new CacheSet(50,300,16);
//        CacheBlock cacheBlock = new CacheBlock(16,400,200);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
