package Components;

import GUI_admin.ComponentFactory;
import GUI_admin.GUIUpdater;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CPU {
    public boolean available;
    public String instructionName;
    public boolean currentInsIsMemory;
    public String   currentDirectionInstruction;
    public int cyclesToAvailable;
    public Rectangle rectangle;
    public Cache cache;
    public FetchInstruction fetchInstruction;
    public Text instructionText;
    public static int cpuNumber = 0;
    public int thisCPUNumber;
    public Text instructionMemoryText;
    public  CPU(int X,int Y,int cacheMemorySize){
        fetchInstruction = new FetchInstruction(X,Y-50,4);
        rectangle = ComponentFactory.createRectangle(300,300,X,Y,1);
        this.cache = new Cache(cacheMemorySize,X+170,Y+20);
        Bus.cacheList.add(this.cache);
        this.instructionText = ComponentFactory.createText(12,X+10,Y+30,"Instruction: None");
        this.instructionMemoryText = ComponentFactory.createText(12,X+10,Y+60,"Dir: None");
        this.available = true;
        instructionName = "";
        currentDirectionInstruction = "";
        cyclesToAvailable=0;
        this.thisCPUNumber = CPU.cpuNumber;
        CPU.cpuNumber+=1;

    }
    public static synchronized CPU createCPU(int X, int Y, int cacheMemorySize){
        CPU toReturn = new CPU(X,Y,cacheMemorySize);
        cpuNumber+=1;
        return toReturn;

    }

    public void setInstruction(String name,String memory){
        if (memory==null){
            this.instructionName = name;
            this.currentInsIsMemory = false;
            this.cyclesToAvailable = 0;
            this.available = true;
            this.currentDirectionInstruction = "";
            GUIUpdater.updateText(this.instructionText, "Instruction: "+name);

        }
        else {
            this.instructionName = name;
            this.currentInsIsMemory = true;
            this.cyclesToAvailable = 3;
            this.available = false;
            this.currentDirectionInstruction = memory;
            GUIUpdater.updateText(this.instructionText, "Instruction: "+name);
            GUIUpdater.updateText(this.instructionMemoryText, "Dir: "+currentDirectionInstruction);

        }
    }
    public void fetchInstruction(){
        if (this.available){

        }
        else {
            cyclesToAvailable-=1;
        }
    }
    public void increaseCyclesToAvailable(int cyclesToAvailable){
        this.cyclesToAvailable+=cyclesToAvailable;
    }
}
