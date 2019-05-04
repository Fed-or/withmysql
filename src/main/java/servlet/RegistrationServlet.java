package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final UserDao USERDAO = UserDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");


        if (USERDAO.getUser(name) != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signin.jsp?message=error");
            requestDispatcher.forward(req, resp);
        }
        Long id = UserDao.getInstance().getId(name);
        boolean newUser = USERDAO.addUser(new User(id, name, password));
        if (newUser) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("afterregistration.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}