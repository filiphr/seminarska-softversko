/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naracka;

import DataBase.DataBaseHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.net.SecureNioChannel.ApplicationBufferHandler;
import prediction.BuildC45forAllEmployee;

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
        //Get the session
        HttpSession session = request.getSession();

        //Get the pending order
        List<String> Odbrani = new ArrayList<String>();

        String tmp1 = request.getParameter("Odbrani");
        if (tmp1 == null || tmp1.isEmpty()) {
            //If the order is empty just forward to Naracka.jsp
            //RequestDispatcher rd = request.getRequestDispatcher("Naracka.jsp");
            //rd.forward(request, response);
            response.sendRedirect("Naracka.jsp");
        } else {
            String[] tmpNiza = tmp1.split(";;");
            Odbrani.addAll(Arrays.asList(tmpNiza));
            //Get the user for whom the order is being made
            String user = (String) request.getParameter("OrderUser");

            //Get the user from the session
            String UserOrdering = (String) session.getAttribute("username");

            //Get the groupID
            int IDGroup = Integer.parseInt(request.getParameter("groupID"));

            //Get the comment
            String komentar = request.getParameter("komentar");

            //Get the ID for the orders that the user has done in the group with new orders
            List<Integer> ID = DataBaseHelper.getIDNaracka2(user, IDGroup);
            //If there are orders we need do delete them in order to avoid dulicates
            if (!ID.isEmpty()) {
                for (int i = 0; i < ID.size(); i++) {
                    Integer tmp = ID.get(i);
                    DataBaseHelper.deleteNaracka(tmp);
                }
            }
            if (komentar == null) {
                //If there is no comment create an empty one
                komentar = new String();
            }

            //Add the pending orders in the database
            for (int i = 0; i < Odbrani.size(); i++) {
                String kom = request.getParameter("Komentar" + i);
                DataBaseHelper.insertNaracka(user, IDGroup, Odbrani.get(i), kom, UserOrdering);

            }
            //Remove the pending order from the session
            //session.removeAttribute("Odbrani");

            //Redirecto to MainPage.jsp
            //RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
            // rd.forward(request, response);
            //response.sendRedirect("MainPage.jsp");

            if (!user.equals(UserOrdering)) {
                response.sendRedirect("Naracka.jsp?groupID=" + IDGroup + "&join=false");
            }else {
                ServletContext application = getServletContext();
                BuildC45forAllEmployee bC45 = (BuildC45forAllEmployee) application.getAttribute("PredikcijaRestorani");
                String restoran = DataBaseHelper.getRestaurantName(IDGroup);
                String date = (String) application.getAttribute("DatumObuka");
                try {
                   bC45.BuildRestoran(date);
                    synchronized(application){
                        application.setAttribute("PredikcijaRestorani", bC45);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Naracka.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("MainPage.jsp");
            }

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
