
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;


/**
 *
 * @author Acer
 */
@WebServlet(name = "login2", urlPatterns = {"/login2"})
public class login2 extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        staffB sf2=new staffB();
        sf2.setEmail(email);
        sf2.setPassword(password);

        try {
            if(sf2.login())
            {
                out.println("Login Success");
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                RequestDispatcher rd = request.getRequestDispatcher("client.jsp");
                rd.include(request, response);
            }
            else{
                out.println("Login Failed");
                RequestDispatcher rd = request.getRequestDispatcher("MyAdmin.html");
                rd.include(request, response);
                
            }   } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
                 processRequest(request, response);
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
