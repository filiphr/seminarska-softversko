/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Invite;

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

/**
 *
 * @author Filip
 */
public class PokaniServlet extends HttpServlet {

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
            out.println("<title>Servlet PokaniServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PokaniServlet at " + request.getContextPath () + "</h1>");
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
        String grID = request.getParameter("groupID");
        int IDGroup = Integer.parseInt(grID);
        List<String> Pokaneti = (List<String>) session.getAttribute("pokaneti");
        if (Pokaneti == null) {
            RequestDispatcher rd = request.getRequestDispatcher("Invite.jsp?groupID=" + IDGroup);
            rd.forward(request, response);
        } else {
            String username = (String) session.getAttribute("username");
            for (int i = 0; i < Pokaneti.size(); i++) {
                DataBaseHelper.insertPokani(Pokaneti.get(i), IDGroup);
            }
        }
        session.removeAttribute("pokaneti");

        RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
        rd.forward(request, response);
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
