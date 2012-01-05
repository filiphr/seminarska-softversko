/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import DataBase.DataBaseHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Home
 */
public class Popolni extends HttpServlet {

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
         DataBaseHelper.insertUser("marko","docevski","mdocevski","mdocevski@gmail.com ","marko");
         DataBaseHelper.insertUser("igor","sudijovski","isudijovski","isudijovski@gmail.com ","igor");
         DataBaseHelper.insertUser("filip","hrisafov","fhrisafov","fhrisafov@gmail.com ","filip");
         DataBaseHelper.insertRestoran("Enriko", "Leptokarija");
         DataBaseHelper.insertRestoran("Vegera", "Karpos 3");
         DataBaseHelper.insertRestoran("Staro Bure", "Leptokarija");
         DataBaseHelper.insertStavkaMeni("Pica");
         DataBaseHelper.insertStavkaMeni("Lazanji");
         DataBaseHelper.insertStavkaMeni("Kifla");
         DataBaseHelper.insertStavkaMeni("Kebap");
         DataBaseHelper.insertStavkaMeni("Pileski prsti");
         DataBaseHelper.insertMeni("250", "Enriko", "Pica");
         DataBaseHelper.insertMeni("80", "Enriko", "Sendvic");
         DataBaseHelper.insertMeni("280", "Enriko", "Lazanji");
         DataBaseHelper.insertMeni("20", "Vegera", "Gevrek");
         DataBaseHelper.insertMeni("30", "Vegera", "Kifla");
         DataBaseHelper.insertMeni("50", "Vegera", "Burek");
         DataBaseHelper.insertMeni("12", "Staro Bure", "Kebap");
         DataBaseHelper.insertMeni("170", "Staro Bure", "Lovecka");
         DataBaseHelper.insertMeni("250", "Staro Bure", "Pileski prsti");
         DataBaseHelper.insertTekovnaGrupa("12:00:00", "isudijovski", "Enriko");
         DataBaseHelper.insertTekovnaGrupa("11:30:00", "mdocevski", "Vegera");
         DataBaseHelper.insertNaracka("fhrisafov", 1, "Pica", "Bez Kecap");
         DataBaseHelper.insertNaracka("isudijovski", 1, "Lazanji");
         DataBaseHelper.insertNaracka("mdocevski", 2, "Burek");
        response.sendRedirect("Najava.jsp");
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
