/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
public class LoginServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("Najava.jsp");

//        RequestDispatcher rd = request.getRequestDispatcher("Najava.jsp");
//        rd.forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get the username from the request
        String username = request.getParameter("username");

        //Get the password from the request
        String password = request.getParameter("password");

        //Get the remember from the request to check whether the user wants to remeber his password
        String remember = request.getParameter("remember");

        //Check whether a user exists in the database
        String user = null;
        if (DataBaseHelper.CheckUser(username, password)) {
            user = username;
        }

        //If a user exists then add him in session and redirect him to MainPage.jsp
        if (user != null) {

            if ("selected".equals(remember)) {
                //Save his password and username in his cookie
                Cookie c = new Cookie("username", username);
                c.setMaxAge(100000);
                response.addCookie(c);

                c = new Cookie("password", password);
                c.setMaxAge(100000);
                response.addCookie(c);
            }

            //Get the session
            HttpSession session = request.getSession();

            //Save the user in the session
            session.setAttribute("username", username);
            //Redirect to MainPage.jsp
            response.sendRedirect("UserPages/MainPage.jsp");
        } else {
            //Redirect to Najava.jsp
            response.sendRedirect("Najava.jsp");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
