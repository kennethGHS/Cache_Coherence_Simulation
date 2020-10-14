package GUI_admin;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class ComponentFactory {
    public static Rectangle createRectangle(int dimensionX, int dimensionY, int X, int Y, int color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(dimensionY);
        rectangle.setWidth(dimensionX);
        rectangle.setX(X);
        rectangle.setY(Y);
        rectangle.setArcWidth(10.0);
        rectangle.setArcHeight(10.0);
        switch (color) {
            case 0:
                rectangle.setFill(Color.LIGHTBLUE);
                break;
            case 1:
                rectangle.setFill(Color.LIGHTGRAY);
                break;

            case 2:
                rectangle.setFill(Color.LIGHTGREEN);
                break;

            case 3:
                rectangle.setFill(Color.LIGHTCORAL);
                break;

            case 4:
                rectangle.setFill(Color.LIGHTSALMON);
                break;

            case 5:
                rectangle.setFill(Color.LIGHTSEAGREEN);
                break;

            case 6:
                rectangle.setFill(Color.LIGHTCYAN);
                break;

            case 7:
                rectangle.setFill(Color.OLIVE);
                break;

        }
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStroke(Color.GRAY);
        GUIUpdater.addComponent(rectangle);
        return rectangle;
    }

    public static Text createText(int fontSize,int X,int Y, String text) {
        Text returnText = new Text(text);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, fontSize);
        returnText.setFont(font);
        returnText.setX(X);
        returnText.setY(Y);
        GUIUpdater.addComponent(returnText);
        return returnText;
    }
    public static Line createLine(int begX,int begY, int finX,int finY,int color,int thickness){
        Line line = new Line();
        line.setStrokeWidth(thickness);
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);
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
        GUIUpdater.addComponent(line);

        return line;
    }

}
