package sandbox.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sandbox.util.Lambda;
import sandbox.util.model.Analista;
import sandbox.util.model.Funcionario;
import sandbox.util.model.Gerente;

public class LambdaTest {
	
	private Lambda lambda = new Lambda(funcionarios);
	private static List<Funcionario> funcionarios = new ArrayList<>();
	// private static Optional<Integer> idadeMaxima;
	private final Integer idade = 20;
	
	@BeforeAll
	static void preparaLista() {
		funcionarios.add(new Funcionario("Paulo", 25, "123"));
		funcionarios.add(new Analista("José", 23, "653"));
		funcionarios.add(new Gerente("João", 27, "543"));
	}
	
	@Test
	void testListaOrdenadaIdadeAcimaDe() {
		List<Funcionario> list = lambda.getIdadeAcimaDe(idade);
		assertTrue(!list.stream().anyMatch(item -> item.getIdade() < idade));
	}
	
	@Test
	void testNomes() {
		List<String> nomes = lambda.mapToNomes();
		assertTrue(nomes.size() == funcionarios.size());
	}
	
	@Test
	void testConsumer() {
		List<Funcionario> list = lambda.functionalConsumer();
		assertTrue(!list.stream().anyMatch(item -> item.getNome().charAt((item.getNome().length() - 1)) != 'X'));
	}
	
	@Test
	void testSuplier() {
		assertTrue(lambda.getRandom(18, 35) != null);
	}
	
	@Test
	void testConcatena() {
		assertTrue(lambda.concatenaNomes().length() > funcionarios.get(0).getNome().length());
	}
	
	@Test
	void testRecordTime() {
		// StringSuppliers supp = lambda::mockProcesso;
		Integer result = lambda.recordTime(lambda::mockProcesso);
		System.out.println(result);
	}

}
