package br.csi.model;
import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

import java.sql.*;

public class Teste {
    public static void main(String[] args) {

      UsuarioDAO dao = new UsuarioDAO();

        Usuario u1 = new Usuario("fulano@gmail.com", "ekjdkd", true);
        Usuario u2 = new Usuario("alberta@gmail.com", "ekjei989kd", true);

        dao.inserirUsuario(u1);
        dao.inserirUsuario(u2);

      for(Usuario u : dao.getUsuarios()) {
          printUsuario(u);
      }

    }

    public static void printUsuario(Usuario usuario) {
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getSenha());
        System.out.println(usuario.getId());
    }
}
