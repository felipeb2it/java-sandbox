package sandbox.util.model;

public class Gerente extends Funcionario {
	private String celularCorp;

	public Gerente(String nome, Integer idade, String cpf) {
		super(nome, idade, cpf);
	}
	
	public String getCelularCorp() {
		return celularCorp;
	}

	public void setCelularCorp(String celularCorp) {
		this.celularCorp = celularCorp;
	}
}
