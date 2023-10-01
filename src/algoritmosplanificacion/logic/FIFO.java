package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements Methods {
    private ArrayList<Process> processes;
    private int actualTime = 0;

    public FIFO(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public void addProcess(Process process) {

    }

    @Override
    public void planificationMethod() {
        for (int i = 0; processes.isEmpty(); i++) {
            Process newProcess = processes.get(i);
            if (newProcess.getArrivalTime() <= actualTime) {

                actualTime += newProcess.getArrivalTime();
                newProcess.setStartTime(actualTime);
                calculateEndTime(newProcess);
                calculateWaitTime(newProcess);
            }
        }
    }
    @Override
    public void calculateStartTime(Processes processes) {
    }


    @Override
    public void calculateEndTime(Process process) {
        process.setEndTime(process.getStartTime() + process.getCpuTime());
    }

    @Override
    public void calculateWaitTime(Process process) {
        process.setWaitTime(process.getStartTime() - process.getArrivalTime());
    }
}
