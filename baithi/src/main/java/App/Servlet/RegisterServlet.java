package App.Servlet;

import App.DATALISTEN;
import App.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            em.persist(user);
            em.getTransaction().commit();
            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            resp.getWriter().println("Registration error: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
