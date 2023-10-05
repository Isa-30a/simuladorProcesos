package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SRTF implements Methods {
    private ArrayList<Process> processes;
    private int actualTime = 0;

    private Process actualProcess;

    public SRTF(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public void addProcess(Process process) {

    }

    @Override
    public void planificationMethod() {
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
        Process newProcess;
        int i = 0;
        do {
            newProcess = processes.get(i);
            if (newProcess.getArrivalTime() == 0) {
                newProcess.setStartTime(actualTime);
                actualProcess = newProcess;
            }
            if (processes.get(i).getArrivalTime() <= actualTime) {

                if (newProcess.getCpuRemainingTime() < actualProcess.getCpuRemainingTime()) {
                    actualProcess.setEndTime(actualTime);

                    actualProcess.setWaitTime(lastIndex(actualProcess.getStartTime()) - actualProcess.getArrivalTime());
                    actualProcess = newProcess;
                    newProcess.setStartTime(actualTime);
                } else {
                    priorityQueue.add(newProcess);
                }
            }
            i++;
        } while ( i < processes.size());

        System.out.println(processes + "\n" + "Average Wait Time: " + averageWaitTime());
    }

    private int lastIndex(ArrayList arr) {
        return (int) arr.get(arr.size() - 1);
    }

    private int changeExProcess(ArrayList<Process> auxProcesses, Process newProcess, int i) {
        newProcess = auxProcesses.get(i);
        if (newProcess.getArrivalTime() <= actualTime) {
            newProcess.setStartTime(actualTime);
            calculateEndTime(newProcess);
            calculateWaitTime(newProcess);
            processes.set(processes.indexOf(newProcess), newProcess);
            auxProcesses.remove(i);
            i = -1;
        }
        return i;
    }


    private int remainingTime(Process process) {
        return process.getCpuTime() - actualTime;
    }
    public float averageWaitTime() {
        float averageTime = 0;
        for (Process process : processes) {
            averageTime += process.totalWaitTime();
        }
        return  averageTime / processes.size();
    }
    @Override
    public void calculateStartTime(Processes processes) {

    }

    @Override
    public void calculateEndTime(Process process) {

    }

    @Override
    public void calculateWaitTime(Process process) {

    }

}
