package Components;

import GUI_admin.BitsetToHex;
import GUI_admin.ComponentFactory;

import java.util.ArrayList;
import java.util.BitSet;

public class Memory {
    private ArrayList<MemoryBlock> memoryBlocks;
    public int memorySize;
    public Memory(int X, int Y, int Size,int blocks){
        this.memorySize = Size;
        this.memoryBlocks = new ArrayList<>();
        ComponentFactory.createRectangle(60*blocks+40,70,X-20,Y-10,0);
        while (blocks!=0){
            this.memoryBlocks.add( new MemoryBlock(Size,X,Y));
            X+=60;
            blocks-=1;
        }
    }
    public BitSet readInformation(String dirHex){
        int dir = BitsetToHex.getHexNum(dirHex.charAt(0));
        if (dir>=memoryBlocks.size()){
            return null;
        }

     return memoryBlocks.get(dir).getMemoryBits();
    }

    public int modifyInformation(String dirHex,BitSet information){
        int dir = BitsetToHex.getHexNum(dirHex.charAt(0));
        if (dir>=memoryBlocks.size()){
            return -1;
        }
        memoryBlocks.get(dir).setMemoryBits(information);
        return 1;
    }
}
