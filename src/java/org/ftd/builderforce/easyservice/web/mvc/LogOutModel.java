package org.ftd.builderforce.easyservice.web.mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 */
public class LogOutModel extends AbstractModelMVC implements IMVC {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        AbstractModelMVC.cleanDefaultAppAttributes(req);
        HttpSession session = req.getSession(true);
        if (session != null) {
            session.removeAttribute("userId");
            session.removeAttribute("userName");
            session.removeAttribute("userCompanyId");
            session.removeAttribute("userProfileIds");
            session.removeAttribute("userProfileId");
            session.removeAttribute("userProfileName");
            session.invalidate();
        }

        String msg = (String) req.getAttribute("msg");
        if ((msg == null) || (msg.equals(""))) {
            req.setAttribute("msg", "O usuário solicitou sair!");
        }

        return "signin.jsp";
    }

    @Override
    public String buildListModel(HttpServletRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildReadModel(HttpServletRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
