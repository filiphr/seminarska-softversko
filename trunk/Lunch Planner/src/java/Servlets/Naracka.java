/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import sun.org.mozilla.javascript.internal.regexp.RegExpImpl;

/**
 *
 * @author Filip
 */
public class Naracka extends HttpServlet {

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
            out.println("<title>Servlet Naracka</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Naracka at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        List<String> Odbrani = (List<String>) session.getAttribute("Odbrani");
        if (Odbrani == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Naracka.jsp");
            rd.forward(request, response);
        } else {
            String user = (String) session.getAttribute("username");
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            String komentar = request.getParameter("komentar");
            if (komentar == null) {
                for (int i = 0; i < Odbrani.size(); i++) {
                    DataBaseHelper.insertNaracka(user, groupID, Odbrani.get(i));
                }
            } else {
                for (int i = 0; i < Odbrani.size(); i++) {
                    DataBaseHelper.insertNaracka(user, groupID, Odbrani.get(i), komentar);
                }
            }
            session.removeAttribute("Odbrani");

            RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
            rd.forward(request, response);
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
