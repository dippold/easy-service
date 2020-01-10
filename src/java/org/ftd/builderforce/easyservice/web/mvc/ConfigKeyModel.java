package org.ftd.builderforce.easyservice.web.mvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.adapters.KeyValueAdapter;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.builderforce.easyservice.web.mvc.managers.MVCManager;
import org.ftd.educational.entityframework.daos.ConfigKeyDAO;
import org.ftd.educational.entityframework.entities.ConfigKey;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 *
 */
public class ConfigKeyModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "ConfigKey";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Chaves");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "Descrição: (Chave & Valor)");
        req.setAttribute("showBtnCreate", true);
        req.setAttribute("showBtnHome", true);

        /**
         * DATASOURCES...
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ConfigKeyDAO dao = new ConfigKeyDAO(factory);
        req.setAttribute("datasource", dao.findAll());

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "list", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Criando Chave");
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
        req.setAttribute("title", "Chave");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");
        req.setAttribute("fieldKeyLabel", "Chave");
        req.setAttribute("fieldValueLabel", "Valor");
        
        /**
         * LABEL'S VIEW DEFINITIONS...
         */
        this.setFieldViewsDefinition(req);
        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ConfigKeyDAO dao = new ConfigKeyDAO(factory);
        ConfigKey o = dao.findConfigKey(id);
        req.setAttribute("entity", new KeyValueAdapter(o.getId(), o.getName(), o.getValue(), o.getSystem()));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "delete", PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Atualizando Chave");
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
        ConfigKeyDAO dao = new ConfigKeyDAO(factory);
        ConfigKey o = dao.findConfigKey(id);
        req.setAttribute("entity", new KeyValueAdapter(o.getId(), o.getName(), o.getValue(), o.getSystem()));

        return MVCManager.getInstance().findView(this.getClass().getSimpleName(), "update", PERSISTENCE_UNIT_NAME);
    }

    /* PRIVATE MEMBERS... */
    private void setFieldViewsDefinition(HttpServletRequest req) {
        req.setAttribute("fieldKeyLabel", "Chave");
        req.setAttribute("fieldKeyType", "input"); // input OR textArea
        req.setAttribute("fieldKeyRow", "3");
        req.setAttribute("fieldKeyMaxSize", "100");
        req.setAttribute("fieldKeyPlaceHolder", "Informe um nome para chave de sistema");

        req.setAttribute("fieldValueLabel", "Valor");
        req.setAttribute("fieldValueType", "textArea"); // input OR textArea
        req.setAttribute("fieldValueRow", "5");
        req.setAttribute("fieldValueMaxSize", "255");
        req.setAttribute("fieldValuePlaceHolder", "Informe: valor atômico ou objeto/array JSon ou uma lista de valores utilizando ponto e vírgula como separador");

    }

}
