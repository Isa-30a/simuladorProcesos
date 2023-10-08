/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmosplanificacion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Isa
 */
public class Process implements Comparable<Process> {

    private String name;
    private int priority;
    final private int arrivalTime;
    private int cpuTime;
    private int cpuRemainingTime;
    private ArrayList<Integer> startTime;
    private ArrayList<Integer> endTime;
    private ArrayList<Integer> waitTime;

    public Process(int arrivalTime, int cpuTime) {
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.startTime = new ArrayList<>();
        this.endTime = new ArrayList<>();
        this.waitTime = new ArrayList<>();
        this.cpuRemainingTime = cpuTime;
    }

    public Process(String name, int arrivalTime, int cpuTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.cpuTime = cpuTime;
        this.startTime = new ArrayList<>();
        this.endTime = new ArrayList<>();
        this.waitTime = new ArrayList<>();
        this.cpuRemainingTime = cpuTime;

    }

    public Process(String name,  int arrivalTime, int cpuTime,int priority) {
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.cpuRemainingTime = cpuTime;
        this.cpuTime = cpuTime;
        this.startTime = new ArrayList<>();
        this.endTime = new ArrayList<>();
        this.waitTime = new ArrayList<>();

    }

    public int totalWaitTime() {
        int averageTime = 0;
        for (int espera : waitTime) {
            averageTime += espera;
        }
        return averageTime ;
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
        this.cpuTime = cpuTime;
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

    public int getCpuRemainingTime() {
        return cpuRemainingTime;
    }

    public void setCpuRemainingTime(int cpuRemainingTime) {
        this.cpuRemainingTime = cpuRemainingTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", arrivalTime=" + arrivalTime +
                ", cpuTime=" + cpuTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", waitTime=" + waitTime +
                '}'+ '\n';
    }

    @Override
    public int compareTo(Process otro) {
        int pancho = Integer.compare(this.cpuRemainingTime, otro.cpuRemainingTime);
        if (pancho == 0) {
            return Integer.compare(this.arrivalTime, otro.arrivalTime);
        }
        return pancho;
    }
}
