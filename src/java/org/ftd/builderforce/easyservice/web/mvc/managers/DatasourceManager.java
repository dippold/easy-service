package org.ftd.builderforce.easyservice.web.mvc.managers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.ftd.builderforce.easyservice.web.adapters.IdNameAdapter;
import org.ftd.educational.entityframework.daos.CompanyDAO;
import org.ftd.educational.entityframework.daos.ConfigKeyDAO;
import org.ftd.educational.entityframework.daos.ProfileDAO;
import org.ftd.educational.entityframework.daos.RotaDAO;
import org.ftd.educational.entityframework.daos.RuleDAO;
import org.ftd.educational.entityframework.daos.UserDAO;
import org.ftd.educational.entityframework.entities.Company;
import org.ftd.educational.entityframework.entities.Profile;
import org.ftd.educational.entityframework.entities.Rota;
import org.ftd.educational.entityframework.entities.Rule;
import org.ftd.educational.entityframework.entities.User;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-09-01
 *
 */
public class DatasourceManager {

    public static final String PERSISTENCE_UNIT_NAME = "entityframeworkPU";
    private static final DatasourceManager INSTANCE;

    static {
        INSTANCE = new DatasourceManager();
    }

    public static DatasourceManager getInstance() {
        return INSTANCE;
    }

    private DatasourceManager() {
    }
   
    
    public List<Rota> getRotasActiveProfile(HttpServletRequest req) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        // BUSCAR PERFIL
        HttpSession session = req.getSession(false);
        String profileId = (String) session.getAttribute("userProfileId");
        ProfileDAO profileDAO = new ProfileDAO(factory);
        Profile profile = profileDAO.find(Long.parseLong(profileId));
        
        // BUSCAR RULE IDS DO PERFIL
        String userRuleIds[] = profile.getRoleIds().split(";");
        
        // BUSCAR ROTAS DE MENU
        RotaDAO rotaDAO = new RotaDAO(factory);
        List<Rota> rotas = rotaDAO.findRotaMenu();
        
        // BUSCAR ROTAS DE CADA RULE
        List<Rota> rotasPerfil = new ArrayList<>();
        for (Rota rota:rotas) {
            String[] rotaRuleIds = rota.getPermissoesId().split(";");
            if (thereIsSimilar(rotaRuleIds, userRuleIds)) {
                rotasPerfil.add(rota);
            }
        }
        
