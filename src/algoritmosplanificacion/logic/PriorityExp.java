package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.Queue;


public class PriorityExp implements Methods {
    private ArrayList<Process> processes;
    private int actualTime = 0;
    private Queue<Process> arrivedProcess = new java.util.LinkedList<>();
    private Process actualProcess;

    public PriorityExp(ArrayList<Process> processes) {
        this.processes = processes;

    }

    public void planificationMethod() {
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process lastProcess = null;

        while (!auxProcesses.isEmpty()) {
            addArrivedProcess(processes);
            if (arrivedProcess.isEmpty()) {
                actualTime++;
                continue;
            }//TODO: make a solution
            actualProcess = lessPriority(arrivedProcess);
            if (lastProcess != null && lastProcess != actualProcess && lastProcess.getCpuRemainingTime() > 0) {


                lastProcess.setEndTime(actualTime);
                lastProcess.setStartTime(lastIndex(lastProcess.getEndTime(),-1)-lastIndex(lastProcess.getEndTime(),-2));
                lastProcess.setWaitTime(lastIndex(lastProcess.getStartTime(),-1) - lastProcess.getArrivalTime());
            }
            actualProcess.setCpuRemainingTime(actualProcess.getCpuRemainingTime() - 1);

            if (actualProcess.getCpuRemainingTime() == 0) {
                actualProcess.setEndTime(actualTime + 1);
                processes.set(processes.indexOf(actualProcess), actualProcess);
                auxProcesses.remove(actualProcess);
                arrivedProcess.remove(actualProcess);
                actualProcess = null;
            }
            actualTime++;
            lastProcess = actualProcess;
        }
        System.out.println(processes + "\n" + "Tiempos de espera promedio: " + averageWaitTime());
    }

    private void addArrivedProcess(ArrayList<Process> auxProcesses) {
        for (Process p : auxProcesses) {
            if (p.getArrivalTime() == actualTime) {
                arrivedProcess.add(p);
            }
        }
    }

    private Process lessPriority(Queue<Process> auxProcesses) {
        Process lessPriority = auxProcesses.peek();
        for (Process process : auxProcesses) {
            if (process.getPriority() < lessPriority.getPriority()) {
                lessPriority = process;
            }
        }
        if (lessPriority.getCpuRemainingTime() == lessPriority.getCpuTime()) {
            lessPriority.setStartTime(actualTime);
            lessPriority.setWaitTime(lastIndex(lessPriority.getStartTime(),-1) - lessPriority.getArrivalTime());
        }
        return lessPriority;
    }


    private int lastIndex(ArrayList arr, int index) {
        if (arr.size() > 0 && index < arr.size()-1)
            return (int) arr.get(arr.size() - index);

        return 0;
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
