/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
public class NodoLiga {
    NodoGeneral direccion;
    NodoLiga ant, sig;
    public NodoLiga(NodoGeneral direccion){
        this.direccion = direccion;
        ant = null;
        sig = null;
    }
}
