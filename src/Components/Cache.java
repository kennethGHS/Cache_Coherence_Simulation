package Components;

import GUI_admin.BitsetToHex;

import java.util.BitSet;

public class Cache {
    CacheSet set1;
    CacheSet set2;

    public Cache(int memorySize, int X, int Y) {
        this.set1 = new CacheSet(X, Y, memorySize);
        Y += 125;
        this.set2 = new CacheSet(X, Y, memorySize);
    }

    public BitSet readValue(String dir, int block) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            System.out.print("Correct");
            return set1.readBlock(block);
        } else {
            return set2.readBlock(block);
        }
    }

    public void setValue(String dir, int block, BitSet values) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            set1.modifyDataBlock(block, values);
        } else {
            set2.modifyDataBlock(block, values);
        }
    }

    public int getBlockHit(String dir) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            binaryRep = binaryRep.substring(0, binaryRep.length() - 1);
            return set1.isHit(binaryRep);
        } else {
            binaryRep = binaryRep.substring(0, binaryRep.length() - 1);
            return set2.isHit(binaryRep);
        }
    }

    public void changeBlockState(String dir, int block, int state) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            this.set1.updateStateBlock(block, state);
        } else {
            this.set2.updateStateBlock(block, state);
        }
    }

    public int getMatchingBlockState(String dir, int block) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            return this.set1.getsStateBlock(block);
        } else {
            return this.set2.getsStateBlock(block);
        }
    }

    public void readRequestBus(String dir, int block) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            this.set1.readRequestBus(block);
        } else {
            this.set2.readRequestBus(block);
        }
    }

    public void increaseValuesUsed() {
        this.set1.increaseClockBlock();
        this.set2.increaseClockBlock();
    }

    public int getBlockToWrite(String dir) {
        String binaryRep = "";
        for (char a : dir.toCharArray()) {
            binaryRep = binaryRep + BitsetToHex.getHexBit(a);
        }
        if (binaryRep.charAt(binaryRep.length() - 1) == '0') {
            CacheSet cacheSet = this.set1;
            if (cacheSet.block1.i == 1) {
                return 0;
            }
            if (cacheSet.block2.i == 1) {
                return 1;
            }
            if (cacheSet.block1.counterSinceAccessed > cacheSet.block2.counterSinceAccessed) {
                return 0;
            } else {
                return 1;
            }
        } else {
            CacheSet cacheSet = this.set2;
            if (cacheSet.block1.i == 1) {
                return 0;
            }
            if (cacheSet.block2.i == 1) {
                return 1;
            }
            if (cacheSet.block1.counterSinceAccessed > cacheSet.block2.counterSinceAccessed) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
