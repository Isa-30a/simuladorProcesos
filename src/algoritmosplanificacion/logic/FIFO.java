package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements Methods {
    private Queue<Process> processes;

    public FIFO(Queue<Process> processes) {
        this.processes = processes;
    }

    @Override
    public void addProcess(Process process) {
        processes.add(process);
    }

    @Override
    public void planificationMethod(Processes processes) {

    }

    @Override
    public void calculateWaitTime(Processes processesType) {
        Queue<Process> processes = processesType.getProcessesFifo();
        for (Process process : processes) {
            process.setWaitTime(process.getStartTime() - process.getArrivalTime());
        }
    }

    @Override
    public void calculateEndTime(Processes processesType) {
        Queue<Process> processes = processesType.getProcessesFifo();
        for (Process process : processes) {
            process.setEndTime(process.getStartTime() + process.getCpuTime());
        }
    }

    @Override
    public void calculateStartTime(Processes processes) {
        Queue<Process> processQueue = new LinkedList<>(processes.getProcesses());
        int exTime = processQueue.element().getStartTime();
        for (Process process : processQueue) {
            process.setStartTime(exTime);
            exTime += process.getCpuTime();
        }
    }
}
