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
        DataBaseHelper.insertUser("Марко", "Доцевски", "mdocevski", "mdocevski@gmail.com ", "marko");
        DataBaseHelper.insertUser("Игор", "Судијовски", "isudijovski", "isudijovski@gmail.com ", "igor");
        DataBaseHelper.insertUser("Филип", "Хрисафов", "fhrisafov", "fhrisafov@gmail.com ", "filip");
        DataBaseHelper.insertUser("Ѓорѓи", "Маџаров", "gmadzarov", "asdfa@asdfa.sad", "gorgi");
        DataBaseHelper.insertAdministrator("isudijovski");
        DataBaseHelper.insertRestoran("Енрико", "Лептокарија", "076512354");
        DataBaseHelper.insertRestoran("Вегера", "Карпош 3", "076512354");
        DataBaseHelper.insertRestoran("Старо буре", "Лептокарија", "076512354");
        DataBaseHelper.insertStavkaMeni("Пица");
        DataBaseHelper.insertStavkaMeni("Лазањи");
        DataBaseHelper.insertStavkaMeni("Кифла");
        DataBaseHelper.insertStavkaMeni("Кебап");
        DataBaseHelper.insertStavkaMeni("Пилешки прсти");
        DataBaseHelper.insertMeni("250", "Енрико", "Пица");
        DataBaseHelper.insertMeni("80", "Енрико", "Сендвич");
        DataBaseHelper.insertMeni("280", "Енрико", "Лазањи");
        DataBaseHelper.insertMeni("20", "Вегера", "Геврек");
        DataBaseHelper.insertMeni("30", "Вегера", "Кифла");
        DataBaseHelper.insertMeni("50", "Вегера", "Бурек");
        DataBaseHelper.insertMeni("12", "Старо буре", "Кебап");
        DataBaseHelper.insertMeni("170", "Старо буре", "Ловечка");
        DataBaseHelper.insertMeni("250", "Старо буре", "Пилешки прсти");
        DataBaseHelper.insertTekovnaGrupa("12:00:00", "isudijovski", "Енрико");
        DataBaseHelper.insertTekovnaGrupa("11:30:00", "mdocevski", "Вегера");
        DataBaseHelper.insertNaracka("fhrisafov", 1, "Пица", "Без кечап", "fhrisafov");
        DataBaseHelper.insertNaracka("isudijovski", 1, "Лазањи", "", "isudijovski");
        DataBaseHelper.insertNaracka("mdocevski", 2, "Бурек", "", "mdocevski");
        DataBaseHelper.insertArhiviraniGrupi("2012-01-05", "isudijovski", "Енрико", "Пица");
        DataBaseHelper.insertArhiviraniGrupi("2012-01-06", "isudijovski", "Вегера", "Бурек");
        DataBaseHelper.insertArhiviraniGrupi("2012-01-07", "isudijovski", "Енрико", "Пица");
        DataBaseHelper.insertArhiviraniGrupi("2012-01-07", "mdocevski", "Енрико", "Пица");
        DataBaseHelper.insertNotofication("Групата...", "isudijovski");
        DataBaseHelper.insertNotofication("Групата mdocevski", "gmadzarov");
        DataBaseHelper.insertPokani("gmadzarov", 1);
        DataBaseHelper.insertPokani("gmadzarov", 2);
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
