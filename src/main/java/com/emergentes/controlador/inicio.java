
package com.emergentes.controlador;

import com.emergentes.dao.PostDAO;
import com.emergentes.dao.PostDAOimpl;
import com.emergentes.modelo.Post;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO dao = new PostDAOimpl();
            int id;
            Post post = new Post();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch(action){
                case "add":
                    request.setAttribute("post", post);
                    request.getRequestDispatcher("frmpost.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    post = dao.getById(id);
                    System.out.println(post);
                    request.setAttribute("post", post);
                    request.getRequestDispatcher("frmpost.jsp").forward(request, response);
                    break;
                case "delete":
                    id =Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/inicio");
                    break;
                case "view":
                    List<Post> lista = dao.getAll();
                    request.setAttribute("posts", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error "+ ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fechaStr = request.getParameter("fecha");
        Date fecha = Date.valueOf(fechaStr);
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        
        Post post = new Post();
        post.setId(id);
        post.setFecha(fecha);
        post.setTitulo(titulo);
        post.setContenido(contenido);
        
        if (id==0) {
            PostDAO dao = new PostDAOimpl();
            try {
                dao.insert(post);
            } catch (Exception ex) {
                System.out.println("Error "+ex.getMessage());
            }
                response.sendRedirect(request.getContextPath()+"/inicio");
        } else {
            try {
                PostDAO dao = new PostDAOimpl();
                dao.update(post);
                response.sendRedirect(request.getContextPath()+"/inicio");
            } catch (Exception ex) {
                System.out.println("Error "+ex.getMessage());
            }
        }
    }

}
