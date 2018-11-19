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
import vo.Escuela;

/**
 *
 * @author Labing
 */
public class EscuelaDAO implements IBaseDatos<Escuela> {

    @Override
    public List<Escuela> findAll() {
        List<Escuela> escuelas = null;
	    String query = "SELECT * FROM Escuela";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int codEscuela =0;
	    String nombre = null;
	
	    while (rs.next()){
	    	if(escuelas == null){
	    		escuelas = new ArrayList<Escuela>();
	    	}
	      
	        Escuela registro= new Escuela();
	        codEscuela = rs.getInt("cod_Escuela");
	        registro.setCodEscuela(codEscuela);
	        
	        nombre = rs.getString("nom_Escuela");
	        registro.setNom_Escuela(nombre);
	        
	        escuelas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Escuelas");
			e.printStackTrace();
		}
	    
	    return escuelas;
    }

    @Override
    public boolean insert(Escuela t) {
        boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Escuela (cod_Escuela, nom_Escuela)" + " values (?,?)";
            PreparedStatement preparedStmt=null;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt (1, t.getCodEscuela());
                preparedStmt.setString (2, t.getNom_Escuela());
		result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
            }
	return result;
    }

    @Override
    public boolean update(Escuela t) {
        boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Escuela set nom_Escuela = ? where cod_Escuela = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom_Escuela());
                    preparedStmt.setInt   (2, t.getCodEscuela());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Escuela t) {
        boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(EscuelaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Escuela where cod_Escuela = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getCodEscuela());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
