/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Mateusz
 */
public class Tuple<X, Y, Z> {

    public final X item1;
    public final Y item2;
    public final Z item3;

    public Tuple(X item1, Y item2, Z item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
}
