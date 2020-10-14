package Components;

import GUI_admin.ComponentFactory;

public class FetchInstruction {
    public FetchInstruction(int X, int Y){
        ComponentFactory.createRectangle(300,50,X,Y,6);
    }
}
