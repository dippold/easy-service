package org.ftd.builderforce.easyservice.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.entityframework.daos.ConfigKeyDAO;
import org.ftd.educational.entityframework.daos.SystemMessageDAO;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.ConfigKey;
import org.ftd.educational.entityframework.entities.SystemMessage;
import org.ftd.educational.entityframework.entities.User;


/**
 *
 * @author Fabio Tavares Dippold
 * @version 03/06/2016
 *
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/ca"})
public class CreateAccountControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 5322767689464123753L;

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

        String newName = request.getParameter("newNameInput");
        String newEmail = request.getParameter("newEmailInput");
        String newPasswd = request.getParameter("newPasswdInput");

        String msg;

        if (alreadyExistsThatUserName(newName)) {
            msg = "Nome do usuário informado já existe. Tente outro!";
        } else if (alreadyExistsThatUserEmail(newEmail)) {
            msg = "E-mail informado já existe. Utilize outro!";
        } else if (!createBlockedUser(newName, newEmail, newPasswd)) {
            msg = "Houve um problema ao criar seu usuário. Tente mais tarde!";
        } else {
            createBlockedUser(newName, newEmail, newPasswd);
            msg = "O usuário foi criado com successo. Aguarde o procedimento de validação e aprovação!";
        }

        request.setAttribute("msg", msg);
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    private boolean createBlockedUser(String name, String email, String passwd) {
        boolean success;
        final String PERSISTENCE_UNIT_NAME = "ppmPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswd(passwd);
        user.setRuleId(getDefaultRuleId());
        user.setSystem(false);
        user.setBlocked(true);        
        try {
            dao.create(user);
            this.createTaskMessage(user);
            success = true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            success = false;
        }

        return success;
    }

    private boolean alreadyExistsThatUserName(String name) {
        boolean exists;
        final String PERSISTENCE_UNIT_NAME = "ppmPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        try {
            User user = dao.findUserByName(name);
            exists = true;
        } catch (NoResultException e) {
            exists = false;
        }

        return exists;
    }

    private boolean alreadyExistsThatUserEmail(String email) {
        boolean exists;
        final String PERSISTENCE_UNIT_NAME = "ppmPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        try {
            User user = dao.findUser(email);
            exists = true;
        } catch (NoResultException e) {
            exists = false;
        }

        return exists;
    }

    private Long getDefaultRuleId() {
        final String PERSISTENCE_UNIT_NAME = "ppmPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ConfigKeyDAO dao = new ConfigKeyDAO(factory);
        ConfigKey key = dao.findConfigKey("app.default.rule.id");

        return Long.parseLong(key.getValue());
    }

    private void createTaskMessage(User user) {
        final String PERSISTENCE_UNIT_NAME = "ppmPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        SystemMessageDAO dao = new SystemMessageDAO(factory);
        SystemMessage o = new SystemMessage();

        // SET THE SPECIFICS ATTRIBUTES...
        o.setName(this.getDateTime() + " = Create new user request...");
        o.setUserSenderId(1L);
        o.setUserId(1L);
        o.setUrgent(Boolean.TRUE);
        o.setSubject("Tarefa: APROVAR NOVO USUÁRIO");
        o.setMessage(
                "Foi criado o novo usuário ("
                + user.getName()
                + ") com email (" + user.getEmail() 
                + ")! Favor desbloquear o usuário."
        );
        o.setResource("UserUpdModelCmd");
        o.setResourceId(user.getId());

        // SET THE DEFAULTS ATTRIBUTES...                       
        o.setCompanyId(1L);
        o.setRuleId(1L);
        o.setBlocked(Boolean.TRUE);
        o.setSystem(Boolean.FALSE);

        dao.create(o);
    }

    protected String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        
        return dateFormat.format(date);
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
