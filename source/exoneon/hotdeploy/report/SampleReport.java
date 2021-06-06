package exoneon.hotdeploy.report;

import exoneon.hotdeploy.Report;
import exoneon.hotdeploy.ReportManager;

public class SampleReport extends Report {

	public SampleReport() {
		ReportManager.register(this.getClass().getSimpleName(), this);
	}

	public String describe() {
		return "Sample Report number";
	}

}
