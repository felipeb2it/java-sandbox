package sandbox.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sandbox.util.model.Funcionario;

public class ClienteRel {

	private String path; //Caminho base
	private String pathToReport; // Caminho completo com arquivo
	
	public static final String JASPER_FILE = "Leaf_Green.jrxml";
	public static final String REPORT_FILE = "Financeiro.pdf";
	public static final String RELATIVE_LOCATION = "/reports/";
	
	//Recupera os caminhos para que a classe possa encontrar os relat�rios
	public ClienteRel() {
		this.path = getClass().getResource(RELATIVE_LOCATION).getPath();
		this.pathToReport = path + JASPER_FILE;
		
	}
	
	//Imprime/gera uma lista de Clientes
	public void imprimir(List<Funcionario> clientes) throws Exception	
	{
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReport());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("imagesDir", path);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(clientes));
 
		JasperExportManager.exportReportToPdfFile(print, "c:/Projetos/" + REPORT_FILE);		
	}
 
	public String getPathToReport() {
		return this.pathToReport;
	}
	
	public String getPath() {
		return this.path;
	}
	
}
