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
    private ArrayList<Process> processes;

    public Processes(int choice, ArrayList<Process> processes) {
        processes.sort(Comparator.comparing(Process::getArrivalTime));
        switch (choice) {
            case 1 -> method = new FIFO(processes);
            case 2 -> method = new SJF(processes);
            case 3 -> method = new SRTF(processes);
            case 4 -> method = new PriorityExp(processes);
        }
        this.processes = processes;

    }



    public void printProcesses() {
        method.planificationMethod();
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public String toString() {
        return "\nProcesses{" + "\n " + processes +  '}';
    }

}
