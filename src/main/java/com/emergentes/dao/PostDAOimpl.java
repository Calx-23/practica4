package com.emergentes.dao;

import com.emergentes.modelo.Post;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAOimpl extends ConexionDB implements PostDAO {

    @Override
    public void insert(Post post) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into posts(fecha,titulo,contenido) VALUES(?,?,?)");
            ps.setDate(1, post.getFecha());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getContenido());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Post post) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE posts set fecha = ?, titulo = ?, contenido = ? where id = ?");
            ps.setDate(1, post.getFecha());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getContenido());
            ps.setInt(4, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM posts WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Post getById(int id) throws Exception {
        Post post = new Post();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM posts WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                post.setId(rs.getInt("id"));
                post.setFecha(rs.getDate("fecha"));
                post.setTitulo(rs.getString("titulo"));
                post.setContenido(rs.getString("contenido"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return post;
    }

    @Override
    public List<Post> getAll() throws Exception {
        List<Post> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM posts");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Post>();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setFecha(rs.getDate("fecha"));
                post.setTitulo(rs.getString("titulo"));
                post.setContenido(rs.getString("contenido"));
                lista.add(post);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;

    }
    
}
