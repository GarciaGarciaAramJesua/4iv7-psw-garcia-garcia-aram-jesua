/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//de poder realizar la conexion con la bd
import java.sql.Connection;
import java.sql.DriverManager;
//de poder realizar las sentencias sql, create, insert, delete, drop, update, alter
import java.sql.Statement;
//de poder realizar las consultas a la bd
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

/**
 *
 * @author Lewandowsky
 */
@WebServlet(urlPatterns = {"/Consultar"})
public class Consultar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    //vamos a crear el metodo constructor
    
    public void init(ServletConfig cfg) throws ServletException{
        //para conectarnos con la bd
        String url = "jdbc:mysql:3306//localhost/registro4iv7ggaj";
                    //driver:gestorbd:puerto//IP/nombrebd
                    
        String userName = "root";
        String password = "Aram2004";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            /*
            a veces el driver ya maneja por defecto el puerto de comunicación
            por ello que pueden mandar un error, en ese caso
            url = "jdbc:mysql://localhost/registro4iv7ggaj";
            */
            url = "jdbc:mysql://localhost/registro4iv7ggaj";
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            
            System.out.println("Se ha conectado a la BD");
            
        }catch(Exception e){
            System.out.println("No se ha conectado a la BD");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Consultar Usuarios</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body style=\"color: #a70e09ea; background-color: #f8b334; font-family: 'Poppins', sans-serif;\" align=\"center\">");
            out.println("<h1 style='font-weight: bold;' align='center'>Tabla General de Usuarios</h1>");
            out.println("<table align='center' style=\"border: 1px solid #a70e09ea;\">"
                    + "<thead>"
                    + "<tr>"
                    + "<th style=\"border-bottom: 1px solid #a70e09ea; border-right: 1px solid #a70e09ea;\">ID</th>"
                    + "<th style=\"border-bottom: 1px solid #a70e09ea; border-right: 1px solid #a70e09ea;\">Nombre Completo</th>"
                    + "<th style=\"border-bottom: 1px solid #a70e09ea; border-right: 1px solid #a70e09ea;\">Edad</th>"
                    + "<th style=\"border-bottom: 1px solid #a70e09ea;\">Correo</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>");
            try{
                String nom, appat, apmat, correo, q;
                int edad, id;
                
                q = "select * from mregistro";
                
                set = con.createStatement();
                rs = set.executeQuery(q);
                while(rs.next()){
                    id = rs.getInt("id_usu");
                    nom = rs.getString("nom_usu");
                    appat = rs.getString("appat_usu");
                    apmat = rs.getString("apmat_usu");
                    edad = rs.getInt("edad_usu");
                    correo = rs.getString("email_usu");
                    out.println("<tr>"
                            + "<td style=\" border-right: 1px solid #a70e09ea;\">" +id+ "</td>"
                            + "<td style=\" border-right: 1px solid #a70e09ea;\">" +nom+" "+appat+" "+apmat+"</td>"
                            + "<td style=\" border-right: 1px solid #a70e09ea;\">" +edad+ "</td>"
                            + "<td>" +correo+ "</td>"
                            + "</tr>");
                }
                
                System.out.println("Consulta exitosa");
                rs.close();
                set.close();
                
            }catch(Exception e){
                System.out.println("Error al consultar la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());


            }
            out.println("</tbody>"
                    + "</table>"
                    + "<br>"
                    + "<a style=\"text-decoration: none;\" href='index.html'>"
                    + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                    + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">" 
                    + "Regresar al Menú Principal</div></a>"
                    + "<br>"
                    + "<a style=\"text-decoration: none;\" href='Registro.html'>"
                    + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                    + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                    + "Registrar otro usuario</div></a>");
            out.println("</body>");
            out.println("</html>");
        } 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    
    public void destroy(){
        try{
            con.close();

        }catch(Exception e){
            super.destroy();

        }
    }
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
