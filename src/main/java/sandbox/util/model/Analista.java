package sandbox.util.model;

public class Analista extends Funcionario {
	private Boolean alocadoCliente;

	public Analista(String nome, Integer idade, String cpf) {
		super(nome, idade, cpf);
	}

	public Boolean getAlocadoCliente() {
		return alocadoCliente;
	}

	public void setAlocadoCliente(Boolean alocadoCliente) {
		this.alocadoCliente = alocadoCliente;
	}
}
