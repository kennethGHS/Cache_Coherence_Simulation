package Main;

import Components.*;
import GUI_admin.BasicComponents;
import Threads.CPUThread;
import Threads.MasterThread;
import Threads.RunTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.BitSet;
import java.util.concurrent.CyclicBarrier;

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
        MasterThread masterThread = new MasterThread();
        Thread thread = new Thread(masterThread);
        thread.setDaemon(true);
        thread.start();
        primaryStage.show();
//        Thread thread = new Thread(new RunTest());
//        thread.start();

//        Cache c= new Cache(16,100,100);
//        c.setValue("2",0,new BitSet(16));
//        c.changeBlockState("2",0,0);
//        c.getBlockHit("2");
//        Random rand = new Random();
//        int i = 100;
//        while (i!=0){
//            System.out.println(rand.nextGaussian());
//            i-=1;
//        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
