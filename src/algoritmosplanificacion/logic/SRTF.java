package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.Comparator;

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
        processes.sort(Comparator.comparing(Process::getArrivalTime));
        actualProcess = processes.get(0);
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process newProcess;
        for (int i = 0; !processes.isEmpty(); i++) {
            newProcess = auxProcesses.get(i);
            if (newProcess.getArrivalTime() <= actualTime && remainingTime(newProcess) < remainingTime(actualProcess)) {
                i = changeExProcess(auxProcesses, newProcess, i);
            } else if (newProcess.getArrivalTime() == 0) {
                i = changeExProcess(auxProcesses, newProcess, i);
            }
        }
        System.out.println(processes);
    }

    private int changeExProcess(ArrayList<Process> auxProcesses, Process newProcess, int i) {
        actualProcess.setStartTime(actualTime);
        actualProcess.setEndTime(actualTime);
        int lastindex = actualProcess.getStartTime().size() - 1;
        actualProcess.setWaitTime((int) actualProcess.getStartTime().get(lastindex) - actualProcess.getArrivalTime());
        actualTime += actualProcess.getCpuTime() - remainingTime(actualProcess);
        actualProcess.setCpuTime(remainingTime(actualProcess));
        if (remainingTime(actualProcess) <= 0) {
            auxProcesses.remove(actualProcess);
            i = 0;
        }
        actualProcess = newProcess;
        return i;
    }

    private int remainingTime(Process process) {
        return process.getCpuTime() - actualTime;
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
