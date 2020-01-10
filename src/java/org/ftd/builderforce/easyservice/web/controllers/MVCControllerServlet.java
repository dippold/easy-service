package org.ftd.builderforce.easyservice.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 3.0.4 - 30/08/2017
 * 
 */
@WebServlet(name = "MVCControllerServlet", urlPatterns = {"/mvc"}, initParams = {
    @WebInitParam(name = "mvcObjectPackage", value = "org.ftd.builderforce.easyservice.web.mvc")
})
public class MVCControllerServlet extends HttpServlet {
    
    private static final long serialVersionUID = 596620210605010358L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req
     * @param res
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {   
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");            
            try {
                String MVCClass = AbstractMVC.readParameter(req, "class");
                String classMVCPackage = getInitParameter("mvcObjectPackage");
                Class theMVCClassName = Class.forName(classMVCPackage + "." + MVCClass);
                IMVC theClassMVCInstance = (IMVC) theMVCClassName.newInstance();
                String nextMVCObject = theClassMVCInstance.execute(req, res);
                req.getRequestDispatcher(nextMVCObject).forward(req, res);
            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                req.setAttribute("msg","Erro MVC! Não encontrei " + AbstractMVC.readParameter(req, "class","null"));           
                req.getRequestDispatcher("signin.jsp").forward(req, res);
            }
    }    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
