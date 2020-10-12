package Components;

import GUI_admin.BitsetToHex;

import java.util.BitSet;

public class CacheSet {
    CacheBlock block1;
    CacheBlock block2;

    public CacheSet(int X, int Y, int memorySize) {
        this.block1 = new CacheBlock(memorySize, X, Y);
        X += 55;
        this.block2 = new CacheBlock(memorySize, X, Y);
    }

    public int isHit(String tag) {
        if (block1.valueValid(tag)) {
            return 0;
        } else if (block2.valueValid(tag)) {
            return 1;
        }
        return -1;
    }

    public void modifyBlock(int block, BitSet values, String tag) {
        if (block == 0) {
            this.block1.setCounterSinceAccessed(0);
            this.block1.setTag(tag);
            this.block1.setMemoryBits(values);
        } else {
            this.block2.setCounterSinceAccessed(0);
            this.block2.setTag(tag);
            this.block2.setMemoryBits(values);
        }
    }

    public void increaseBlockCounters(int block) {
        switch (block) {
            case 0:
                block1.increaseCounterSinceAccessed();
                return;
            case 1:
                block2.increaseCounterSinceAccessed();
                return;
            case 3:
                block2.increaseCounterSinceAccessed();
                block1.increaseCounterSinceAccessed();
        }
    }

    public int getLeastUsedBlock() {
        if (this.block1.getCounterSinceAccessed() > this.block2.getCounterSinceAccessed()) {
            return 0;
        } else return 1;
    }

    public void modifyDataBlock(int block, BitSet values) {
        if (block == 0) {
            this.block1.setCounterSinceAccessed(0);
            this.block1.setMemoryBits(values);
        } else {
            this.block2.setCounterSinceAccessed(0);
            this.block2.setMemoryBits(values);
        }
    }

    public void readRequestBus(int block) {
        if (block == 0) {
            this.block1.readRequestBus();
        } else {
            this.block2.readRequestBus();
        }
    }

    public BitSet readValueBlock(int block) {
        if (block == 0) {
            return this.block1.getMemoryBits();

        } else {
            return this.block2.getMemoryBits();
        }
    }

    /**
     * Actualiza el estado de un bloque, 0 para modificado, 1 para exclusivo, 2 para shared y 3 para invalidar
     * @param block cual bloque
     * @param mode cual modo,
     */
    public void updateStateBlock(int block, int mode) {
        CacheBlock toModify;
        if (block == 0) {
            toModify = this.block1;
        } else {
            toModify = this.block2;
        }
        switch (mode) {
            case 0:
                toModify.setToModified();
            case 1:
                toModify.setToExclusive();
            case 2:
                toModify.setShared();
            case 3:
                toModify.invalidate();
        }
    }
    public BitSet readBlock(int block){
        if (block==0){
            return block1.getMemoryBits();
        }
        else {
            return block2.getMemoryBits();
        }
    }
    public int getsStateBlock(int block){
        if (block==0){
            return block1.getState();
        }
        else {
            return block2.getState();
        }
    }
}
