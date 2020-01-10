package org.ftd.builderforce.easyservice.web.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.adapters.ListNameAdapter;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.User;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class UserModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "User";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Usuários");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "(Nome & Perfis)");
        req.setAttribute("showBtnCreate", true);
        req.setAttribute("showBtnHome", true);

        /**
         * DATASOURCES...
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        req.setAttribute("datasource", doAdjustNameToListModel(dao.findUserOrderByName()));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "list", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Criando Usuário");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("fieldNameLabel", "Nome");

        /**
         * DATASOURCES...
         */
        req.setAttribute("companies", DatasourceManager.getInstance().getCompanies());

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "create", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildReadModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Usuário");
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
        User user = DatasourceManager.getInstance().getUser(id);
        req.setAttribute("entity", user);
        req.setAttribute("companyName", DatasourceManager.getInstance().getCompany(user.getCompanyId()).getName());

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Atualizando Usuário");
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
        req.setAttribute("entity", DatasourceManager.getInstance().getUser(id));
        req.setAttribute("companies", DatasourceManager.getInstance().getCompanies());

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
    }

    /* PRIVATE MEMBERS... */
    private List<ListNameAdapter> doAdjustNameToListModel(List<User> lst) {
        final String iconBlocked = "&nbsp;&nbsp;<span class=\"glyphicon glyphicon-pushpin\" aria-hidden=\"true\" style=\"color:red\"></span>&nbsp;&nbsp;";
        List<ListNameAdapter> lstAdapted = new ArrayList<>();

        for (User o : lst) {
            StringBuilder sb = new StringBuilder();
            String[] names = o.getName().split(" ");
            sb.append(names[0]);
            sb.append("&nbsp;&nbsp;");
            sb.append(DatasourceManager.getInstance().getProfileNames(o.getProfileIds()));
            if (o.getBlocked()) {
                sb.append(iconBlocked);
            }
            
            lstAdapted.add(new ListNameAdapter(o.getId(),sb.toString()));
        }

        return lstAdapted;
    }
}
