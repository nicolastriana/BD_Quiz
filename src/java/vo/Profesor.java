/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author Labing
 */
public class Profesor {
    
    private int codProfesor;
    private String nom_Profesor;
    private String apellido;

    public int getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }

    public String getNom_Profesor() {
        return nom_Profesor;
    }

    public void setNom_Profesor(String nom_Profesor) {
        this.nom_Profesor = nom_Profesor;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
