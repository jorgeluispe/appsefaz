package util;

/**
 *
 * @author Jorge Luis
 */
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class LoginFilter
        implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext facesContext = pe.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
        if (currentPage.contains("/restrito/")) {
            HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if ((sessao == null) || (Util.pegarObjetoDaSessao("usuarioLogado") == null)) {
                Util.redirecionarPagina("");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
