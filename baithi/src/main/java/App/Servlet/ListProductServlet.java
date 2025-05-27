package App.Servlet;

@WebServlet("/list_products")
public class ListProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            req.setAttribute("productList", products);
            req.getRequestDispatcher("list_products.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }
}
