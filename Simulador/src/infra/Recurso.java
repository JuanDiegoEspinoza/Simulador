/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

/**
 *
 * @author felipe
 */
public class Recurso {
    int id;
    String nombre;
    int semaforo;
    int porcentaje_uti;
    
    public Recurso(int id, String nombre){
        this.id= id;
        this.nombre= nombre;
        this.semaforo=1;
        this.porcentaje_uti= 0;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre= nombre;
    }
    public int getSemaforo(){
        return this.semaforo;
    }
    public void setSemaforo(int semaforo){
        if (semaforo==0){
        this.semaforo=semaforo;
    }
        else if(semaforo==1){
            this.semaforo= semaforo;
    }
        
    }
    public int getPorcentaje_uti(){
        return this.porcentaje_uti;
    }
    public void setPorcentaje_uti(int porcetaje_uti){
        this.porcentaje_uti= porcentaje_uti;
    }
    
}
