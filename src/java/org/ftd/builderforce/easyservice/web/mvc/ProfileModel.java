package org.ftd.builderforce.easyservice.web.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.adapters.KeyValueAdapter;
import org.ftd.builderforce.easyservice.web.adapters.ListNameAdapter;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.ProfileDAO;
import org.ftd.educational.entityframework.entities.Profile;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class ProfileModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "Profile";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Perfis");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "Descrição: (Perfil & Papéis)");
        req.setAttribute("showBtnCreate", true);
        req.setAttribute("showBtnHome", true);

        /**
         * DATASOURCES...
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        req.setAttribute("datasource", doAdjustNameToListModel(dao.findProfileEntities()));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "list", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Criando Perfil");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        this.setFieldViewsDefinition(req);

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "create", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildReadModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Perfil");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        this.setFieldViewsDefinition(req);

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        Profile o = dao.findProfile(id);
        req.setAttribute("entity", new KeyValueAdapter(o.getId(), o.getName(), o.getRoleIds(), o.getHomeView(), o.getSystem()));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Atualizando Perfil");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");
        req.setAttribute("fieldKeyLabel", "Nome");
        req.setAttribute("fieldValueLabel", "ID's de Papéis");

        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        this.setFieldViewsDefinition(req);

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        Profile o = dao.findProfile(id);
        req.setAttribute("entity", new KeyValueAdapter(o.getId(), o.getName(), o.getRoleIds(), o.getSystem()));
        req.setAttribute("descriptionValue", o.getHomeView());

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
    }

    /* PRIVATE MEMBERS... */
    private void setFieldViewsDefinition(HttpServletRequest req) {
        req.setAttribute("fieldKeyLabel", "Nome");
        req.setAttribute("fieldKeyType", "input"); // input OR textArea
        req.setAttribute("fieldKeyRow", "3");
        req.setAttribute("fieldKeyMaxSize", "100");
        req.setAttribute("fieldKeyPlaceHolder", "Informe o nome do perfil de acesso");

        req.setAttribute("fieldValueLabel", "Papéis IDs");
        req.setAttribute("fieldValueType", "input"); // input OR textArea
        req.setAttribute("fieldValueRow", "3");
        req.setAttribute("fieldValueMaxSize", "100");
        req.setAttribute("fieldValuePlaceHolder", "Informe os ID's dos papéis separados por ponto e vírgula.");

        req.setAttribute("fieldDescriptionLabel", "Tela de Home");
        req.setAttribute("fieldDescriptionType", "input"); // input OR textArea
        req.setAttribute("fieldDescriptionRow", "3");
        req.setAttribute("fieldDescriptionMaxSize", "100");
        req.setAttribute("fieldDescriptionPlaceHolder", "Defina uma tela de home para o perfil");
    }

    private List<ListNameAdapter> doAdjustNameToListModel(List<Profile> lst) {
        final String icon1 = "&nbsp;&nbsp;<span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\" style=\"color:blue\"></span>&nbsp;&nbsp;";
        List<ListNameAdapter> lstAdapted = new ArrayList<>();
        for (Profile o : lst) {
            lstAdapted.add(new ListNameAdapter(
                    o.getId(),
                    o.getName()
                    + icon1
                    + DatasourceManager.getInstance().getRuleNames(o.getRoleIds())
            ));
        }

        return lstAdapted;
    }

}
