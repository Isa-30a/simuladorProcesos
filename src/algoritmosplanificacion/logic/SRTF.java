package algoritmosplanificacion.logic;

import algoritmosplanificacion.Methods;
import algoritmosplanificacion.Process;
import algoritmosplanificacion.Processes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SRTF implements Methods {
    private ArrayList<Process> processes;
    private int actualTime = 0;
    private Queue<Process> arrivedProcess = new LinkedList<>();

    private Process actualProcess;

    public SRTF(ArrayList<Process> processes) {
        this.processes = processes;
        this.actualProcess = processes.get(0);
    }

    @Override
    public void planificationMethod() {
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process lastProcess = null;
        while (!auxProcesses.isEmpty()) {

            addArrivedProcess(processes);
            if(arrivedProcess.isEmpty()){
                actualTime++;
                continue;
            }
            actualProcess = lessRemainingTime(arrivedProcess);
            if( lastProcess!= null && lastProcess!=actualProcess && lastProcess.getCpuRemainingTime()>0 ){
                lastProcess.setStartTime(actualTime);
                lastProcess.setWaitTime(lastIndex(lastProcess.getStartTime()) - lastProcess.getArrivalTime());
            }
            actualProcess.setCpuRemainingTime(actualProcess.getCpuRemainingTime() - 1);

            if(actualProcess.getCpuRemainingTime() == 0){
                actualProcess.setEndTime(actualTime+1);
                processes.set(processes.indexOf(actualProcess), actualProcess);
                auxProcesses.remove(actualProcess);
                arrivedProcess.remove(actualProcess);
                actualProcess = null;
            }
            actualTime++;
            lastProcess=actualProcess;
        }
        System.out.println(processes + "\n" + "Tiempos de espera promedio: " + averageWaitTime());
    }

    private void addArrivedProcess(ArrayList<Process> auxProcesses) {
        for(Process p : auxProcesses){
            if(p.getArrivalTime() == actualTime){
                arrivedProcess.add(p);
            }
        }
    }

    private Process lessRemainingTime(Queue<Process> auxProcesses) {
        Process lessRemainingTime = auxProcesses.peek();
        for (Process process : auxProcesses) {
            if (process.getCpuRemainingTime() < lessRemainingTime.getCpuRemainingTime()) {
                lessRemainingTime = process;
            }
        }
        if(lessRemainingTime.getCpuRemainingTime() == lessRemainingTime.getCpuTime()){
            lessRemainingTime.setStartTime(actualTime);
            lessRemainingTime.setWaitTime(lastIndex(lessRemainingTime.getStartTime()) - lessRemainingTime.getArrivalTime());
        }
        return lessRemainingTime;
    }



    private int lastIndex(ArrayList arr) {
        if (arr.size() > 0)
            return (int) arr.get(arr.size() - 1);
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
