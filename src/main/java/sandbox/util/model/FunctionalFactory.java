package sandbox.util.model;

public interface FunctionalFactory<A, B, C> {
	Funcionario factory(A nome, B idade, C cpf);
}
