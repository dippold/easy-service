package org.ftd.builderforce.easyservice.web.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.builderforce.easyservice.web.mvc.managers.DatasourceManager;
import org.ftd.educational.entityframework.entities.Profile;
import static org.ftd.educational.web.mvc.abstracts.AbstractMVC.readParameter;

/**
 *
 * @author ftd
 */
@WebFilter(filterName = "MainFilter",
        urlPatterns = {"/mvc", "/WEB-INF/**"})
public class MainFilter implements Filter {

    private static final boolean DEBUG = true;

    private FilterConfig filterConfig = null;

    public MainFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isUserAuthorized = false;

        String firewallMsg = "";

        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            String userName = (String) session.getAttribute("userName");
            String userCompanyId = (String) session.getAttribute("userCompannyId");
            String userProfileIds = (String) session.getAttribute("userProfileIds");
            String userProfileId = (String) session.getAttribute("userProfileId");
            String userProfileName = (String) session.getAttribute("userProfileName");
            if ((userId != null)
                    && (!userId.equals(""))
                    && (userName != null)
                    && (!userName.equals("")
                    && (!userProfileId.equals("")
                    && (!userProfileName.equals("")
                    && (!userProfileIds.equals("")))))) {

                // VALIDA SE O USUÁRIO SOLICITOU UMA MUDANÇA DE PROFILE (PERFIL PARAMETER) ...
                String newProfileId = readParameter(req, "perfil", "");
                if (!newProfileId.equals("")) {
                    if (isValidaProfile(newProfileId, userProfileIds)) {
                        Profile newProfile = DatasourceManager.getInstance().getProfile(Long.parseLong(newProfileId));
                        killSession(session);
                        session = req.getSession(true);
                        session.setAttribute("userId", userId);
                        session.setAttribute("userName", userName);
                        session.setAttribute("userCompannyId", userCompanyId);
                        session.setAttribute("userProfileIds", userProfileIds);
                        session.setAttribute("userProfileId", Long.toString(newProfile.getId()));
                        session.setAttribute("userProfileName", newProfile.getName());
                        isUserAuthorized = true;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Firewall: Perfil id=");
                        sb.append(newProfileId);
                        sb.append(" inválido!(1)");
                        firewallMsg = sb.toString();
                    }
                } else {
                    if (isValidaProfile(userProfileId, userProfileIds)) {
                        isUserAuthorized = true;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Firewall: Perfil id=");
                        sb.append(newProfileId);
                        sb.append(" inválido!(2)");
                        firewallMsg = sb.toString();
                    }
                }
            } else {
                firewallMsg = "Firewall: Sessão inválida! Logue-se novamente.";
            }
        }

        if (isUserAuthorized) {
            if (DEBUG) {
                System.out.println(firewallMsg);
            }
            chain.doFilter(request, response);
        } else {
            if (DEBUG) {
                System.out.println(firewallMsg);
            }
            request.setAttribute("msg", firewallMsg);
            req.getRequestDispatcher("signin.jsp").forward(req, res);
        }

    }

    private boolean isValidaProfile(String profileId, String profilesIds) {
        boolean result = false;
        String[] ids = profilesIds.split(";");
        for (String id : ids) {
            if (id.equals(profileId)) {
                result = true;
                break;
            }
        }

        return result;
    }

    private void killSession(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userCompanyId");
        session.removeAttribute("userProfileIds");
        session.removeAttribute("userProfileId");
        session.removeAttribute("userProfileName");
        session.invalidate();
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
                log("MainFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MainFilter()");
        }
        StringBuilder sb = new StringBuilder("MainFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
