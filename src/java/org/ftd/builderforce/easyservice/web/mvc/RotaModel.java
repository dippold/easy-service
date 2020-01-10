package org.ftd.builderforce.easyservice.web.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.builderforce.easyservice.web.adapters.IdNameAdapter;
import org.ftd.builderforce.easyservice.web.adapters.ListNameAdapter;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.educational.entityframework.daos.ConfigKeyDAO;
import org.ftd.educational.entityframework.daos.RotaDAO;
import org.ftd.educational.entityframework.entities.Rota;
import org.ftd.educational.web.mvc.abstracts.AbstractMVC;
import org.ftd.educational.web.mvc.abstracts.AbstractModelMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-30
 *
 */
public class RotaModel extends AbstractModelMVC implements IMVC {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    public static final String ENTITY_NAME = "Rota";

    @Override
    public String buildListModel(HttpServletRequest req) {
        req.setAttribute("app", DatasourceManager.getInstance().getConfigValue("app.name"));
        req.setAttribute("title", "Rotas MVC");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DYNAMNIC LABEL'S VIEW DEFINITIONS...
         */
        req.setAttribute("listDescriptionLabel", "Descrição: ( Papéis & Model & Ação & Controller & (1)Sucesso ou (2)Falha )");
        req.setAttribute("showBtnCreate", true);
        req.setAttribute("showBtnHome", true);
        
        /**
         * DATASOURCES...
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        req.setAttribute("datasource", doAdjustNameToListModel(dao.findRotaEntities()));

        ConfigKeyDAO cfgDAO = new ConfigKeyDAO(factory);
        String crudActions = cfgDAO.find("lst.crud.actions").getValue();
        String[] tmpActions = crudActions.split(";");
        List<IdNameAdapter> actions = new ArrayList<>();
        for (String action : tmpActions) {
            actions.add(new IdNameAdapter(action, action, true));
        }
        req.setAttribute("actions", actions);

        return "/WEB-INF/views/ListView.jsp";
    }

    @Override
    public String buildCreateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Criando Rota MVC");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ConfigKeyDAO cfgDAO = new ConfigKeyDAO(factory);
        String crudActions = cfgDAO.find("lst.crud.actions").getValue();
        String[] tmpActions = crudActions.split(";");
        List<IdNameAdapter> actions = new ArrayList<>();
        for (String action : tmpActions) {
            actions.add(new IdNameAdapter(action, action));
        }
        req.setAttribute("actions", actions);

        String processosNegocioCfg = cfgDAO.find("lst.business.process").getValue();
        String[] tmpProcessosNegocio = processosNegocioCfg.split(";");
        List<IdNameAdapter> processosNegocio = new ArrayList<>();
        for (String processo : tmpProcessosNegocio) {
            processosNegocio.add(new IdNameAdapter(processo, processo));
        }
        req.setAttribute("processosNegocio", processosNegocio);        
        
        return "/WEB-INF/views/RotaCreateView.jsp";
    }

    @Override
    public String buildReadModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Rota MVC");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        req.setAttribute("entity", dao.findRota(id));

        return "/WEB-INF/views/RotaReadView.jsp";
    }

    @Override
    public String buildUpdateModel(HttpServletRequest req) {
        req.setAttribute("app", "MVC Framework Admin Mode");
        req.setAttribute("title", "Atualizando Rota MVC");
        req.setAttribute("model", this.getClass().getSimpleName());
        req.setAttribute("controller", ENTITY_NAME + "Controller");

        /**
         * DATASOURCES...
         */
        Long id = Long.parseLong(AbstractMVC.readParameter(req, "id"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        req.setAttribute("entity", dao.findRota(id));

        ConfigKeyDAO cfgDAO = new ConfigKeyDAO(factory);
        String crudActions = cfgDAO.find("lst.crud.actions").getValue();
        String[] tmpActions = crudActions.split(";");
        List<IdNameAdapter> actions = new ArrayList<>();
        for (String action : tmpActions) {
            actions.add(new IdNameAdapter(action, action, true));
        }
        req.setAttribute("actions", actions);

        return "/WEB-INF/views/RotaUpdateView.jsp";
    }

    /* PRIVATE MEMBERS... */
    private List<ListNameAdapter> doAdjustNameToListModel(List<Rota> lst) {
        final String iconIniciar = "&nbsp;<span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\" style=\"color:blue\"></span>&nbsp;";
        final String iconProximo = "&nbsp;<span class=\"glyphicon glyphicon-arrow-right\" aria-hidden=\"true\" style=\"color:blue\"></span>&nbsp;";
        final String iconSucesso = "&nbsp;<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\" style=\"color:green\"></span>&nbsp;";
        final String iconFalha = "&nbsp;<span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\" style=\"color:red\"></span>&nbsp;";
        final String iconSeguranca = "<span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\" style=\"color:blue\"></span>&nbsp;";
        List<ListNameAdapter> lstAdapted = new ArrayList<>();
        for (Rota o : lst) {
            lstAdapted.add(
                    new ListNameAdapter(
                            o.getId(),
                            DatasourceManager.getInstance().getRuleNames(o.getPermissoesId())
                            + iconIniciar + o.getRecurso()
                            + iconProximo + o.getAcao()
                            + iconProximo + o.getTela()
                            + iconProximo + o.getControlador()
                            + iconSucesso + "(1)" + o.getAcaoSucesso()
                            + iconFalha + "(2)" + o.getAcaoFalha()

                    )
            );
        }

        return lstAdapted;
    }

}
