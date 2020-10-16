package Components;

import GUI_admin.BitsetToHex;
import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.text.Text;

import java.util.BitSet;
import java.util.Random;

public class FetchInstruction {
    public Text instruction;
    public String[] instructionStrings;
    int memorySizes;
    public FetchInstruction(int X, int Y, int memorySizes) {
        this.memorySizes = memorySizes;
        ComponentFactory.createRectangle(300, 50, X, Y, 6);
        this.instruction = ComponentFactory.createText(12, X + 20, Y + 25, "Instruction: NONE");
        newInstruction(16);
    }

    public String[] getInstruction() {
        String[] nameAndDirection = this.instructionStrings;
        newInstruction(this.memorySizes);
        return nameAndDirection;

    }

    public void newInstruction(int size) {
        String[] nameAndDirection = {null, null};
        Random random = new Random();
        double gaussianValue = random.nextGaussian()%1;
        System.out.println(gaussianValue);
        int randomDir = random.nextInt() % (size*4);
        if (!(gaussianValue > -0.8 && gaussianValue < 0.8)) {
            BitSet bits = BitSet.valueOf(new long[]{randomDir});
            nameAndDirection[1] = BitsetToHex.bitToHex(bits, this.memorySizes);
            if ((gaussianValue < -0.8))
                nameAndDirection[0] = "READ";
            else nameAndDirection[0] = "WRITE";
            GUIUpdater.updateText(this.instruction, "Instruction: " + nameAndDirection[0] + " " + nameAndDirection[1]);
            this.instructionStrings = nameAndDirection;
            return;
        } else {
            nameAndDirection[0] = "ESCA";
            GUIUpdater.updateText(this.instruction, "Instruction:ESCA");
            this.instructionStrings = nameAndDirection;
            return;
        }
    }
}
