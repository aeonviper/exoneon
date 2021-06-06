package exoneon.hotdeploy;

import java.util.Scanner;

public class HotDeployRunner {

	public static void main(String[] args) {
		new HotDeployRunner().run();
	}

	void run() {
		ReportManager.refresh();
		Scanner scanner = new Scanner(System.in);

		String line;
		while ((line = scanner.nextLine()) != null) {
			if ("refresh".contentEquals(line)) {
				ReportManager.refresh();
			} else if ("list".contentEquals(line)) {
				for (String reportType : ReportManager.getReportTypeList()) {
					Report report = ReportManager.handlerFor(reportType);
					System.out.println("ReportType: " + reportType + " -> " + report.describe());
				}
			} else if ("quit".contentEquals(line)) {
				break;
			}
		}
	}

}
