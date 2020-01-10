package org.ftd.builderforce.easyservice.web.mvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.ConfigKeyDAO;
import org.ftd.educational.entityframework.daos.ProfileDAO;
import org.ftd.educational.entityframework.daos.RotaDAO;
import org.ftd.educational.entityframework.daos.RuleDAO;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.Profile;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class HomeAdminModel extends AbstractModelMVC implements IMVC {

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
        req.setAttribute("showBtnCreate", false);

        /**
         * PROFILE ADMINISTRATION...
         */
        HttpSession session = req.getSession(false);
//        req.removeAttribute("profileId");
//        req.removeAttribute("profileName");
        req.setAttribute("profileId", session.getAttribute("userProfileId"));
        req.setAttribute("profileName", session.getAttribute("userProfileName"));
        
//        String sessionVars = "(userProfileId:" + (String) session.getAttribute("userProfileId")
//                + " | userProfileName:" + (String) session.getAttribute("userProfileName")
//                + " | userProfileIds:" + (String) session.getAttribute("userProfileIds")
//                + ")";
//        
//        req.setAttribute("sessionVars", sessionVars);
        
        /**
         * DATASOURCES...
         */        
        String userProfileIds = (String) session.getAttribute("userProfileIds");
        req.setAttribute("userProfiles", DatasourceManager.getInstance().getProfiles(userProfileIds));
        req.setAttribute("rotas", DatasourceManager.getInstance().getRotasActiveProfile(req));

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
