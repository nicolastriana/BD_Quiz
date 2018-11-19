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
import vo.Registro;

/**
 *
 * @author Labing
 */
public class RegistroDAO implements IBaseDatos<Registro>{

    @Override
    public List<Registro> findAll() {
        List<Registro> registros = null;
	    String query = "SELECT * FROM Registro";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_registro =0;
	    int semestre =0;
	    int nota =0;
	    
	    while (rs.next()){
	    	if(registros == null){
                    registros = new ArrayList<Registro>();
	    	}
	      
	        Registro registro= new Registro();
	        id_registro = rs.getInt("id_registro");
	        registro.setId_registro(id_registro);
	        
                semestre = rs.getInt("semestre");
	        registro.setSemestre(semestre);
	        
                nota = rs.getInt("nota");
	        registro.setNota(nota);
                
	        registros.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Registros");
			e.printStackTrace();
		}
	    
	    return registros;
    }

    @Override
    public boolean insert(Registro t) {
        boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Registro (id_registro, semestre, nota)" + " values (?,?,?)";
            PreparedStatement preparedStmt=null;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt (1, t.getId_registro());
		preparedStmt.setInt (2, t.getSemestre());
		preparedStmt.setInt (3, t.getNota());                
		result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
            }
	return result;
    }

    @Override
    public boolean update(Registro t) {
        boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Registro set semestre = ?, nota = ? where id_registro = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setInt   (1, t.getSemestre());
                    preparedStmt.setInt   (2, t.getNota());
                    preparedStmt.setInt   (3, t.getId_registro());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Registro t) {
        boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Registro where id_registro = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getId_registro());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