        return rotasPerfil;
    }
    
    public boolean thereIsSimilar(String[] lst1, String[] lst2) {
        boolean result = false;
        for (int i=0; i<lst1.length; i++) {
            for (int j=0; j<lst2.length; j++) {
                if (lst1[i].equals(lst2[j])) {
                   result = true;
                   break;
                }
            }
        }
        
        return result;
    }    
    
    public List<IdNameAdapter> getBusinessProcess(List<Rota> rotas) {
        List<IdNameAdapter> lst = new ArrayList<>();
        for (Rota rota:rotas) {
            if (isNew(rota.getProcessoNegocio(),lst)) {
                lst.add(new IdNameAdapter(Long.toString(rota.getId()), rota.getProcessoNegocio()));
            }
        }
        
        return lst;
    }
    
    private boolean isNew(String name, List<IdNameAdapter> lst) {
        boolean result = true;
        for (IdNameAdapter o:lst) {
            if (o.getName().equals(name)) {
                result = false;
                break;
            }
        }
        
        return result;
    }
    
    public User getUser(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);

        return dao.find(id);
    }

    public Profile getProfile(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);

        return dao.find(id);
    }    
    
    public Company getCompany(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        CompanyDAO dao = new CompanyDAO(factory);

        return dao.find(id);
    }

    public List<Company> getCompanies() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        CompanyDAO dao = new CompanyDAO(factory);

        return dao.findCompanyEntities();
    }

    public String getConfigValue(String configKey) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ConfigKeyDAO dao = new ConfigKeyDAO(factory);

        return dao.find(configKey).getValue();
    }

    public String getRuleNames(String ruleIds) {
        StringBuilder ruleNames = new StringBuilder();
        String[] ids = ruleIds.split(";");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RuleDAO dao = new RuleDAO(factory);
        for (int i = 0; i < ids.length; i++) {
            if (i != 0) {
                ruleNames.append(" ");
            }
            if (isNumeric(ids[i])) {
                Rule rule = dao.find(Long.parseLong(ids[i]));
                if (rule != null) {
                    if (rule.getId() == 1L) {
                        ruleNames.append("<span class=\"label label-warning\">");
                        ruleNames.append(rule.getName());
                        ruleNames.append("</span>");
                    } else {
                        ruleNames.append("<span class=\"label label-default\">");
                        ruleNames.append(rule.getName());
                        ruleNames.append("</span>");
                    }
                } else {
                    ruleNames.append("<span class=\"label label-info\">id=");
                    ruleNames.append(ids[i]);
                    ruleNames.append(" (ERRO: id não cadastrado!)</span>");
                }
            } else {
                if (ids[i].equals("*")) {
                    ruleNames.append("<span class=\"label label-danger\">Todos</span>");
                } else {
                    ruleNames.append("<span class=\"label label-info\">id=");
                    ruleNames.append(ids[i]);
                    ruleNames.append(" (ERRO: id não numérico!)</span>");
                }
            }
        }

        if (ids.length == 0) {
            ruleNames.append("(ERRO: Defina um papel!)");
        }

        return ruleNames.toString();
    }

    public String getProfileNames(String profileIds) {
        StringBuilder profileNames = new StringBuilder();
        String[] ids = profileIds.split(";");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        for (int i = 0; i < ids.length; i++) {
            if (i != 0) {
                profileNames.append(" ");
            }
            if (isNumeric(ids[i])) {
                Profile profile = dao.find(Long.parseLong(ids[i]));
                if (profile != null) {
                    if (profile.getId() == 1L) {
                        profileNames.append("<span class=\"label label-warning\">");
                        profileNames.append(profile.getName());
                        profileNames.append("</span>");
                    } else {
                        profileNames.append("<span class=\"label label-default\">");
                        profileNames.append(profile.getName());
                        profileNames.append("</span>");
                    }
                } else {
                    profileNames.append("<span class=\"label label-info\">id=");
                    profileNames.append(ids[i]);
                    profileNames.append(" (ERRO: id não cadastrado!)</span>");
                }
            } else {
                if (ids[i].equals("*")) {
                    profileNames.append("<span class=\"label label-danger\">Todos</span>");
                } else {
                    profileNames.append("<span class=\"label label-info\">id=");
                    profileNames.append(ids[i]);
                    profileNames.append(" (ERRO: id não numérico!)</span>");
                }
            }
        }
        if (ids.length == 0) {
            profileNames.append("(ERRO: Defina um Perfil!)");
        }

        return profileNames.toString();
    }

    public List<Profile> getProfiles(String profileIds) {
        List<Profile> profiles = new ArrayList<>();
        String[] ids = profileIds.split(";");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        ProfileDAO dao = new ProfileDAO(factory);
        for (String id : ids) {
            if (id.equals("*")) {
                profiles = dao.findProfileEntities();
                break;
            } else {
                Profile profile = dao.find(Long.parseLong(id));
                if (profile != null) {
                    profiles.add(profile);
                }
            }
        }

        return profiles;
    }

    public List<Rule> getRules(String ruleIds) {
        List<Rule> rules = new ArrayList<>();
        String[] ids = ruleIds.split(";");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        RuleDAO dao = new RuleDAO(factory);
        for (String id : ids) {
            if (id.equals("*")) {
                rules = dao.findAll();
                break;
            } else {
                Rule rule = dao.find(Long.parseLong(id));
                if (rule != null) {
                    rules.add(rule);
                }
            }
        }

        return rules;
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
