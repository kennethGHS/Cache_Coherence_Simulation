package Components;

import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BusRequestHandler {

    public Rectangle rectangle;
    public Text signalName;
    public Text dirHexSignal;
    public String dirHexSignalString;
    public String signalNameString;
    public int procNumber;
    public boolean onHold;
    public boolean invalidated;
    public boolean onUse;
    public boolean toMemory;
    public boolean hit;
    public boolean shared;
    public BusRequestHandler(int X, int Y, int procNumber) {
        this.hit = false;
        this.onHold = false;
        this.shared = false;
        this.dirHexSignalString = "";
        this.procNumber = procNumber;
        this.invalidated = false;
        this.toMemory = false;
        this.rectangle = ComponentFactory.createRectangle(300, 50, X, Y, 4);
        this.signalName = ComponentFactory.createText(12, X + 10, Y + 20, "Signal: None");
        this.dirHexSignal = ComponentFactory.createText(12, X + 10, Y + 40, "Signal Dir: None");
    }
    public void resetHandler(){
        this.hit = false;
        this.shared=false;
        this.onHold = false;
        this.dirHexSignalString = null;
        this.signalNameString = null;
        this.procNumber = procNumber;
        this.invalidated = false;
        this.toMemory = false;
        GUIUpdater.updateText(this.signalName,"Signal: None");
        GUIUpdater.updateText(this.dirHexSignal,"Signal Dir: None");
    }
    public void setMessage(String messageName, String dirHexSignalString) {
        this.dirHexSignalString = dirHexSignalString;
        this.signalNameString = messageName;
        GUIUpdater.updateText(this.dirHexSignal, "Signal Dir:" + dirHexSignalString);
        GUIUpdater.updateText(this.signalName, "Signal: " + messageName);
    }

    public void invalidate() {
        this.invalidated = true;
    }

    public void putOnHold() {
        this.onHold = true;
    }

    public void setOnUse() {
        this.onUse = true;
    }

    public void validate() {
        this.validate();
    }

    public void release() {
        this.onUse = false;
    }
    public void setSignalName(String signalName){

    }
}
