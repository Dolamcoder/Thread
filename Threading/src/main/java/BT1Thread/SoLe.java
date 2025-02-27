/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BT1Thread;

/**
 *
 * @author Admin
 */
public class SoLe implements Runnable{
    Print print;
    public SoLe(Print print){
        this.print=print;
    }
    @Override
    public void run() {
     print.printOdd();
    }
    
}
