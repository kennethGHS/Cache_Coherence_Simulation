package Threads;

import Components.Bus;
import Components.CPU;
import Components.Memory;

import java.util.concurrent.CyclicBarrier;

public class RunTest implements Runnable {
    @Override
    public void run() {

        Memory memory = new Memory(400,900,16,16);
        CPU cpu = new CPU(200, 200, 16);
        CPU cpu2 = new CPU(600, 200, 16);
        CPU cpu3 = new CPU(1000, 200, 16);
        CPU cpu4 = new CPU(1400, 200, 16);
        Bus.initBusHandlers(4,200,700);
        CPUThread cpuThread = new CPUThread(cpu,new CyclicBarrier(2),memory);
        CPUThread cpuThread2= new CPUThread(cpu2,new CyclicBarrier(2),memory);
        CPUThread cpuThread3 = new CPUThread(cpu3,new CyclicBarrier(2),memory);
        CPUThread cpuThread4 = new CPUThread(cpu4,new CyclicBarrier(2),memory);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0;i<30;i+=1){
        cpuThread.readyNextCycle();
        cpuThread2.readyNextCycle();
        cpuThread3.readyNextCycle();
        cpuThread4.readyNextCycle();
        Bus.executeInvalidations();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            cpuThread.executeActualInst();
            cpuThread2.executeActualInst();
            cpuThread3.executeActualInst();
            cpuThread4.executeActualInst();
        }
    }
}
