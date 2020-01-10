package org.ftd.builderforce.easyservice.web.mvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.ftd.educational.entityframework.daos.RotaDAO;
import org.ftd.educational.entityframework.entities.Rota;
import org.ftd.educational.web.mvc.abstracts.AbstractControllerMVC;
import org.ftd.educational.web.mvc.interfaces.IMVC;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-31
 * 
 */
public class RotaController extends AbstractControllerMVC implements IMVC {
    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    
    @Override
    public String doCreate(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        try {
            Rota o = new Rota();
            o.setProcessoNegocio((readParameter(req, "processoNegocioInput")));
            o.setNomeGrupoMenu(readParameter(req, "grupoMenuInput"));
            o.setNomeMenu(readParameter(req, "nomeMenuInput"));
            o.setMostrarNoMenu(Boolean.parseBoolean(readParameter(req, "mostrarNoMenuCombo")));
            o.setRecurso(readParameter(req, "recursoInput"));
            o.setAcao(readParameter(req, "acaoCombo"));
            o.setTela(readParameter(req, "telaInput"));
            o.setControlador(readParameter(req, "controladorInput"));
            o.setAcaoSucesso(readParameter(req, "acaoSucessoCombo"));
            o.setAcaoFalha(readParameter(req, "acaoFalhaCombo"));
            o.setPermissoesId(readParameter(req, "permissoesIdInput"));
            o.setDicaTela(readParameter(req, "dicaTelaInput"));
            o.setObservacao(readParameter(req, "observacaoInput"));
            o.setBtnType(readParameter(req, "btnTypeInput"));
            o.setIcon(readParameter(req, "iconInput"));            
            dao.create(o);
            
            req.setAttribute("msg", "ID=" + o.getId() + " criado!");
            
            return "mvc?class=RotaModel&do=list";
            
        } catch (Exception e) {
            req.setAttribute("msg", "Criar o registro falhou!");
            
            return "mvc?class=RotaModel&do=create";
            
        }
    }

    @Override
    public String doUpdate(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        String id = readParameter(req, "id", "");
        try {
            Rota o = dao.findRota(Long.parseLong(id));
            o.setProcessoNegocio((readParameter(req, "processoNegocioInput")));
            o.setNomeGrupoMenu(readParameter(req, "grupoMenuInput"));
            o.setNomeMenu(readParameter(req, "nomeMenuInput"));
            o.setMostrarNoMenu(Boolean.parseBoolean(readParameter(req, "mostrarNoMenuCombo")));
            o.setSistema(Boolean.parseBoolean(readParameter(req, "sistemaCombo")));
            o.setRecurso(readParameter(req, "recursoInput"));
            o.setAcao(readParameter(req, "acaoCombo"));
            o.setTela(readParameter(req, "telaInput"));
            o.setControlador(readParameter(req, "controladorInput"));
            o.setAcaoSucesso(readParameter(req, "acaoSucessoCombo"));
            o.setAcaoFalha(readParameter(req, "acaoFalhaCombo"));
            o.setPermissoesId(readParameter(req, "permissoesIdInput"));
            o.setDicaTela(readParameter(req, "dicaTelaInput"));
            o.setObservacao(readParameter(req, "observacaoInput"));
            o.setBtnType(readParameter(req, "btnTypeInput"));
            o.setIcon(readParameter(req, "iconInput"));            
            dao.edit(o);
            
            req.setAttribute("msg", "ID=" + o.getId() + " atualizado!");
            
            return "mvc?class=RotaModel&do=list";
            
        } catch (Exception e) {
            req.setAttribute("msg", "Atualizar registro falhou!");
            
            return "mvc?class=RotaModel&do=update";
            
        }
    }

    @Override
    public String doDelete(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RotaDAO dao = new RotaDAO(factory);
        String id = readParameter(req, "id", "");
        try {
            Rota o = dao.findRota(Long.parseLong(id));
            if (o.getSistema()) {
                req.setAttribute("msg", "Dado de sistema não pode ser deletado!");
                return "mvc?class=RotaModel&do=read&id=" + id;                
            } else {
                dao.destroy(Long.parseLong(id));
                req.setAttribute("msg", "ID=" + id + " deletado!");
                return "mvc?class=RotaModel&do=list";
            }
        } catch (Exception e) {
            req.setAttribute("msg", "Apagar registro falhou! ");
            return "mvc?class=RotaModel&do=read";
        }
    }
    
}
