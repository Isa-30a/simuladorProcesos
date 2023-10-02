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
    public void addProcess(Process process) {

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
        average = average();
        System.out.println(processes + "\n" + average);
    }

    @Override
    public void calculateStartTime(Processes processes) {
    }


    @Override
    public void calculateEndTime(Process process) {
        process.setEndTime(Integer.parseInt((String) process.getStartTime().get(0)) + process.getCpuTime());
    }

    public int average() {
        int espera = 0;
        for (int i = 0; !processes.isEmpty(); i++) {
            processes.get(i).totalWaitTime();
        }
        return espera;
    }

    @Override
    public void calculateWaitTime(Process process) {
        process.setWaitTime(Integer.parseInt((String) process.getStartTime().get(0)) - process.getArrivalTime());
    }
}
