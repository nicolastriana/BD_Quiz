/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Giraldo
 */
public class CreateDataBase {
     public static void run(){
        String esc = "CREATE TABLE Escuela(codEscuela integer, nom_Escuela varchar(40), PRIMARY KEY(codEscuela))";
            Connection connectionesc = null;
                try {
                    connectionesc = Conexion.getConnection();
                    Statement stmt = connectionesc.createStatement();
                    stmt.executeUpdate(esc);
                
                } catch (URISyntaxException ex) {
                    Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String est = "CREATE TABLE Estudiante(codEstudiante integer, nom_Estudiante varchar(40), codEscuela integer, beca varchar(40), PRIMARY KEY(codEstudiante)"
                + "FOREIGN KEY(codEscuela) REFERENCES Escuela(codEscuela))";
            Connection connectionest = null;
                try {
                    connectionest = Conexion.getConnection();
                    Statement stmt = connectionest.createStatement();
                    stmt.executeUpdate(est);

                } catch (URISyntaxException ex) {
                    Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String pro = "CREATE TABLE Profesor(codProfesor integer, nom_Profesor varchar(40), apellido varchar(40), PRIMARY KEY(codProfesor))";
            Connection connectionpro = null;
                try {
                    connectionpro = Conexion.getConnection();
                    Statement stmt = connectionpro.createStatement();
                    stmt.executeUpdate(pro);

                } catch (URISyntaxException ex) {
                    Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String cur = "CREATE TABLE Curso(codCurso integer, nom_Curso varchar(40), codProfesor varchar(40), PRIMARY KEY(codCurso), "
                + "FOREIGN KEY(codProfesor) REFERENCES Profesor(codProfesor))";
            Connection connectioncur = null;
                try {
                    connectioncur = Conexion.getConnection();
                    Statement stmt = connectioncur.createStatement();
                    stmt.executeUpdate(cur);
                
                } catch (URISyntaxException ex) {
                    Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        String reg = "CREATE TABLE Registro(id_registro integer, codEstudiante integer, codCurso integer, semestre integer, nota integer, "
                + "PRIMARY KEY(id_registro), FOREIGN KEY(codEstudiante) REFERENCES Estudiante(codEstudiante), "
                + "FOREIGN KEY(codCurso) REFERENCES Curso(codCurso))";
            Connection connectionreg = null;
                try {
                    connectionreg = Conexion.getConnection();
                    Statement stmt = connectionreg.createStatement();
                    stmt.executeUpdate(reg);

                } catch (URISyntaxException ex) {
                    Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CreateDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
