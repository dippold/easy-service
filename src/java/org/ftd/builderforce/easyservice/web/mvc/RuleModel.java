package org.ftd.builderforce.easyservice.web.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.adapters.ListNameAdapter;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.RuleDAO;
import org.ftd.educational.entityframework.entities.Profile;
import org.ftd.educational.entityframework.entities.Rule;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class RuleModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "Rule";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Papéis");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "Nome");
        req.setAttribute("showBtnHome", true);
        req.setAttribute("showBtnCreate", true);
        req.setAttribute("showColumnId", true);

        /**
         * DATASOURCES...
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RuleDAO dao = new RuleDAO(factory);
        req.setAttribute("datasource", dao.findAll());

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
