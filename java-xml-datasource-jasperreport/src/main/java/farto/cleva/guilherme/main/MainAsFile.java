package farto.cleva.guilherme.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class MainAsFile {

	private static final String RESOURCES_DIR = "src/main/resources/";

	private static final String REPORTS_DIR = RESOURCES_DIR + "reports/";

	private static final File MAIN_REPORT = new File(REPORTS_DIR + "main_report.jrxml");
	// private static final File MAIN_REPORT = new File(MainAsFile.class.getClassLoader().getResource("reports/main_report.jrxml").getFile());

	private static final File DATA = new File(RESOURCES_DIR + "data/dados1.xml");
	// private static final File DATA = new File(MainAsFile.class.getClassLoader().getResource("data/dados1.xml").getFile());

	private static final String OUTPUT_REPORT = RESOURCES_DIR + "output/main_report.pdf";

	public static void main(String[] args) {

		try {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("SUBREPORT_DIR", REPORTS_DIR);
			//			params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "dd/MM/yyyy");
			//			params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,###,##0.##");
			//			params.put(JRXPathQueryExecuterFactory.XML_LOCALE, new Locale("en","US"));

			JasperDesign jasperDesign = JRXmlLoader.load(MAIN_REPORT);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRXmlDataSource(JRXmlUtils.parse(DATA), "/root/alunos/aluno"));

			JasperExportManager.exportReportToPdfFile(jasperPrint, OUTPUT_REPORT);

			JasperViewer.viewReport(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
