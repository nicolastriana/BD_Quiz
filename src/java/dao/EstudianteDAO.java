/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.Estudiante;

/**
 *
 * @author Labing
 */
public class EstudianteDAO implements IBaseDatos<Estudiante> {

    @Override
    public List<Estudiante> findAll() {
        List<Estudiante> estudiantes = null;
	    String query = "SELECT * FROM Estudiante";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int codEstudiante =0;
	    String nombre = null;
            String beca = null;
	
	    while (rs.next()){
	    	if(estudiantes == null){
	    		estudiantes = new ArrayList<Estudiante>();
	    	}
	      
	        Estudiante registro= new Estudiante();
	        codEstudiante = rs.getInt("cod_Estudiante");
	        registro.setCodEstudiante(codEstudiante);
	        
	        nombre = rs.getString("nom_Estudiante");
	        registro.setNom_Estudiante(nombre);
                
                beca = rs.getString("beca");
	        registro.setBeca(beca);
                
	        estudiantes.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Estudiantes");
			e.printStackTrace();
		}
	    
	    return estudiantes;
    }

    @Override
    public boolean insert(Estudiante t) {
         boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Estudiante (cod_Estudiante, nom_Estudiante, beca)" + " values (?,?,?)";
            PreparedStatement preparedStmt=null;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt (1, t.getCodEstudiante());
                preparedStmt.setString (2, t.getNom_Estudiante());
                preparedStmt.setString (3, t.getBeca());
		result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
            }
	return result;
    }

    @Override
    public boolean update(Estudiante t) {
        boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Estudiante set nom_Estudiante = ?, beca = ? where cod_Estudiante = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom_Estudiante());
		    preparedStmt.setString(2, t.getBeca());
                    preparedStmt.setInt   (3, t.getCodEstudiante());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Estudiante t) {
        boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Estudiante where cod_Estudiante = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getCodEstudiante());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
