package application;

import java.util.Scanner;

import dao.UsuarioDao;
import entities.Usuario;

public class Main {

	public static void menu() {
		System.out.println("\n-----MENU-----");
		System.out.println("1) Cadastrar usuário");
		System.out.println("2) Realizar login");
		System.out.println("3) Deletar usuário");
		System.out.println("4) Editar e-mail cadastrado");
		System.out.println("0) SAIR");
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		boolean sair = false;
		int opcao;
		while (!sair) {
			menu();
			System.out.print("\nDigite uma opção: ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 0:
				if(UsuarioDao.encerrarConexões())
					System.out.println("Sistema fechado!");
				sair = true;
				break;
			case 1:
				System.out.print("Digite seu nome: ");
				String nome = console.nextLine();
				System.out.print("Digite seu login: ");
				String login = console.nextLine();
				System.out.print("Digite sua senha: ");
				String senha = console.nextLine();
				System.out.print("Digite seu email: ");
				String email = console.nextLine();
				Usuario u = new Usuario(nome, login, senha, email);
				if(UsuarioDao.cadastrarUsuario(u))
					System.out.println("Cadastro realizado!");
				break;
			case 2:
				System.out.print("Digite seu login: ");
				String login2 = console.nextLine();
				System.out.print("Digite sua senha: ");
				String senha2 = console.nextLine();
				if(UsuarioDao.login(login2, senha2))
					System.out.println("Login realizado!");
				break;
			case 3:
				System.out.print("Digite seu login: ");
				String login3 = console.nextLine();
				System.out.print("Digite sua senha: ");
				String senha3 = console.nextLine();
				System.out.print("Tem certeza que deseja deletar este usuário? (S / N): ");
				String deletar = console.nextLine();
				if(deletar.charAt(0) == 'S' || deletar.charAt(0) == 's') {
					if(UsuarioDao.deletarUsuario(login3, senha3))
						System.out.println("Usuário deletado!");
				} else {
					System.out.println("Operação de exclusão cancelada!");
					menu();
				}
				break;
			case 4:
				System.out.print("Digite seu login: ");
				String login4 = console.nextLine();
				System.out.print("Digite sua senha: ");
				String senha4 = console.nextLine();
				System.out.print("Digite o novo e-mail: ");
				String email2 = console.nextLine();
				if(UsuarioDao.editarEmailUsuario(login4, senha4, email2))
					System.out.println("E-mail alterado!");
				break;
			default:
				System.out.println("Opção inválida, escolha novamente:");
				break;
			}
		}
		console.close();
	}

}
