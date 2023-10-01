/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmosplanificacion;

import algoritmosplanificacion.logic.FIFO;
import algoritmosplanificacion.logic.PriorityExp;
import algoritmosplanificacion.logic.SJF;
import algoritmosplanificacion.logic.SRTF;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Isa
 */
public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        int choice = 8;
        do {
            System.out.print("Please, select a method:" + "\n" +
                    "1 - FIFO" + "\n" +
                    "2 - SJF" + "\n" +
                    "3 - SRTF (expropriative)" + "\n" +
                    "4 - PRIORITY (expropriative)" + "\n");
            choice = reader.nextInt();

        } while (choice > 5 || choice < 0);

        ArrayList<Process> array = new ArrayList<>();
        array.add(new Process("a", 0, 5));
        array.add(new Process("b", 3, 3));
        array.add(new Process("c", 4, 4));
        array.add(new Process("d", 5, 2));
        /*this.processesSFJ.add(new Process("a", 0, 5));
        this.processesSFJ.add(new Process("b", 3, 3));
        this.processesSFJ.add(new Process("c", 4, 4));
        this.processesSFJ.add(new Process("d", 5, 2));*/

        Processes context = new Processes(choice,array);


    }

}
