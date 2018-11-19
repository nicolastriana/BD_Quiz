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
import vo.Curso;

/**
 *
 * @author Labing
 */
public class CursoDAO implements IBaseDatos<Curso> {

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = null;
	    String query = "SELECT * FROM Curso";
	    Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int codCurso =0;
	    String nom_Curso = null;
	    
	    while (rs.next()){
	    	if(cursos == null){
                    cursos = new ArrayList<Curso>();
	    	}
	      
	        Curso registro= new Curso();
	        codCurso = rs.getInt("cod_curso");
	        registro.setCodCurso(codCurso);
	        
                nom_Curso = rs.getString("nom_Curso");
	        registro.setNom_Curso(nom_Curso);

	        cursos.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Cursos");
			e.printStackTrace();
		}
	    
	    return cursos;
    }

    @Override
    public boolean insert(Curso t) {
        boolean result=false;
		Connection connection=null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	    String query = " insert into Curso (cod_curso, nom_Curso)" + " values (?,?)";
            PreparedStatement preparedStmt=null;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt (1, t.getCodCurso());
                preparedStmt.setString (2, t.getNom_Curso());
		result= preparedStmt.execute();
	    } catch (SQLException e) {
			e.printStackTrace();
            }
	return result;
    }

    @Override
    public boolean update(Curso t) {
        boolean result=false;
		Connection connection= null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
		String query = "update Curso set nom_Curso = ? where cod_curso = ?";
		PreparedStatement preparedStmt=null;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, t.getNom_Curso());
                    preparedStmt.setInt   (2, t.getCodCurso());
		    if (preparedStmt.executeUpdate() > 0){
		    	result=true;
		    }
			    
		} catch (SQLException e) {
				e.printStackTrace();
		}
			
		return result;
    }

    @Override
    public boolean delete(Curso t) {
         boolean result=false;
	   Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
	   String query = "delete from Curso where cod_curso = ?";
	   PreparedStatement preparedStmt=null;
	   try {
		     preparedStmt = connection.prepareStatement(query);
		     preparedStmt.setInt(1, t.getCodCurso());
		    result= preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
	   return result;
    }
    
}
