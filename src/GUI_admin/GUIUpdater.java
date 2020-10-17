package GUI_admin;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GUIUpdater {
    public static void updateText(Text text,String newText){
        System.out.println(newText);
        Runnable updater = new Runnable() {

            @Override
            public void run() {
                text.setText(newText);
            }
        };
        Platform.runLater(updater);

    }
    public static void updateButtonText(String newText, Button button){
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                button.setText(newText);
            }
        };
        Platform.runLater(updater);
    }
    public static void updateRectangleColor(Rectangle rectangle,int color){
        Runnable updater = new Runnable() {

            @Override
            public void run() {
                switch (color) {
                    case 0:
                        rectangle.setFill(Color.LIGHTBLUE);
                    case 1:
                        rectangle.setFill(Color.LIGHTGRAY);
                    case 2:
                        rectangle.setFill(Color.LIGHTGREEN);
                    case 3:
                        rectangle.setFill(Color.LIGHTCORAL);
                    case 4:
                        rectangle.setFill(Color.LIGHTYELLOW);
                    case 5:
                        rectangle.setFill(Color.LIGHTSEAGREEN);
                    case 6:
                        rectangle.setFill(Color.LIGHTCYAN);
                    case 7:
                        rectangle.setFill(Color.OLIVE);

                }
            }
        };
        Platform.runLater(updater);
    }
    public static void updateLineColor(Line line,int color){
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                switch (color) {
                    case 0:
                        line.setFill(Color.LIGHTBLUE);
                    case 1:
                        line.setFill(Color.LIGHTGRAY);
                    case 2:
                        line.setFill(Color.LIGHTGREEN);
                    case 3:
                        line.setFill(Color.LIGHTCORAL);
                    case 4:
                        line.setFill(Color.LIGHTYELLOW);
                    case 5:
                        line.setFill(Color.LIGHTSEAGREEN);
                    case 6:
                        line.setFill(Color.LIGHTCYAN);
                    case 7:
                        line.setFill(Color.OLIVE);
                }
            }
        };
        Platform.runLater(updater);
    }
    public static void addComponent(Node node){
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                BasicComponents.root.getChildren().add(node);
            }
        };
        Platform.runLater(updater);
    }

}
