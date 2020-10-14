package Components;

import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BusRequestHandler {

    public Rectangle rectangle;
    public Text signalName;
    public Text dirHexSignal;
    public String dirHexSignalString;
    public int procNumber;
    public boolean onHold;
    public boolean invalidated;
    public boolean onUse;

    public BusRequestHandler(int X, int Y, int procNumber) {
        this.onHold = false;
        this.dirHexSignalString = "";
        this.procNumber = procNumber;
        this.invalidated = false;
        this.rectangle = ComponentFactory.createRectangle(300, 50, X, Y, 4);
        this.signalName = ComponentFactory.createText(12, X + 10, Y + 20, "Signal: None");
        this.dirHexSignal = ComponentFactory.createText(12, X + 10, Y + 40, "Signal Dir: None");
    }

    public void setMessage(String messageName, String dirHexSignalString) {
        this.dirHexSignalString = dirHexSignalString;
        GUIUpdater.updateText(this.dirHexSignal, "Signal Dir:" + dirHexSignalString);
        GUIUpdater.updateText(this.signalName, "Signal: " + dirHexSignalString);
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
}
