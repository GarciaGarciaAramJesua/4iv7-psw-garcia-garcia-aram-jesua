import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;


/**
 *
 * @author Lewandowsky
 */
public class Modificar extends HttpServlet {

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
                    
        String userName = "bdb01263b39425";
        String password = "dc50908f";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            /*
            a veces el driver ya maneja por defecto el puerto de comunicación
            por ello que pueden mandar un error, en ese caso
            url = "jdbc:mysql://localhost/registro4iv7ggaj";
            */
            url = "jdbc:mysql://us-cdbr-east-03.cleardb.com:3306/heroku_891dae52504be37";
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Modificar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Modificar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String modificacion, validacion, validacioncorreo, validacionnum= "";
            String chosenbut = "";
            String q = "";
            int id = 0;
            boolean continuar = true, continuaralt = false;
            
            chosenbut = request.getParameter("Criterio"); 
            id = Integer.parseInt(request.getParameter("idmodificar"));
            modificacion = request.getParameter("modificacion");
            
            validacion = "QWERTYUIOPASDFGHJKLÑZXCVBNM"
                        + "qweryuiopasdfghjklñzxcvbnmáéíóúÁÉÍÓÚ";
            validacioncorreo = validacion + "._@-,$&";
            validacionnum = "1234567890";
            
            if(chosenbut.equals("Nombre") || chosenbut.equals("ApePater") || chosenbut.equals("ApeMater")){
                for(int i=0; i<modificacion.length(); i++){
                    char caracter = modificacion.charAt(i);
                    for(int j = 0; j < modificacion.length(); j++){
                        if(caracter == modificacion.charAt(j))
                            break;
                        if(j == (validacion.length()-1)){
                            continuar = false;
                            break;
                        }
                    }
                }
                if(modificacion.length()>45 && modificacion.length()==0){
                    continuar = false;
                }
            }
            
            if(chosenbut.equals("Edad")){
                for(int i=0; i<modificacion.length(); i++){
                    char caracter = modificacion.charAt(i);
                    for(int j = 0; j < validacionnum.length(); j++){
                        if(caracter == validacionnum.charAt(j))
                            break;
                        if(j == (validacionnum.length()-1)){
                            continuar = false;
                            break;
                        }
                    }
                }
                if(modificacion.length()>2 && modificacion.length()==0){
                    continuar = false;
                }
            }
            
            if(chosenbut.equals("Edad") && continuar == true){
                if(Integer.parseInt(modificacion)<1){
                    continuaralt = false;
                }
            }
            
            
            if(chosenbut.equals("Correo")){
                for(int i=0; i<modificacion.length(); i++){
                    char caracter = modificacion.charAt(i);
                    for(int j = 0; j < validacioncorreo.length(); j++){
                        if(caracter == validacioncorreo.charAt(j))
                            break;
                        if(j == (validacioncorreo.length()-1)){
                            continuar = false;
                            break;
                        }
                    }
                }
                Pattern pat = Pattern.compile("([a-z0-9]+(\\\\.?[a-z0-9])*)+@(([a-z]+)\\\\.([a-z]+))+");
                Matcher mather = pat.matcher(modificacion);
                if (mather.find() == false) {
                    continuar = false;
                }
            }
            
            
            if(id <1 ){
                continuar = false;
            }
            
            try{
                String q2 = "select * from mregistro";
                set = con.createStatement();
                rs = set.executeQuery(q2);
                int idEnBD;
                while(rs.next()){
                    idEnBD = rs.getInt("id_usu");
                    if(idEnBD == id){
                        continuaralt = true;
                    }
                }
            }catch(Exception e){
                System.out.println("No se ha podido consultar el usuario con ese id en la tabla de la BD");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
        
            if(continuar == true && continuaralt == true){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Modificar</title>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body style=\"color: #a70e09ea; background-color: #97fb0d; font-family: 'Poppins', sans-serif;\" align=\"center\">");
                out.println("<h1 style='font-weight: bold;' align='center'>Usuario Modificado</h1>");
                try{
                    switch(chosenbut){
                        case "Nombre":
                            q = "update mregistro set nom_usu ='"+modificacion+"' where id_usu ="+id;
                        break;

                        case "ApePater":
                            q = "update mregistro set appat_usu ='"+modificacion+"' where id_usu ="+id;
                        break;
                            
                        case "ApeMater":
                            q = "update mregistro set apmat_usu ='"+modificacion+"' where id_usu ="+id;
                        break;
                            
                        case "Edad":
                            int edadnueva= Integer.parseInt(modificacion);
                            q = "update mregistro set edad_usu ="+edadnueva+" where id_usu ="+id;
                        break;
                            
                        case "Correo":
                            q = "update mregistro set email_usu ='"+modificacion+"'where id_usu ="+id;
                        break; 
                    }
                    if(!(q.equals(""))){
                        set.executeUpdate(q);
                    }
                    out.println("<label>El usuario con el id: " +id
                        + " ha sido modificado con sus cambios correspondientes.</label>");
            
                }catch(Exception e){
                    out.println("<label>El usuario con el id: " +id
                            + " no ha sido modificado, ocurrió un error al hacer los cambios.</label>");
                    System.out.println("No se ha podido modificar el usuario");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
                    
                }
                
                out.println("<br><br><br><br>"
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
                        + "<a style=\"text-decoration: none;\" href='Modificar.html'>"
                        + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                        + "color: white; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                        + "Modificar de nuevo</div></a>");
                out.println("</body>");
                out.println("</html>");
            
            }else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Usuario no modificado</title>");            
                out.println("<link rel='preconnect' href='https://fonts.gstatic.com'>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap' rel='stylesheet'>");      
                out.println("</head>");
                out.println("<body style=\"color: #a70e09ea; background-color: #97fb0d; font-family: 'Poppins', sans-serif;\" align=\"center\">"
                            + "<h1 style='font-weight: bold;' align='center'>Usuario no modificado</h1>"
                            + "<br><br><label>Se introdujo un dato que no es válido o el id del usuario no existe.<label>"
                            + "<br><br><br><br>"
                            + "<a style=\"text-decoration: none;\" href='index.html'>"
                            + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                            + "color: #ebdc8b; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">" 
                            + "Regresar al Menú Principal</div></a>"
                            + "<br>" 
                            + "<a style=\"text-decoration: none;\" href='Consultar'>"
                            + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;" 
                            + "color: #ebdc8b; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                            + "Consultar la Tabla General de Usuarios</div></a>"
                            + "<br>"
                            + "<a style=\"text-decoration: none;\" href='Modificar.html'>"
                            + "<div style=\"font-family: 'Poppins', sans-serif; border-radius: 5px; width: 20%;"
                            + "color: #ebdc8b; background-color: #a70e09ea; margin-left: 30rem; text-align: center;\">"
                            + "Modificar de nuevo</div></a>");      
                out.println("</body>");
                out.println("</html>");
            }
        }
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
