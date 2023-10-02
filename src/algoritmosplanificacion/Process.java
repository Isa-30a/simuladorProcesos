/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmosplanificacion;

import java.util.ArrayList;

/**
 * @author Isa
 */
public class Process {

    private String name;
    private int priority;
    final private int arrivalTime;
    private int cpuTime;
    private ArrayList<Integer> startTime;
    private ArrayList<Integer> endTime;
    private ArrayList<Integer> waitTime;

    public Process(int arrivalTime, int cpuTime) {
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;

    }

    public Process(String name, int arrivalTime, int cpuTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;

    }

    public Process(String name, int priority, int arrivalTime, int cpuTime) {
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCpuTime() {
        return cpuTime;
    }
    public void setCpuTime(int cpuTime) {
        this.cpuTime= cpuTime;
    }
    public ArrayList getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime.add(startTime);
    }

    public ArrayList getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime.add(endTime);
    }

    public ArrayList getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime.add(waitTime);
    }

    @Override
    public String toString() {
        return "Process{" + "nombre=" + name + ", tiempoLlegada=" + arrivalTime + ", tiempoCpu=" + cpuTime + ", tiempoComienzo=" + startTime + ", tiempoFin=" + endTime + ", tiempoEspera=" + waitTime + "}\n";
    }

}
