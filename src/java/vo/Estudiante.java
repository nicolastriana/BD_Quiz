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
public class Estudiante {
    
    private int codEstudiante;
    private String nom_Estudiante;
    private String beca;

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getNom_Estudiante() {
        return nom_Estudiante;
    }

    public void setNom_Estudiante(String nom_Estudiante) {
        this.nom_Estudiante = nom_Estudiante;
    }

    public String getBeca() {
        return beca;
    }

    public void setBeca(String beca) {
        this.beca = beca;
    }
    
}
