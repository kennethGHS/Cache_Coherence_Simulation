package Components;

import GUI_admin.BitsetToHex;
import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

import java.util.BitSet;

public class MemoryBlock {
    private BitSet memoryBits;
    private Text memoryValuesHex;
    private Rectangle blockContainer;
    private int memorySize;

    public MemoryBlock(int memorySize, int placeX, int placeY) {
        this.memoryBits = new BitSet(memorySize);
        this.memorySize = memorySize;
        this.blockContainer = ComponentFactory.createRectangle(50, 50, placeX, placeY, 1);
        blockContainer.toFront();
        this.memoryValuesHex = ComponentFactory.createText(10, placeX + 12, placeY + 28,
                BitsetToHex.bitToHex(this.memoryBits, this.memorySize));

    }

    public BitSet getMemoryBits() {
        return memoryBits;
    }

    public synchronized void setMemoryBits(BitSet memoryBits) {
        this.memoryBits = memoryBits;
        GUIUpdater.updateText(this.memoryValuesHex, BitsetToHex.bitToHex(this.memoryBits, this.memorySize));
    }
}
