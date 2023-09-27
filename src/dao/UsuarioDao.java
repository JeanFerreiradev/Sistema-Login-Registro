package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import entities.Usuario;

public class UsuarioDao {
	static Connection connection = Conexao.getConexao();
	static PreparedStatement ps;
	static ResultSet rs;

	public static boolean cadastrarUsuario(Usuario usuario) {
		String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, EMAIL) VALUES (?, ?, ?, ?)";
		ps = null;

		try {

			ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getEmail());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao realizar cadastro / " + e.getMessage());
			return false;
		}
	}

	public static boolean login(String login, String senha) {
		String sql = "SELECT login, senha FROM USUARIO WHERE login = ? AND senha = ?";
		ps = null;
		rs = null;

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			rs = ps.executeQuery();

			return rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao realizar login / " + e.getMessage());
			return false;
		}
	}

	public static boolean deletarUsuario(String login, String senha) {
		String sql = "DELETE FROM USUARIO WHERE login = ? AND senha = ?";
		ps = null;

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao deletar usuário / " + e.getMessage());
			return false;
		}
	}

	public static boolean editarEmailUsuario(String login, String senha, String email) {
		String sql = "UPDATE USUARIO SET email = ? WHERE login = ? AND senha = ?";
		ps = null;

		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, login);
			ps.setString(3, senha);
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar email / " + e.getMessage());
			return false;
		}
	}
	
	public static boolean encerrarConexões() {
		try {
			if (connection != null)
				connection.close();
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao encerrar as conexões / " + e.getMessage());
			return false;
		}
	}

}
