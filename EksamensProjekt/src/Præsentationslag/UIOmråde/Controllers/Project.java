package Præsentationslag.UIOmråde.Controllers;

import javafx.beans.property.SimpleStringProperty;

public class Project {
	private SimpleStringProperty projectName;
	
	public Project(String projectName) {
		this.projectName = new SimpleStringProperty(projectName);

	}
	
	public String getProjectName() {
        return projectName.get();
    }
}
