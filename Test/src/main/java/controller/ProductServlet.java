package controller;

import model.Category;
import model.Product;
import service.CategoryDAO;
import service.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action){
                case "create":
                    createGet(req, resp);
                    break;
                case "edit":
                    editGet(req, resp);
                    break;
                case "delete":
                    Delete(req, resp);
                case "search":
                    search(req, resp);
                    break;
                default:
                    listProduct(req, resp);

            }
        }

        private void search(HttpServletRequest req, HttpServletResponse resp) {
            List<Product> products = productDAO.searchByName("%" + new String(req.getParameter("search").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) + "%");
            req.setAttribute("products", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        private void Delete(HttpServletRequest req, HttpServletResponse resp) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                productDAO.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Product> products = productDAO.selectAll();
            req.setAttribute("products", products);
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        private void editGet(HttpServletRequest req, HttpServletResponse resp) {
            List<Category> categories = categoryDAO.selectAll();
            req.setAttribute("category", categories);
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productDAO.select(id);
            req.setAttribute("product", product);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/edit.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }

        }
        private void createGet(HttpServletRequest req, HttpServletResponse resp) {
            List<Category> categories = categoryDAO.selectAll();
            req.setAttribute("category", categories);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/create.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Product> products = productDAO.selectAll();
            req.setAttribute("products", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            requestDispatcher.forward(req, resp);
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action){
                case "create":
                    createPost(req, resp);
                    break;
                case "edit":
                    editPost(req, resp);
                    break;
            }
        }
        private void editPost(HttpServletRequest req, HttpServletResponse resp) {
            RequestDispatcher requestDispatcher;
            int id = Integer.parseInt(req.getParameter("id"));
            String name = new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String color = req.getParameter("color");
            String describeProduct = new String(req.getParameter("describe").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            int idCategory = Integer.parseInt(req.getParameter("category"));
            try{
                Product product = new Product(id, name, price, quantity, color, describeProduct);
                productDAO.edit(product,idCategory);
                List<Product> products = productDAO.selectAll();
                req.setAttribute("products", products);

            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }


        private void createPost(HttpServletRequest req, HttpServletResponse resp) {
            String name = req.getParameter("name");
            double price =Double.parseDouble( req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String color = req.getParameter("color");
            String describe = req.getParameter("describe");
            int idCategory = Integer.parseInt(req.getParameter("category"));
            Product product = new Product(name, price, quantity, color, describe);

            try {
                productDAO.insert(product, idCategory);
                boolean check = true;
                List<Product> products = productDAO.selectAll();
                req.setAttribute("check", check);
                req.setAttribute("products", products);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
                requestDispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ServletException | IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @WebServlet(urlPatterns = "/products")
    public class ProductServlet extends HttpServlet {

        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action){
                case "create":
                    createGet(req, resp);
                    break;
                case "edit":
                    editGet(req, resp);
                    break;
                case "delete":
                    Delete(req, resp);
                case "search":
                    search(req, resp);
                    break;
                default:
                    listProduct(req, resp);

            }
        }

        private void search(HttpServletRequest req, HttpServletResponse resp) {
            List<Product> products = productDAO.searchByName("%" + new String(req.getParameter("search").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) + "%");
            req.setAttribute("products", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        private void Delete(HttpServletRequest req, HttpServletResponse resp) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                productDAO.delete(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Product> products = productDAO.selectAll();
            req.setAttribute("products", products);
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        private void editGet(HttpServletRequest req, HttpServletResponse resp) {
            List<Product> categories = productDAO.selectAll();
            req.setAttribute("category", categories);
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productDAO.select(id);
            req.setAttribute("product", product);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/edit.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }

        }
        private void createGet(HttpServletRequest req, HttpServletResponse resp) {
            List<Category> categories = CategoryDAO.;
            req.setAttribute("category", categories);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/create.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Product> products = productDAO.selectAll();
            req.setAttribute("products", products);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            requestDispatcher.forward(req, resp);
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action){
                case "create":
                    createPost(req, resp);
                    break;
                case "edit":
                    editPost(req, resp);
                    break;
            }
        }
        private void editPost(HttpServletRequest req, HttpServletResponse resp) {
            RequestDispatcher requestDispatcher;
            int id = Integer.parseInt(req.getParameter("id"));
            String name = new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String color = req.getParameter("color");
            String describeProduct = new String(req.getParameter("describe").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            int idCategory = Integer.parseInt(req.getParameter("category"));
            try{
                Product product = new Product(id, name, price, quantity, color, describeProduct);
                productDAO.update(product,idCategory);
                List<Product> products = productDAO.selectAll();
                req.setAttribute("products", products);

            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            requestDispatcher = req.getRequestDispatcher("product/view.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }


        private void createPost(HttpServletRequest req, HttpServletResponse resp) {
            String name = req.getParameter("name");
            double price =Double.parseDouble( req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String color = req.getParameter("color");
            String describe = req.getParameter("describe");
            int idCategory = Integer.parseInt(req.getParameter("category"));
            Product product = new Product(name, price, quantity, color, describe);

            try {
                productDAO.insert(product, idCategory);
                boolean check = true;
                List<Product> products = productDAO.selectAll();
                req.setAttribute("check", check);
                req.setAttribute("products", products);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/view.jsp");
                requestDispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ServletException | IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
