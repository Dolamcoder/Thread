/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BT1Thread;

/**
 *
 * @author Admin
 */
public class bt1 {
    public static void main(String[] args) {
       Print print=new Print(100);
       Thread chan=new Thread(new SoChan(print));
       chan.start();
       Thread le=new Thread(new SoLe(print));
       le.start();
    }
}
