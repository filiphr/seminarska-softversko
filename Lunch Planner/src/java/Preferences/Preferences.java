/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Preferences;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Home
 */
public class Preferences extends HttpServlet {

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
            out.println("<title>Servlet Preferences</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Preferences at " + request.getContextPath () + "</h1>");
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
        String restorant = request.getParameter("ImeRestorant");
        String stavka = request.getParameter("Meni");
        String vreme = request.getParameter("Vreme");
        String komentar = request.getParameter("Komentar");
        String user=null;
        HttpSession ses = request.getSession();
        synchronized(ses)
        {
            user=(String) ses.getAttribute("username");
        }
        if(restorant!=null)
            DataBaseHelper.updatePreferencesRestoran(restorant, user);
        if(stavka!=null)
            DataBaseHelper.updatePreferencesStavka(stavka, user);
        if(vreme!=null)
            DataBaseHelper.updatePreferencesVreme(vreme, user);
        if(komentar!=null)
            DataBaseHelper.updatePreferencesKomentar(komentar, user);
        
        RequestDispatcher rd = request.getRequestDispatcher("Preferences.jsp");
        rd.forward(request,response);
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