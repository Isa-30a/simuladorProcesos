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
        PriorityQueue<Process> readyQueue = new PriorityQueue<>();
        ArrayList<Process> procesos = new ArrayList<>(processes);
        int tiempoActual = 0, i = 0;
        Process procesoActual = null;

        while (!readyQueue.isEmpty() || i < processes.size()) {
            // Agrega procesos a la cola de listos si han llegado
            for (Process proceso : procesos) {
                if (proceso.getArrivalTime() <= tiempoActual) {
                    readyQueue.add(proceso);
                }
            }
            procesos.removeAll(readyQueue);

            if (!readyQueue.isEmpty()) {
                Process siguienteProceso = readyQueue.poll();
                if (procesoActual == null || siguienteProceso.getCpuRemainingTime() < procesoActual.getCpuRemainingTime()) {
                    if (procesoActual != null) {
                        readyQueue.add(procesoActual);
                    }
                    procesoActual = siguienteProceso;
                    procesoActual.setStartTime(tiempoActual);
                } else {
                    readyQueue.add(siguienteProceso);
                }

                procesoActual.setCpuRemainingTime(procesoActual.getCpuRemainingTime() - 1);

                System.out.println("Tiempo " + tiempoActual + ": Ejecutando " + procesoActual.getName());

                if (procesoActual.getCpuRemainingTime() == 0) {
                    procesoActual.setEndTime(tiempoActual + 1); // Registra el tiempo de finalización
                    procesoActual.setWaitTime((int) procesoActual.getStartTime().get(procesoActual.getStartTime().size() - 1) - procesoActual.getArrivalTime()); // Calcula el tiempo de espera
                    procesoActual = null;
                }
            } else {
                System.out.println("Tiempo " + tiempoActual + ": CPU en espera");
            }

            tiempoActual++;
            i++;
        }

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
