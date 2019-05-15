package sandbox.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sandbox.util.model.Funcionario;

public class Lambda {
	
	private static Logger logger = LogManager.getLogger(Lambda.class);
	List<Funcionario> funcionarios;
	
	public Lambda(List<Funcionario> funcs) {
		funcionarios = funcs;
	}
	
	
	/**
	 * Mapeia os nomes dos funcionários em uma lista de Strings.
	 * @author felipe.goliveira
	 * @return lista de Strings mapeadas.
	 */
	public List<String> mapToNomes(){
		List<String> nomeFunc = funcionarios.stream()
				.map(Funcionario::getNome)
				.collect(Collectors.toList());
		return nomeFunc;
	}
	
	/**
	 * Filtra a lista de funcionários por idade e ordena por nome
	 * @param idade
	 * @return lista de funcionários filtrada e ordenada
	 */
	public List<Funcionario> getIdadeAcimaDe(Integer idade){
		List<Funcionario> funcAlt = funcionarios.stream()
				.filter(funcionario -> funcionario.getIdade() > idade)
				.sorted(Comparator.comparing(Funcionario::getNome))
				.collect(Collectors.toList());
		return funcAlt;
	}
	
	/**
	 * Exemplo de funcionamento da interface Consumer
	 * @return lista de funcionários.
	 */
	public List<Funcionario> functionalConsumer() {
		List<Funcionario> copiaFuncionarios = new ArrayList<>();
		copiaFuncionarios.addAll(funcionarios);
		Consumer<List<Funcionario>> modify = list -> list.forEach(item -> item.setNome(item.getNome() + " X"));
		logger.info("Teste de accept and then com nomes de funcionários modificados ------------");
		Consumer<List<Funcionario>> dispList = list -> list.stream()
				.forEach(a -> System.out.print(a.getNome() + ", ")); 
		modify.andThen(dispList).accept(copiaFuncionarios);
		// modify.accept(copiaFuncionarios);
		return copiaFuncionarios;
	}
	
	/**
	 * Gera um número aleatório com os limites de parâmetros min e max.
	 * @param min
	 * @param max
	 * @return valor inteiro no intervalo.
	 */
	public Integer getRandom(int min, int max) {
		Supplier<Integer> randomValue = () -> {
			Random r = new Random();
			return r.ints(min,(max+1)).findFirst().getAsInt();
		};
		return randomValue.get();
	}
	

}
