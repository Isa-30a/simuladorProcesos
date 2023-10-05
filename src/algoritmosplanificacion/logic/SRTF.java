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
        this.actualProcess = processes.get(0);
    }

    @Override
    public void addProcess(Process process) {

    }

    @Override
    public void planificationMethod() {

        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process newProcess = auxProcesses.get(0);
        while (actualTime != totalExecutionTime() && !auxProcesses.isEmpty()) {
            if (newProcess.getArrivalTime() == 0) {
                actualProcess = newProcess;
                actualProcess.setStartTime(actualTime);
                actualProcess.setWaitTime(lastIndex(actualProcess.getStartTime()) - actualProcess.getArrivalTime());
            } else if (newProcess.getArrivalTime() < actualTime) {
                if (isRemainingLess(newProcess)) {
                    switchProcess(newProcess);
                }
                actualTime++;
                if (actualProcess!=null)
                    if(actualProcess.getCpuRemainingTime()-1 >0){
                        actualProcess.setCpuRemainingTime(actualProcess.getCpuRemainingTime()-1);}
                    else {
                        actualProcess.setCpuRemainingTime(0);
                        processes.set(processes.indexOf(actualProcess), actualProcess);
                        auxProcesses.remove(actualProcess);
                    }
            }
        }

        System.out.println("Tiempos de espera promedio: " + averageWaitTime() + "\n" + processes);
    }

    private void switchProcess(Process newProcess) {
        actualProcess.setEndTime(actualTime);
        actualProcess.setWaitTime(lastIndex(actualProcess.getStartTime()) - actualProcess.getArrivalTime());
        actualProcess = newProcess;
        actualProcess.setStartTime(actualTime);
        actualProcess.setWaitTime(lastIndex(actualProcess.getStartTime()) - actualProcess.getArrivalTime());
    }
    private boolean isRemainingLess(Process newProcess) {
        return remainingTime(newProcess) < remainingTime(actualProcess);
    }

    private int totalExecutionTime() {
        int total = 0;
        for (Process process : processes) {
            total += process.getCpuTime();
        }
        return total;
    }

    private int lastIndex(ArrayList arr) {
        if (arr.size() > 0)
            return (int) arr.get(arr.size() - 1);
        return 0;
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
        return averageTime / processes.size();
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
