package Threads;

import Components.*;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CPUThread implements Runnable {
    public static ArrayList<CPU> cpuList = new ArrayList<>();
    CPU cpuToExecute;
    CyclicBarrier barrier;
    Memory memory;
    public int mode;

    public CPUThread(CPU cpu, CyclicBarrier barrier, Memory memory) {
        this.cpuToExecute = cpu;
        cpuList.add(cpu);
        this.barrier = barrier;
        this.memory = memory;
    }

    @Override
    public void run() {
        if (mode==0){
            executeNextInst();
        }else if (mode==1){
            executeActualInst();
        }else if (mode==2){
            cpuToExecute.cyclesToAvailable-=1;
        }
    }

    public void readyNextCycle() {
        executeNextInst();
    }
    //NOTA wl Whit cuando el estado es Exclussive pasarlo a mod y no crear mensaje
    //
    public void executeActualInst(){
        BusRequestHandler handler = Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber);
        System.out.println("Ejecutando instruccion actual:" + handler.signalNameString );
        if (handler.signalNameString!=null){
            if (handler.signalNameString == "WMISS"){
                cpuToExecute.cyclesToAvailable+=3;
                procWMISSActual(handler);
            }
            if (handler.signalNameString=="WHIT"){
                procWHITActual(handler);
            }
            if (handler.signalNameString=="RHIT"){
                procRHITActual(handler);

            }
            if (handler.signalNameString=="RMISS"){
                procRMISSActual(handler);

            }
        }
    }
//    public void updateStateBlock(int block, int mode) {
//        CacheBlock toModify;
//        if (block == 0) {
//            toModify = this.block1;
//        } else {
//            toModify = this.block2;
//        }
//        switch (mode) {
//            case 0:
//                toModify.setToModified();
//            case 1:
//                toModify.setToExclusive();
//            case 2:
//                toModify.setShared();
//            case 3:
//                toModify.invalidate();
//            case 4:
//                toModify.setOwned();
//        }
//    }
    private void procRMISSActual(BusRequestHandler handler) {
    BitSet memoryRead = memory.readInformation(handler.dirHexSignalString); //revisar esto es lectura de memoria pero hay que buscar en otra cache
    int block =cpuToExecute.cache.getBlockToWrite(handler.dirHexSignalString);
    int state = cpuToExecute.cache.getMatchingBlockState(handler.dirHexSignalString,block);
    boolean bool = Bus.isSharedCacheBlock(cpuToExecute.thisCPUNumber,handler.dirHexSignalString);
    System.out.println("Miss error con shared :" +bool );
    if(bool){
        Cache cacheToRead = Bus.isInOtherCache(cpuToExecute.thisCPUNumber,handler.dirHexSignalString);
        int blockToRead = cacheToRead.getBlockHit(handler.dirHexSignalString);
        memoryRead = cacheToRead.readValue(handler.dirHexSignalString, blockToRead);
        cpuToExecute.cache.setValue(handler.dirHexSignalString, block,memoryRead);
        cpuToExecute.cache.changeBlockState(handler.dirHexSignalString, block,2);
        int stateCacheRead = cacheToRead.getMatchingBlockState(handler.dirHexSignalString, blockToRead);
        //M 0 O 1 E 2 S 3 I 4
        if (stateCacheRead == 0){
            cacheToRead.changeBlockState(handler.dirHexSignalString, blockToRead,4);
        }
        else if (stateCacheRead == 2){
            cacheToRead.changeBlockState(handler.dirHexSignalString,blockToRead,2);
        }

    }
    else {
        System.out.println("Deberia estar aqui:");
        cpuToExecute.cyclesToAvailable+=3;
        cpuToExecute.cache.setValue(handler.dirHexSignalString, block,memoryRead);
        cpuToExecute.cache.changeBlockState(handler.dirHexSignalString, block,1);
    }
    //Recordar que hay que revisar si es compartido, si es compartido o se encuentra en otro cache hay que revisarlo
        //M 0 O 1 E 2 S 3 I 4
//    if (state==)

    }

    private void procRHITActual(BusRequestHandler handler) {
    return;
    }

    private void procWHITActual(BusRequestHandler handler) {
        if (handler.invalidated){
            return;
        }
        else {
            BitSet bitSet = createRandomBitset(16);
            int block = cpuToExecute.cache.getBlockHit(handler.dirHexSignalString);
            cpuToExecute.cache.setValue(handler.dirHexSignalString,block,bitSet);
            handlerStateWHITActual(handler,block);
        }
    }

    private void handlerStateWHITActual(BusRequestHandler handler, int block) {
        cpuToExecute.cache.changeBlockState(handler.dirHexSignalString,block,0);
    }

    private void procWMISSActual(BusRequestHandler handler) {
        System.out.println("PROCESANDO WMISS XD");
        if (handler.invalidated){
            return;
        }
        else{
            BitSet bitSet = createRandomBitset(16);
            memory.modifyInformation(handler.dirHexSignalString,bitSet);
            int block =cpuToExecute.cache.getBlockToWrite(handler.dirHexSignalString);
            cpuToExecute.cache.setValue(handler.dirHexSignalString,block,bitSet);
            handlerStateWMISSActual(handler,block);
        }
        

    }

    private void handlerStateWMISSActual(BusRequestHandler handler,int block) {
        cpuToExecute.cache.changeBlockState(handler.dirHexSignalString,block,0);

//        if(Bus.isSharedCacheBlock(cpuToExecute.thisCPUNumber,handler.dirHexSignalString)){
//            cpuToExecute.cache.changeBlockState(handler.dirHexSignalString,block,2);
//        }
//        else {
//            cpuToExecute.cache.changeBlockState(handler.dirHexSignalString,block,1);
//        }
    }

    public void executeNextInst() {
        String[] instruction = cpuToExecute.fetchInstruction.getInstruction();
        cpuToExecute.setInstruction(instruction[0], instruction[1]);
        System.out.println(instruction[0] + instruction[1]+ ":" + cpuToExecute.thisCPUNumber);

        if (instruction[0] == "WRITE") {
            writeHandler(instruction);
        }
        else if (instruction[0] == "READ") {
            readHandler(instruction);
        } else {
            Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber).resetHandler();
        }
    }

    public void writeHandler(String[] instruction) {
        int hit = cpuToExecute.cache.getBlockHit(instruction[1]);
        System.out.println("Este es el num hit " + hit);
        if (hit < 0) {
            System.out.println("WMISS EN ESYTO");
            BusRequestHandler handler = Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber);
            handler.setMessage("WMISS", instruction[1]);
            System.out.println("VERIFICAR");
            System.out.println(Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber).signalNameString);
//            cpuToExecute.cyclesToAvailable=2;
        } else {
            BusRequestHandler handler = Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber);
            handler.setMessage("WHIT", instruction[1]);
        }
    }

    public void readHandler(String[] instruction) {
        int hit = cpuToExecute.cache.getBlockHit(instruction[1]);
        if (hit < 0) {
            BusRequestHandler handler = Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber);
            handler.setMessage("RMISS", instruction[1]);
        } else {
            BusRequestHandler handler = Bus.busRequestHandlerList.get(cpuToExecute.thisCPUNumber);
            handler.setMessage("RHIT", instruction[1]);
        }
    }

    public BitSet createRandomBitset(int memorySize) {
        BitSet bitSet = new BitSet(memorySize);
        Random random = new Random();
        for (int i = 0; i < memorySize; i += 1) {
            bitSet.set(i, random.nextBoolean());
        }
        return bitSet;
    }
}
