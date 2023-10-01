/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmosplanificacion;

/**
 *
 * @author Isa
 */
public class Process {

    private String name;
    private int priority;
    final private int arrivalTime;
    final private int cpuTime;
    private int startTime;
    private int endTime;
    private int waitTime;

    public Process(int arrivalTime, int cpuTime) {
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.startTime = 0;
        this.endTime = 0;
        this.waitTime = 0;
    }

    public Process(String name, int arrivalTime, int cpuTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.startTime = 0;
        this.endTime = 0;
        this.waitTime = 0;
    }

    public Process(String name, int priority, int arrivalTime, int cpuTime) {
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.startTime = 0;
        this.endTime = 0;
        this.waitTime = 0;
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

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String toString() {
        return "Process{" + "nombre=" + name + ", tiempoLlegada=" + arrivalTime + ", tiempoCpu=" + cpuTime + ", tiempoComienzo=" + startTime + ", tiempoFin=" + endTime + ", tiempoEspera=" + waitTime + "}\n";
    }

}
