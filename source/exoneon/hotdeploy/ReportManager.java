package exoneon.hotdeploy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ReportManager {

	private static Map<String, Report> reportMap = new TreeMap<>();
	public static final String reportListFilename = "reportList.txt";

	public static void refresh() {
		URL url = null;
		url = Thread.currentThread().getContextClassLoader().getResource(reportListFilename);
		if (url != null) {
			System.out.println(reportListFilename + " = " + url.getPath());
			reportMap = new TreeMap();
			ReportLoader reportLoader = new ReportLoader();
			for (String reportHandler : readFile(new File(url.getPath()))) {
				reportHandler = reportHandler.trim();
				if (!reportHandler.isEmpty()) {
					try {
						Class clazz = reportLoader.loadClass(reportHandler);
						clazz.newInstance();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void register(String reportType, Report report) {
		reportMap.put(reportType, report);
		System.out.println("ReportType: " + reportType + " loaded");
	}

	public static Report handlerFor(String reportType) {
		return reportMap.get(reportType);
	}

	public static Set<String> getReportTypeList() {
		return reportMap.keySet();
	}

	public static List<String> readFile(File file) {
		FileReader fileReader = null;
		BufferedReader reader = null;
		List<String> lines = new ArrayList<String>();
		try {
			String line = null;
			reader = new BufferedReader(fileReader = new FileReader(file));
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException eio) {
			eio.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return lines;
	}

}
