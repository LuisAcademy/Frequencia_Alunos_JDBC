package Entidades;

public class Pessoa {
	private String nome;
	private int dataNasc;
	private int cpf;

	public Pessoa(String nome, int dataNasc, int cpf) {
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(int dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
}
