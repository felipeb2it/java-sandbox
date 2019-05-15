package sandbox.util.model;

public class Funcionario {
	private String nome;
	private Integer idade;
	private String cpf;
	
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", idade=" + idade + "]";
	}
	
	public Funcionario(String nome, Integer idade, String cpf) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
