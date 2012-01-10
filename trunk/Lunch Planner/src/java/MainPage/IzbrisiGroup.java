/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPage;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class IzbrisiGroup extends HttpServlet {

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
            out.println("<title>Servlet IzbrisiGroup</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IzbrisiGroup at " + request.getContextPath () + "</h1>");
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
        response.sendRedirect("MainPage.jsp");
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
        //processRequest(request, response);
        String User = (String)request.getSession().getAttribute("username");
        String inf = (String)request.getParameter("t1");
        int Group = Integer.parseInt(request.getParameter("groupID"));
        List<String> resVreme = DataBaseHelper.getRestoranAndVreme(Group);
        String str = "Групата во " + resVreme.get(0) + " во " + resVreme.get(1) + " часот, креирана од " + DataBaseHelper.getUserIme(User) + " " + DataBaseHelper.getUserPrezime(User)+ " е избришана, од причина: ";
        List<String> Users = DataBaseHelper.getUserByGroup(Group);
        for(int i = 0; i<Users.size(); i++)
        {
            if(!(Users.get(i).equals(User)))
            DataBaseHelper.insertNotofication(str + inf, Users.get(i));
        }
        DataBaseHelper.deleteGroup(Group);
        response.sendRedirect("MainPage.jsp");
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
