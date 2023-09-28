/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmosplanificacion;

import algoritmosplanificacion.logic.FIFO;
import algoritmosplanificacion.logic.PriorityExp;
import algoritmosplanificacion.logic.SJF;
import algoritmosplanificacion.logic.SRTF;

import java.util.*;

/**
 * @author Isa
 */
public class Processes {
    private Methods method;
    private ArrayList<Process> processes ;
    private Queue<Process> processesFifo;
    private Queue<Process> processesSFJ = new PriorityQueue<>();

    public Processes(int choice,ArrayList<Process> processes) {
        processes.sort(Comparator.comparing(Process::getArrivalTime));
        this.processes = processes;
        this.processesFifo = new LinkedList<>(processes);
        switch (choice) {
            case 1 -> method = new FIFO(processesFifo);
            case 2 -> method = new SJF();
            case 3 -> method = new SRTF();
            case 4 -> method = new PriorityExp();
        }
    }

    public Queue<Process> getProcessesFifo() {
        return processesFifo;
    }
    public void setMethod(Methods method) {
        this.method = method;
    }
    public void setProcessesFifo(Queue<Process> processesFifo) {
        this.processesFifo = processesFifo;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public Queue<Process> getProcessesSFJ() {
        return processesSFJ;
    }

    public void setProcessesSFJ(Queue<Process> processesSFJ) {
        this.processesSFJ = processesSFJ;
    }

    @Override
    public String toString() {
        return "\nProcesses{" + "processesFifo=" + processesFifo + ",\n processesSFJ=" + processesSFJ + '}';
    }

}
