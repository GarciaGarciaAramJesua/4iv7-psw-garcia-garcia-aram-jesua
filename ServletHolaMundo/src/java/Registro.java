/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*de poder realizar la conexion con la bd*/
import java.sql.Connection;
import java.sql.DriverManager;
/*de poder realizar las sentencias sql, create, insert, delete, drop, update*/
import java.sql.Statement;
/*de poder realizar las consultas a la bd*/
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
/**
 *
 * @author Lewandowsky
 */
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    el servlet para poderse conectar con la bd, es necesario inicializar
    voy a necesitar de 3 objetos que vienen de la clase sql
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String nom, appat, apmat, correo, validacion, validacionnonormal, nombrecompleto;
            int edad;
            boolean continuar = true;
            
            nom = request.getParameter("nombre");
            appat = request.getParameter("appat");
            apmat = request.getParameter("apmat");
            edad = Integer.parseInt(request.getParameter("edad"));
            correo = request.getParameter("correo");
            
            nombrecompleto = nom + appat + apmat;
            validacion = "qwertyuiopasdfghjklñzxcvbnmQWERTYUIOPASDFGHJKLÑZXCVBNM "
                    + "áéóúíÁÉÍÓÚ";
            validacionnonormal= validacion + "._@-,$&0123456789";
            
            for(int i=0; i<nombrecompleto.length(); i++){
                char caracter = nombrecompleto.charAt(i);
                for(int j = 0; j < validacion.length(); j++){
                    if(caracter == validacion.charAt(j) || String.valueOf(caracter).equals("ñ")|| String.valueOf(caracter).equals("Ñ"))
                        break;
                    if(j == (validacion.length()-1)){
                        continuar = false;
                        break;
                    }
                }
            }
            
            for(int i=0; i<correo.length(); i++){
                char caracter = correo.charAt(i);
                for(int j = 0; j < validacionnonormal.length(); j++){
                    if(caracter == validacionnonormal.charAt(j))
                        break;
                    if(j == (validacionnonormal.length()-1)){
                        continuar = false;
                        break;
                    }
                }
            }
            
            if(edad<1){
                    continuar = false;
            }
            
            if(continuar==true){
            
            try{
            
                //query para poder insertar los datos en la bd
                /*
                insert into nombretabla (atributo, atributo, ...)
                values ("valor1", "valor2", ...)
                */
            
                String q = "insert into Mregistro "
                        + "(nom_usu, appat_usu, apmat_usu, edad_usu, email_usu)"
                        + "values ('"+nom+"', '"+appat+"', '"+apmat+"', "+edad+", '"+correo+"')";
                
                set.executeUpdate(q);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body style=\"color: #a70e09ea; background-color: #ebdc8b; font-family: 'Poppins', sans-serif; font-weight: bold;\" align=\"center\">"
                    + "<h1 style=\"font-weight: bold;\" align=\"center\">Registro de Usuarios</h1>"
                    + "<br>"
                    + "Tu nombre es: " +nom
                    + "<br>"
                    + "Tu apellido paterno es: " +appat
                    + "<br>"
                    + "Tu apellido materno es: " +apmat
                    + "<br>"
                    + "Tu edad es: " +edad
                    + "<br>"
                    + "Tu email es: " +correo
                    + "<br>");
            out.println("<h1 style=\"font-weight: bold;\" align=\"center\">Registro Exitoso</h1>"
                    + "<br><br>"
                    + "<a style=\"text-decoration: none;\" href='index.html'>"
                    + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                    + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">" 
                    + "Regresar al Menú Principal</div></a>"
                    + "<br>" 
                    + "<a style=\"text-decoration: none;\" href='Consultar'>"
                    + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                    + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                    + "Consultar la Tabla General de Usuarios</div></a>"
                    + "<br>"
                    + "<a style=\"text-decoration: none;\" href='Registro.html'>"
                    + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                    + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                    + "Registrar otro usuario</div></a>");
            out.println("</body>");
            out.println("</html>");
            
            System.out.println("Datos registrados en la tabla");
            
            }catch (Exception e){
                
                System.out.println("No se registraron los datos en la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body style=\"color: #a70e09ea; background-color: #ebdc8b; font-family: 'Poppins', sans-serif;\" align=\"center\">"
                        + "<h1 style=\"font-weight: bold;\" align=\"center\">Registro no realizado</h1>"
                        + "<label>No se pudo realizar el registro, hubo un error.</label>"
                        + "<br><br>"
                        + "<a style=\"text-decoration: none;\" href='index.html'>"
                        + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                        + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">" 
                        + "Regresar al Menú Principal</div></a>"
                        + "<br>" 
                        + "<a style=\"text-decoration: none;\" href='Registro.html'>"
                        + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                        + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                        + "Intentar registrar el usuario de nuevo</div></a>");
                out.println("</body>");
                out.println("</html>");
            }
            }else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body style=\"color: #a70e09ea; background-color: #ebdc8b; font-family: 'Poppins', sans-serif;\" align=\"center\">"
                        + "<h1 style=\"font-weight: bold;\" align=\"center\">Registro no realizado</h1>"
                        + "<label>Se ingresó un dato que no fue validado por el sistema, intente de nuevo.</label>"
                        + "<br><br>"
                        + "<a style=\"text-decoration: none;\" href='index.html'>"
                        + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                        + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">" 
                        + "Regresar al Menú Principal</div></a>"
                        + "<br>" 
                        + "<a style=\"text-decoration: none;\" href='Registro.html'>"
                        + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                        + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                        + "Intentar registrar el usuario de nuevo</div></a>");
                out.println("</body>");
                out.println("</html>");
            }
        }
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
        processRequest(request, response);
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
    
    public void destroy(){
        try{
            con.close();
        
        }catch(Exception e){
            super.destroy();
        
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
