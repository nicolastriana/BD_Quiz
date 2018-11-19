package servlet;
 
import dao.EstudianteDAO;
import vo.Estudiante;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("codEstudiante");
        String nombre = request.getParameter("nom_Estudiante");
        String beca = request.getParameter("beca");
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        EstudianteDAO dao = new EstudianteDAO();
        
        Estudiante estudiante = new Estudiante();
        estudiante.setCodEstudiante(Integer.parseInt(id));
        estudiante.setNom_Estudiante(nombre);
        estudiante.setBeca(beca);
        dao.insert(estudiante);
        
        //Listando la informacion  
        List<Estudiante> estudiantes =  dao.findAll();
        request.setAttribute("estudiantes", estudiantes);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        
        }
}
