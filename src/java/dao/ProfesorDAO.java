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
import vo.Profesor;

/**
 *
 * @author Labing
 */
public class ProfesorDAO implements IBaseDatos<Profesor>{

    @Override
    public List<Profesor> findAll() {
        List<Profesor> profesores = null;
	    String query = "SELECT * FROM Profesor";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int codProfesor = 0;
	    String nom_Profesor = null;
            String apellido = null;
	
	    while (rs.next()){
	    	if(profesores == null){
	    		profesores = new ArrayList<Profesor>();
	    	}
	      
	        Profesor registro= new Profesor();
	        codProfesor = rs.getInt("cod_Profesor");
	        registro.setCodProfesor(codProfesor);
	        
	        nom_Profesor = rs.getString("nom_Profesor");
	        registro.setNom_Profesor(nom_Profesor);
                
                apellido = rs.getString("apellido");
	        registro.setApellido(apellido);
	        
	        profesores.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Profesores");
			e.printStackTrace();
		}
	    
	    return profesores;
    }

    @Override
    public boolean insert(Profesor t) {
        boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Profesor (cod_Profesor, nom_Profesor, apellido)" + " values (?,?,?)";
            PreparedStatement preparedStmt=null;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt (1, t.getCodProfesor());
                preparedStmt.setString (2, t.getNom_Profesor());
                preparedStmt.setString (2, t.getApellido());
		result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
            }
	return result;
    }

    @Override
    public boolean update(Profesor t) {
        boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Profesor set nom_Profesor = ?, apellido = ? where cod_Profesor = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom_Profesor());
		    preparedStmt.setString(2, t.getApellido());
                    preparedStmt.setInt   (3, t.getCodProfesor());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Profesor t) {
        boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Profesor where cod_Profesor = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getCodProfesor());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
