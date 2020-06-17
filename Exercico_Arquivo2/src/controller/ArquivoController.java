package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import interfaces.IArquivosController;
import model.entity.Pessoa;

public class ArquivoController implements IArquivosController {

	@Override
	public void verifDir() throws IOException {
		String path = "C:\\TEMP";
		File dir = new File(path);
		if (!dir.exists() || !dir.isDirectory()) {
			if (dir.mkdir()) {
				System.out.println("Diretorio criado ");
			} else {
				System.out.println("faio");
			}
		}
	}

	@Override
	public boolean verifRegistro() throws IOException {
		String path = "C:\\TEMP\\registro.csv";
		File file = new File(path);
		return file.exists();
	}

	@Override
	public void mostrarCadastro() throws IOException {
		verifDir();
		String path = "C:\\TEMP\\registro.csv";
		File file = new File(path);
		if (verifRegistro()) {
			FileInputStream stream = new FileInputStream(file);
			InputStreamReader flow = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(flow);
			String line = reader.readLine();
			StringBuffer buffer = new StringBuffer();
			line = reader.readLine();
			while (line != null) {
				String[] auxs = line.split(";");
				buffer.append("Código: " + auxs[0] + " |Nome: " + auxs[1] + " |E-mail: " + auxs[2]);
				buffer.append("\n");
				line = reader.readLine();
			}
			reader.close();
			flow.close();
			stream.close();
			String mostra = buffer.toString();
			JOptionPane.showMessageDialog(null, mostra);

		} else {
			System.err.println("Nao existe cadastros");
		}
	}

	@Override
	public Fila salvar(Fila fila) throws IOException {
		verifDir();
		String path = "C:\\TEMP\\registro.csv";
		File file = new File(path);
		if (verifRegistro()) {
			String save = preparar(fila);
			FileWriter writer = new FileWriter(file, true);
			PrintWriter printer = new PrintWriter(writer);
			printer.write(save);
			printer.flush();
			printer.close();
			writer.close();
		} else {
			String save = "Codigo;Nome;Email\n";
			save += preparar(fila);
			FileWriter writer = new FileWriter(file);
			PrintWriter printer = new PrintWriter(writer);
			printer.write(save);
			printer.flush();
			printer.close();
			writer.close();
		}
		return fila;
	}

	@Override
	public Fila cadastrar(Fila fila) {
		Pessoa data = registrarPessoa();
		fila.adicionar(data);
		return null;
	}

	private String preparar(Fila fila) {
		Pessoa pessoa = fila.remover();
		StringBuffer buffer = new StringBuffer();
		String preparo;
		do {
			buffer.append(pessoa.getCod() + ";" + pessoa.getName() + ";" + pessoa.getEmail());
			buffer.append("\n");
			pessoa = fila.remover();
		} while (pessoa != null);
		preparo = buffer.toString();
		return preparo;
	}

	private Pessoa registrarPessoa() {
		Pessoa data = new Pessoa();
		int cod = Integer.parseInt(JOptionPane.showInputDialog("Inssira o codigo da pessoa:"));
		String nome = JOptionPane.showInputDialog("Insira o nome da pessoa:");
		String email = JOptionPane.showInputDialog("Inssira o email da pessoa:");
		data.setCod(cod);
		data.setName(nome);
		data.setEmail(email);
		return data;
	}

}
