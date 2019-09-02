package sandbox.util;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sandbox.util.model.Funcionario;

public class Lambda {
	private static Logger logger = LogManager.getLogger(Lambda.class);
	List<Funcionario> funcionarios;
	List<String> sobreNomeFakes = Arrays.asList(" Oliveira", " Silva", " Pereira");
	
	public Lambda(List<Funcionario> funcs) {
		funcionarios = funcs;
	}
	
	
	/**
	 * Mapeia os nomes dos funcionários em uma lista de Strings com filtro para nulos.
	 * @author felipe.goliveira
	 * @return lista de Strings mapeadas.
	 */
	public List<String> mapToNomes(){
		List<String> nomeFunc = funcionarios.stream()
				.map(Funcionario::getNome).filter(Objects::nonNull)
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
		funcAlt.forEach(func -> logger.info(func));
		return funcAlt;
	}
	
	/**
	 * Exemplo de funcionamento da interface Consumer
	 * @return lista de funcionários.
	 */
	public List<Funcionario> functionalConsumer() {
		List<Funcionario> copiaFuncionarios = new ArrayList<>();
		copiaFuncionarios.addAll(funcionarios);
		Consumer<List<Funcionario>> modify = list -> list.forEach(item -> item.setNome(item.getNome() + sobreNomeFakes.get(getRandom(0, (sobreNomeFakes.size() - 1)))));
		logger.info("Teste de accept and then com nomes de funcionários modificados ------------");
		Consumer<List<Funcionario>> dispList = list -> list.stream()
				.forEach(a -> logger.info(a.getNome() + ", ")); 
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
	
	/**
	 * Mock para simular um processamento de 1 a 3 segundos.
	 * @return valor Integer aleatório de 1 a 10.
	 */
	public Integer mockProcesso() {
		Integer tempo = getRandom(1, 3);
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(tempo));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Gerando valor aleatório para simular resposta");
		return getRandom(1, 10);
	}
	
	/**
	 * Calcula o tempo gasto, em milisegundos para qualquer método passado para o Supplier.
	 * @param code
	 * @return o resultado do processamento
	 */
	public Integer recordTime(Supplier<Integer> code) {
        Instant start = Instant.now();
        // Supplier method .get() just invokes whatever it is passed
        Integer result = code.get();
        Instant end = Instant.now();

        Duration elapsed = Duration.between(start,end);
        logger.info("Computation took: " + elapsed.toMillis());

        return result;
	}
	
	/**
	 * Concatena os nome de todos os funcionários separados por virgula
	 * @return String com os nomes
	 */
	public String concatenaNomes() {
		String nomes = funcionarios.stream().map(Funcionario::getNome).collect(Collectors.joining(", "));
		logger.info("Nomes concatenados: " + nomes);
		return nomes;
	}
	

}
