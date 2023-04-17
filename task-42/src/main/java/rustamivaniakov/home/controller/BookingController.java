package rustamivaniakov.home.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class BookingController extends ServletHolder {

    static HttpServlet httpServlet = new InternalHttpServlet();

    public BookingController() {
        super(httpServlet);
    }

    @WebServlet("/booking/*")
    private static class InternalHttpServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doPost(req, resp);
        }
    }
}
