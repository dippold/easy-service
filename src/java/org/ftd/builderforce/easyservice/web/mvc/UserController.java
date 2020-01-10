package org.ftd.builderforce.easyservice.web.mvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.User;
import org.ftd.educational.entityframework.entities.Rota;
import org.ftd.educational.web.mvc.abstracts.AbstractControllerMVC;
import static org.ftd.educational.web.mvc.abstracts.AbstractMVC.readParameter;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-09-01
 *
 */
public class UserController extends AbstractControllerMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";

    @Override
    public String doCreate(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        try {
            User o = new User();
            o.setCompanyId(Long.parseLong(readParameter(req, "comboCompany", "")));
            o.setName(readParameter(req, "nameInput"));
            String[] names = o.getName().split(" ");
            o.setNickname(names[0]);
            o.setEmail(readParameter(req, "emailInput", ""));
            o.setPasswd(readParameter(req, "passwdInput", ""));
            o.setProfileIds(readParameter(req, "profileIdsInput", ""));
            o.setBlocked(Boolean.parseBoolean(readParameter(req, "comboBlocked", "")));
            o.setSystem(Boolean.parseBoolean(readParameter(req, "comboSystem", "")));
            dao.create(o);
            req.setAttribute("msg", "ID=" + o.getId() + " foi criado!");
            Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "create", PERSISTENCE_UNIT_NAME);

            return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoSucesso();

        } catch (Exception e) {
            req.setAttribute("msg", "Criar registro falhou! " + e.getMessage());
            Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "create", PERSISTENCE_UNIT_NAME);

            return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoFalha();

        }
    }

    @Override
    public String doUpdate(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        String id = readParameter(req, "id", "");
        try {
            User o = dao.find(Long.parseLong(id));
            o.setCompanyId(Long.parseLong(readParameter(req, "comboCompany", "")));
            o.setName(readParameter(req, "nameInput"));
            o.setEmail(readParameter(req, "emailInput", ""));
            o.setPasswd(readParameter(req, "passwdInput", ""));
            o.setProfileIds(readParameter(req, "profileIdsInput", ""));
            o.setBlocked(Boolean.parseBoolean(readParameter(req, "comboBlocked", "")));
            o.setSystem(Boolean.parseBoolean(readParameter(req, "comboSystem", "")));
            
            dao.edit(o);
            req.setAttribute("msg", "ID=" + id + " atualizado!");
            Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
            return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoSucesso();
        } catch (Exception e) {
            req.setAttribute("msg", "Atualizar ID=" + id + " falhou!");
            Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
            return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoFalha();
        }
    }

    @Override
    public String doDelete(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        String id = readParameter(req, "id", "");
        try {
            User o = dao.find(Long.parseLong(id));
            if (o.getSystem()) {
                req.setAttribute("msg", "Dado de sistema não pode ser deletado!");
                Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
                return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoFalha();
            } else {
                dao.destroy(Long.parseLong(id));
                req.setAttribute("msg", "ID=" + id + " deletado!");
                Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
                return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoSucesso();
            }
        } catch (Exception e) {
            req.setAttribute("msg", "Apagar ID=" + id + " falhou!");
            Rota rota = MVCManager.getInstance().findByController(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
            return "mvc?class=" + rota.getRecurso() + "&do=" + rota.getAcaoFalha();
        }
    }

}
