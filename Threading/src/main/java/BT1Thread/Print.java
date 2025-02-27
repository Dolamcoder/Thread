/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BT1Thread;

/**
 *
 * @author Admin
 */
public class Print {
    private int number=0;
    private  int limit;
    public Print(int limit){
        this.limit=limit;
    }
    public synchronized void printEven(){
        while(number<=limit){
        while(number%2!=0){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Even: "+number);
        number++;
        this.notify();
    }
    }
    public synchronized void printOdd(){
        while(number<=limit){
            while(number%2==0){
                try{
                    this.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Odd: "+number);
            number++;
            this.notify();
        }
    }
}
