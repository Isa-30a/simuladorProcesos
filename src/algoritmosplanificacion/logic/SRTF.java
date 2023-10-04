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
        int tiempoActual = 0;
        int totalProcesos = processes.size();
        int procesoActual = 0;
        

        while (procesoActual < totalProcesos || !readyQueue.isEmpty()) {
            // Agregar procesos a la cola de listos si han llegado.
            while (procesoActual < totalProcesos && processes.get(procesoActual).getArrivalTime() <= tiempoActual) {
                readyQueue.add(processes.get(procesoActual));
                procesoActual++;
            }

            if (!readyQueue.isEmpty()) {
                Process executionProcess = readyQueue.poll();
                if (executionProcess.getStartTime().isEmpty()) {
                    executionProcess.setStartTime(tiempoActual); // Añadir el tiempo de inicio
                }
                //looooooooop
                System.out.println("Tiempo " + tiempoActual + ": Ejecutando " + executionProcess.getName());

                executionProcess.setCpuRemainingTime(executionProcess.getCpuRemainingTime() - 1);

                if (executionProcess.getCpuRemainingTime() == 0) {
                    executionProcess.setEndTime(tiempoActual + 1); // Añadir el tiempo de finalización
                    int ultimoInicio = (int) executionProcess.getStartTime().get(executionProcess.getStartTime().size() - 1);
                    executionProcess.setWaitTime(ultimoInicio - executionProcess.getArrivalTime()); // Calcular el tiempo de espera
                } else {
                    readyQueue.add(executionProcess);
                }
            } else {
                System.out.println("Tiempo " + tiempoActual + ": CPU en espera");
            }

            tiempoActual++;
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
