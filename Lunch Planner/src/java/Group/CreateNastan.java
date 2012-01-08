/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Group;

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
 * @author Home
 */
public class CreateNastan extends HttpServlet {

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
            out.println("<title>Servlet CreateNastan</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateNastan at " + request.getContextPath () + "</h1>");
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

        response.sendRedirect("Create.jsp");

        /*RequestDispatcher rd = request.getRequestDispatcher("Create.jsp");
        rd.forward(request, response);*/
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
        HttpSession ses = request.getSession();
        String user = null;
        synchronized (ses) {
            user = (String) ses.getAttribute("username");
        }
        if(DataBaseHelper.hasCreatedEvent(user))
            response.sendRedirect("MainPage.jsp");
        String restorant = request.getParameter("ImeRestorant");
        String vreme = request.getParameter("Vreme");

        if (user != null && restorant != null && vreme != null) {
            String str = DataBaseHelper.getIDGrupa(restorant, vreme);
            if(!str.isEmpty())
            {
                DataBaseHelper.insertPokani(user, Integer.parseInt(str));
                response.sendRedirect("MainPage.jsp");
                return;
            }
            DataBaseHelper.insertTekovnaGrupa(vreme, user, restorant);
        }
        String[] pokani = request.getParameterValues("Pokani");
        int id = DataBaseHelper.getGroupIDFromCreator(user);
        if (pokani != null) {
            for (int i = 0; i < pokani.length; i++) {
                DataBaseHelper.insertPokani(pokani[i], id);
            }
        }
        response.sendRedirect("Naracka.jsp?groupID=" + id + "&Izmeni=0&join=true");

        /*RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
        rd.forward(request, response);*/
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
