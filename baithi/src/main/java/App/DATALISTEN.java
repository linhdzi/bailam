package App;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DATALISTEN implements ServletContextListener {
    private EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("BIKE");
        }
        sce.getServletContext().setAttribute("emf", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(sce.getServletContext().getAttribute("emf") != null) {
            sce.getServletContext().removeAttribute("emf");

        }
        if(emf != null) {
            emf.close();
        }
    }
}