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
        int currentTime = 0;
        ArrayList<Process> completedProcesses = new ArrayList<>();

        while (!processes.isEmpty()) {

            Process shortestJob = processes.get(0);
            if (shortestJob.getStartTime().isEmpty()) {
                shortestJob.setStartTime(currentTime);
            }

            currentTime++;
            shortestJob.setCpuRemainingTime(shortestJob.getCpuRemainingTime() - 1);

            if (shortestJob.getCpuRemainingTime() == 0) {
                shortestJob.setEndTime(currentTime);
                processes.remove(0);
                completedProcesses.add(shortestJob);
            }

        }
        for (Process p : completedProcesses) {
            p.setWaitTime(lastIndex(p.getStartTime()) - p.getArrivalTime());
        }
        // Calcular tiempos de espera promedio
        int totalWaitingTime = 0;
        for (Process p : completedProcesses) {
            int lastIndex = p.getWaitTime().size() - 1;
            totalWaitingTime += lastIndex(p.getWaitTime());
        }

        double averageWaitingTime = (double) totalWaitingTime / completedProcesses.size();
        System.out.println("Tiempos de espera promedio: " + averageWaitingTime + "\n" + completedProcesses);
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
