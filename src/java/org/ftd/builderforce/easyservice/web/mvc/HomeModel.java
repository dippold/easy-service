package org.ftd.builderforce.easyservice.web.mvc;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.RuleDAO;
import org.ftd.educational.entityframework.entities.Rota;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class HomeModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "MainAdmin";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Home");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");
        
        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "Nome:");
        req.setAttribute("showBtnHome", false);
        req.setAttribute("showBtnChangeProfile", true);
        req.setAttribute("showBtnCreate", false);
        req.setAttribute("showBtnMsg", true);
        req.setAttribute("showBtnAbout", true);
        req.setAttribute("showBtnLogout", true);

        /**
         * PROFILE ADMINISTRATION...
         */
        HttpSession session = req.getSession(false);
        req.setAttribute("profileId", session.getAttribute("userProfileId"));
        req.setAttribute("profileName", session.getAttribute("userProfileName"));
        
        /**
         * DATASOURCES...
         */        
        String userProfileIds = (String) session.getAttribute("userProfileIds");
        String[] countIds = userProfileIds.split(";");
        if (countIds.length == 1) {
            req.setAttribute("showBtnChangeProfile", false);
        }
        req.setAttribute("userProfiles", DatasourceManager.getInstance().getProfiles(userProfileIds));
        
        List<Rota> rotas = DatasourceManager.getInstance().getRotasActiveProfile(req);
        req.setAttribute("rotas", rotas);
        req.setAttribute("processos", DatasourceManager.getInstance().getBusinessProcess(rotas));

        String userName = (String) session.getAttribute("userName");
        String[] names = userName.split(" ");
        req.setAttribute("userNickName", names[0]);
        
        req.setAttribute("nrMsgs", 3);
        
        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "list", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Criando Papel");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("fieldNameLabel", "Nome");

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "create", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildReadModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Papel do usuário");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");
        
        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("fieldNameLabel", "Nome");

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RuleDAO dao = new RuleDAO(factory);
        req.setAttribute("entity", dao.findRule(id));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Atualizando Papel");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");
        
        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("fieldNameLabel", "Nome");

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RuleDAO dao = new RuleDAO(factory);
        req.setAttribute("entity", dao.findRule(id));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
    }

}
