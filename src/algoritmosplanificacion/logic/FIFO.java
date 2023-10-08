package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;

public class FIFO implements Methods {
    private ArrayList<Process> processes;
    private int actualTime = 0;
    private double average;

    public FIFO(ArrayList<Process> processes) {
        this.processes = processes;
    }



    @Override
    public void planificationMethod() {
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process newProcess;
        for (int i = 0; !auxProcesses.isEmpty(); i++) {
            newProcess = auxProcesses.get(i);
            if (newProcess.getArrivalTime() <= actualTime) {
                newProcess.setStartTime(actualTime);
                actualTime += newProcess.getCpuTime();
                calculateEndTime(newProcess);
                calculateWaitTime(newProcess);
                processes.set(processes.indexOf(newProcess), newProcess);
                auxProcesses.remove(i);
                i = -1;
            }
        }

        System.out.println(processes + "\n" + "Average Wait Time: " + averageWaitTime());
    }

    @Override
    public void calculateStartTime(Processes processes) {
    }

    public float averageWaitTime() {
        float averageTime = 0;
        for (Process process : processes) {
            averageTime += process.totalWaitTime();
        }
        return  averageTime / processes.size();
    }
    @Override
    public void calculateEndTime(Process process) {
        process.setEndTime((int)process.getStartTime().get(0) + process.getCpuTime());
    }



    @Override
    public void calculateWaitTime(Process process) {
        process.setWaitTime((int ) process.getStartTime().get(0) - process.getArrivalTime());
    }
}
