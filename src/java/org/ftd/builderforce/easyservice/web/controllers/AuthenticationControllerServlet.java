package org.ftd.builderforce.easyservice.web.controllers;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.entityframework.daos.ProfileDAO;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.Profile;
import org.ftd.educational.entityframework.entities.User;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 29/012/2016
 *
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/signin"})
public class AuthenticationControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 469878074894648120L;
    final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User user;
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");
        if ((email != null) && (passwd != null)) {
            user = this.findUser(email, passwd);
            if ((user != null) && (!user.getBlocked())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", Long.toString(user.getId()));
                session.setAttribute("userName", user.getName());
                session.setAttribute("userCompanyId", Long.toString(user.getCompanyId()));                
                session.setAttribute("userProfileIds", user.getProfileIds());
                String[] profileIds = user.getProfileIds().split(";");
                if (profileIds[0].equals("*")) {
                    profileIds[0] = "1";
                }
                session.setAttribute("userProfileId", profileIds[0]);
                session.setAttribute("userProfileName", getProfile(Long.parseLong(profileIds[0])).getName());

                String nextUrl = "mvc?class=HomeModel&do=list";
                request.getRequestDispatcher(nextUrl).forward(request, response);
            } else {
                request.setAttribute("msg", "Senha informada inválida ou usuário bloqueado!");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "Informe email e senha para autenticação!");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }

    private User findUser(String email, String passwd) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        User user;
        try {
            user = dao.findUser(email, passwd);
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

    private Profile getProfile(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        Profile profile;
        try {
            profile = dao.find(id);
        } catch (NoResultException e) {
            profile = null;
        }

        return profile;
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
