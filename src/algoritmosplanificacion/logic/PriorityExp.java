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

    @Override
    public void planificationMethod() {
        ArrayList<Process> auxProcesses = new ArrayList<>(processes);
        Process lastProcess = null;
        while (!auxProcesses.isEmpty()) {
            addArrivedProcess(processes);
            if (arrivedProcess.isEmpty()) {
                actualTime++;
                continue;
            }
            actualProcess = lessPriority(arrivedProcess);
            for (Process p : arrivedProcess) {
                if (p != actualProcess) {
                    if (p.getWaitTime().isEmpty()) {
                        p.setWaitTime(1);
                    } else {
                        p.setWaitTime((int) p.getWaitTime().get(p.getWaitTime().size() - 1) + 1);
                    }
                }
            }

            if (lastProcess != null && lastProcess != actualProcess) {
                if (lastProcess.getCpuRemainingTime() > 0) {
                    lastProcess.setEndTime(actualTime);
                }
                actualProcess.addStartTime(actualTime);
            }
            actualProcess.setCpuRemainingTime(actualProcess.getCpuRemainingTime() - 1);

            if (actualProcess.getCpuRemainingTime() == 0) {
                actualProcess.setEndTime(actualTime + 1);
                actualProcess.setStartTime(distinct(actualProcess.getStartTime()));
                processes.set(processes.indexOf(actualProcess), actualProcess);
                auxProcesses.remove(actualProcess);
                arrivedProcess.remove(actualProcess);
            }
            actualTime++;
            lastProcess = actualProcess;
        }
        System.out.println(processes + "\n" + "Tiempos de espera promedio: " + averageWaitTime());
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

    private void addArrivedProcess(ArrayList<Process> auxProcesses) {
        for (Process p : auxProcesses) {
            if (p.getArrivalTime() == actualTime && p.getCpuRemainingTime() > 0) {
                arrivedProcess.add(p);
            }
        }
    }

    private Process lessPriority(Queue<Process> auxProcesses) {
        Process lessPriorityProcess = auxProcesses.stream().min((p1, p2) -> {
            int compare = Integer.compare(p1.getPriority(), p2.getPriority());
            if (compare == 0) {
                return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
            }
            return compare;
        }).get();
        if (lessPriorityProcess.getCpuRemainingTime() == lessPriorityProcess.getCpuTime()) {
            lessPriorityProcess.addStartTime(actualTime);
            lessPriorityProcess.setWaitTime(lastIndex(lessPriorityProcess.getStartTime()) - lessPriorityProcess.getArrivalTime());
        }
        return lessPriorityProcess;
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

    private ArrayList<Integer> distinct(ArrayList<Integer> list) {
        ArrayList<Integer> distinctList = new ArrayList<>();
        for (Integer integer : list) {
            if (!distinctList.contains(integer)) {
                distinctList.add(integer);
            }
        }
        return distinctList;
    }
}
