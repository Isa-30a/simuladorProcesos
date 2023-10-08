package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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
        Queue<Process> waitingProcess = new PriorityQueue<>();
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        int pos = 0;
        Process newProcess;

        while (!auxProcesses.isEmpty()) {
            newProcess = auxProcesses.get(pos);
            if (newProcess.getArrivalTime() <= actualTime) {
                waitingProcess.add(newProcess);
                auxProcesses.remove(pos);
                pos = 0;
            } else {
                pos++;
            }
            if (waitingProcess.peek() != null) {
                if (isRemainingLess(newProcess)) {
                    switchProcess(newProcess);
                } else {
                    if (waitingProcess.peek() == (newProcess)) {
                        switchProcess(newProcess);
                    }
                }
            }
            actualTime++;
            if (actualProcess != null) {
                if (actualProcess.getCpuRemainingTime() - 1 > 0) {
                    actualProcess.setCpuRemainingTime(actualProcess.getCpuRemainingTime() - 1);
                } else {
                    actualProcess.setCpuRemainingTime(0);
                    actualProcess.setEndTime(actualTime);
                    processes.set(processes.indexOf(actualProcess), actualProcess);
                    auxProcesses.remove(actualProcess);
                    actualProcess = null;
                }
            }
        }
        System.out.println(processes + "\n" + "Tiempos de espera promedio: " + averageWaitTime());
    }

    private void switchProcess(Process newProcess) {
        if (actualProcess != null) {
            actualProcess.setEndTime(actualTime);

        }
        actualProcess = newProcess;
        actualProcess.setStartTime(actualTime);
        actualProcess.setWaitTime(lastIndex(actualProcess.getStartTime()) - actualProcess.getArrivalTime());
    }

    private boolean isRemainingLess(Process newProcess) {
        if (actualProcess != null) {
            return remainingTime(newProcess) < remainingTime(actualProcess);
        }
        return true;
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
