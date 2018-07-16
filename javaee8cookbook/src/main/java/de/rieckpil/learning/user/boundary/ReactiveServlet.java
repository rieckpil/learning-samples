package de.rieckpil.learning.user.boundary;

import de.rieckpil.learning.user.control.UserService;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReactiveServlet", urlPatterns = {"/ReactiveServlet"}, asyncSupported = true)
public class ReactiveServlet extends HttpServlet {

    @Inject
    private UserService userService;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ctx = req.startAsync();
        ctx.start(() -> {
            try (PrintWriter writer = ctx.getResponse().getWriter()) {
                writer.write(jsonb.toJson(userService.getUser()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            ctx.complete();
        });
    }

    @Override
    public void destroy() {
        try {
            jsonb.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
