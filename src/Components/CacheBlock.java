package Components;

import GUI_admin.BitsetToHex;
import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.BitSet;

public class CacheBlock {
    private int m;
    private int o;
    private int e;
    private int s;
    public int i;
    private BitSet memoryBits;
    private Text memoryValuesHex;
    private Text textM;
    private Text textO;
    private Text textE;
    private Text textS;
    private Text textI;
    private Text textTag;
    private Rectangle blockContainer;
    private int memorySize;
    private String tag;
    public int counterSinceAccessed;
    public int cyclesUsed = 0;

    public CacheBlock(int memorySize, int placeX, int placeY) {
        this.counterSinceAccessed = 0;
        this.tag = "0";
        this.i = 1;
        this.memoryBits = new BitSet(memorySize);
        this.memorySize = memorySize;
        this.blockContainer = ComponentFactory.createRectangle(50, 120, placeX, placeY, 2);
        blockContainer.toFront();
        this.memoryValuesHex = ComponentFactory.createText(10, placeX + 12, placeY + 15,
                BitsetToHex.bitToHex(this.memoryBits, this.memorySize));
        this.textM = ComponentFactory.createText(10, placeX + 12, placeY + 30,
                "M:" + m);
        this.textO = ComponentFactory.createText(10, placeX + 12, placeY + 45,
                "O:" + o);
        this.textE = ComponentFactory.createText(10, placeX + 12, placeY + 60,
                "E:" + e);
        this.textS = ComponentFactory.createText(10, placeX + 12, placeY + 75,
                "S:" + s);
        this.textI = ComponentFactory.createText(10, placeX + 12, placeY + 90,
                "I:" + i);
        this.textTag = ComponentFactory.createText(10, placeX + 12, placeY + 105,
                "Tag:" + this.textTag);
    }

    public int getCounterSinceAccessed() {
        return counterSinceAccessed;
    }

    public void setCounterSinceAccessed(int counterSinceAccessed) {
        this.counterSinceAccessed = counterSinceAccessed;
    }

    public void increaseCounterSinceAccessed() {
        this.counterSinceAccessed += 1;
    }

    public void setTag(String tag) {
        this.tag = tag;
        GUIUpdater.updateText(this.textTag,"Tag: " + tag);
    }

    public BitSet getMemoryBits() {
        return (BitSet) this.memoryBits.clone();
    }

    public synchronized void setMemoryBits(BitSet memoryBits) {
        this.memoryBits = memoryBits;
        GUIUpdater.updateText(this.memoryValuesHex, BitsetToHex.bitToHex(this.memoryBits, this.memorySize));
    }

    public synchronized void setM(int m) {
        this.m = m;
        GUIUpdater.updateText(this.textM, "M:" + this.m);
    }

    public synchronized void setO(int o) {
        this.o = o;
        GUIUpdater.updateText(this.textO, "O:" + this.o);

    }

    public synchronized void setE(int e) {
        this.e = e;
        GUIUpdater.updateText(this.textE, "E:" + this.e);

    }

    public synchronized void setS(int s) {
        this.s = s;
        GUIUpdater.updateText(this.textS, "S:" + this.s);

    }

    public synchronized void setI(int i) {
        this.i = i;
        GUIUpdater.updateText(this.textI, "I:" + this.i);
    }

    public synchronized int readRequestBus() {
        if (this.m == 1) {
            this.setM(0);
            this.setO(1);
            return 1;
        }
        if (this.e == 1) {
            this.setE(0);
            this.setO(1);
            return 1;
        }
        return -1;
    }

    public synchronized void invalidate() {
        this.setM(0);
        this.setO(0);
        this.setE(0);
        this.setI(1);
        this.setS(0);
    }

    public synchronized boolean valueValid(String tag) {
        if (this.i == 1) {
            return false;
        } else if (this.tag.equals(tag)) {
            return true;
        }
        return false;
    }

    public synchronized void setToExclusive() {
        this.setM(0);
        this.setO(0);
        this.setE(1);
        this.setI(0);
        this.setS(0);
    }

    public synchronized void setToModified() {
        this.setM(1);
        this.setO(0);
        this.setE(0);
        this.setI(0);
        this.setS(0);
    }

    public synchronized void setShared() {
        this.setM(0);
        this.setO(0);
        this.setE(0);
        this.setI(0);
        this.setS(1);
    }

    public synchronized void setOwned() {
        System.out.println("Setting to owned");
        this.setM(0);
        this.setO(1);
        this.setE(0);
        this.setI(0);
        this.setS(0);
    }

    public synchronized int getState() {
        if (this.m == 1) {
            return 0;
        }
        if (this.o == 1) {
            return 1;
        }
        if (this.e == 1) {
            return 2;
        }
        if (this.s == 1) {
            return 3;
        }
        if (this.i == 1) {
            return 4;
        }
        return -1;
    }

}
