package org.ftd.builderforce.easyservice.web.mvc.managers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.ftd.educational.entityframework.daos.RotaDAO;
import org.ftd.educational.entityframework.entities.Rota;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-09-01
 * 
 */
public class MVCManager {
    private static final MVCManager INSTANCE;
    
    static {
        INSTANCE = new MVCManager();
    }
    
    public static MVCManager getInstance() {
        return INSTANCE;
    }
    
    private MVCManager() {
    }
    
    /* METODO UTILIZADO PELO "MODEL" PARA BUSCAR A "VIEW" DE CADA CRUD ACTION */
    public String findView(String model, String action, String persistenceUnitName) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        RotaDAO dao = new RotaDAO(factory);
        Rota rota = dao.findRotaByResource(model, action);
        
        return rota.getTela();
    }

    /* METODO UTILIZANDO PELO "CONTROLLER" PARA SABER A PROXIMA "AÇÃO" NO MODEL DIANTE DE SUCESSO */
    public Rota findByController(String controller, String action, String persistenceUnitName) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        RotaDAO dao = new RotaDAO(factory);
        
        return dao.findRotaByController(controller, action);
    }    
    
    
    /* METODO UTILIZANDO PELO "MODEL" PARA SABER A PROXIMA "VIEW" NO MODEL DIANTE DE UMA AÇÃO */
    public Rota findByModel(String model, String action, String persistenceUnitName) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        RotaDAO dao = new RotaDAO(factory);
        
        return dao.findRotaByResource(model, action);
    }

    /* METODO UTILIZANDO PELO "CONTROLLER" PARA SABER A PROXIMA "AÇÃO" NO MODEL DIANTE DE FALHA */
    public String findFaultAction(String controller, String action, String persistenceUnitName) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        RotaDAO dao = new RotaDAO(factory);
        Rota rota = dao.findRotaByController(controller, action);
        
        return rota.getAcaoFalha();
    }
    
}
