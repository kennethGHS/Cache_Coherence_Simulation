package Threads;

import Components.Bus;
import Components.CPU;
import Components.Memory;
import GUI_admin.BasicComponents;
import GUI_admin.GUIUpdater;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CyclicBarrier;

public class MasterThread implements Runnable {
    CPU cpu;
    CPU cpu1;
    CPU cpu2;
    CPU cpu3;
    Memory memory;
    boolean automatic;
    boolean execute;
    boolean pause;
    Button button;
    Button button2;
    Button button3;

    public MasterThread() {
        this.automatic = false;
        execute = false;
        this.pause = true;
        memory = new Memory(400, 900, 16, 16);
        cpu = new CPU(200, 200, 16);
        cpu1 = new CPU(600, 200, 16);
        cpu2 = new CPU(1000, 200, 16);
        cpu3 = new CPU(1400, 200, 16);
        Bus.initBusHandlers(4, 200, 700);
        button = new Button("Pause");
        button.setOnAction(value -> {
            if (pause) {

                automatic = true;
                pause = false;
                GUIUpdater.updateButtonText("Automatic", button);
            } else if (automatic) {
                automatic = false;
                GUIUpdater.updateButtonText("Manual", button);

            } else if (!automatic && !pause) {
                GUIUpdater.updateButtonText("Pause", button);
                pause = true;
            }
        });
        button2 = new Button("Edit Instruction");
        button2.setLayoutX(10);
        button2.setLayoutY(20);
        button.setLayoutX(100);
        button.setLayoutY(20);
        button2.setOnAction(value -> {
            callWindowInfo();
        });
        button3 = new Button("Clock");
        button3.setLayoutX(300);
        button3.setLayoutY(20);
        button3.setOnAction(value -> {
            this.execute = true;
        });
        BasicComponents.root.getChildren().addAll(button, button2, button3);

    }

    @Override
    public void run() {
        CPUThread cpuThread = new CPUThread(cpu, new CyclicBarrier(2), memory);
        CPUThread cpuThread2 = new CPUThread(cpu1, new CyclicBarrier(2), memory);
        CPUThread cpuThread3 = new CPUThread(cpu2, new CyclicBarrier(2), memory);
        CPUThread cpuThread4 = new CPUThread(cpu3, new CyclicBarrier(2), memory);
        while (true) {
            System.out.println("Cycle");
            if (automatic) {
                execute(cpuThread, cpuThread2, cpuThread3, cpuThread4);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (!pause && execute) {
                    execute = false;
                    execute(cpuThread, cpuThread2, cpuThread3, cpuThread4);

                }
            }
        }
    }

    public void execute(CPUThread cpuThread, CPUThread cpuThread2, CPUThread cpuThread3, CPUThread cpuThread4) {

        Thread thread1 = new Thread(cpuThread);
        Thread thread2 = new Thread(cpuThread2);
        Thread thread3 = new Thread(cpuThread3);
        Thread thread4 = new Thread(cpuThread4);
        if (cpuThread.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread.mode = 2;
        } else {
            cpuThread.mode = 0;
        }
        if (cpuThread2.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread2.mode = 2;
        } else {
            cpuThread2.mode = 0;
        }
        if (cpuThread3.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread3.mode = 2;
        } else {
            cpuThread3.mode = 0;
        }
        if (cpuThread4.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread4.mode = 2;
        } else {
            cpuThread4.mode = 0;
        }
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            Bus.fixCacheStates();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (cpuThread.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread.mode = 2;
        } else {
            cpuThread.mode = 1;
        }
        if (cpuThread2.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread2.mode = 2;
        } else {
            cpuThread2.mode = 1;
        }
        if (cpuThread3.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread3.mode = 2;
        } else {
            cpuThread3.mode = 1;
        }
        if (cpuThread4.cpuToExecute.cyclesToAvailable != 0) {
            cpuThread4.mode = 2;
        } else {
            cpuThread4.mode = 1;
        }
        thread1 = new Thread(cpuThread);
        thread2 = new Thread(cpuThread2);
        thread3 = new Thread(cpuThread3);
        thread4 = new Thread(cpuThread4);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void callWindowInfo() {
        if (automatic) {
            return;
        } else {
            List<String> choices = new ArrayList<>();
            choices.add("0");
            choices.add("1");
            choices.add("2");
            choices.add("3");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("0", choices);
            dialog.setTitle("Choice proc");
            dialog.setHeaderText("Choice Proc");
            dialog.setContentText("Select Processor");

// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                int numProc = Integer.parseInt(result.get());
                List<String> choices2 = new ArrayList<>();
                choices2.add("ESCA");
                choices2.add("WRITE");
                choices2.add("READ");
                ChoiceDialog<String> dialog2 = new ChoiceDialog<>("ESCA", choices2);
                dialog2.setTitle("Choice");
                dialog2.setHeaderText("Choice instruction");
                dialog2.setContentText("Select instruction");
                result = dialog2.showAndWait();
                if (result.isPresent()) {
                    String instruction = result.get();
                    if (instruction.equals("ESCA")) {
                        String newInstruction[] = {instruction, null};
                        switch (numProc) {
                            case 0:
                                cpu.fetchInstruction.setInstructionStrings(newInstruction);
                                return;

                            case 1:
                                cpu1.fetchInstruction.setInstructionStrings(newInstruction);
                                return;

                            case 2:
                                cpu2.fetchInstruction.setInstructionStrings(newInstruction);
                                return;
                            case 3:
                                cpu3.fetchInstruction.setInstructionStrings(newInstruction);
                                return;
                            default:
                                return;
                        }
                    }
                    List<String> choices3 = new ArrayList<>();
                    choices3.add("0");
                    choices3.add("1");
                    choices3.add("2");
                    choices3.add("3");
                    choices3.add("4");
                    choices3.add("5");
                    choices3.add("6");
                    choices3.add("7");
                    choices3.add("8");
                    choices3.add("9");
                    choices3.add("A");
                    choices3.add("B");
                    choices3.add("C");
                    choices3.add("D");
                    choices3.add("F");
                    ChoiceDialog<String> dialog3 = new ChoiceDialog<>("0", choices3);
                    dialog3.setTitle("Choice");
                    dialog3.setHeaderText("Choice Dir");
                    dialog3.setContentText("Select Dir");
                    result = dialog3.showAndWait();
                    if (result.isPresent()) {
                        String newInstruction[] = {instruction, result.get()};
                        switch (numProc) {
                            case 0:
                                cpu.fetchInstruction.setInstructionStrings(newInstruction);
                                return;

                            case 1:
                                cpu1.fetchInstruction.setInstructionStrings(newInstruction);
                                return;

                            case 2:
                                cpu2.fetchInstruction.setInstructionStrings(newInstruction);
                                return;
                            case 3:
                                cpu3.fetchInstruction.setInstructionStrings(newInstruction);
                                return;
                            default:
                                return;
                        }
                    } else {
                        return;
                    }

                } else {
                    return;
                }
            } else {
                return;
            }

// The Java 8 way to get the response value (with lambda expression).
        }
    }
}
