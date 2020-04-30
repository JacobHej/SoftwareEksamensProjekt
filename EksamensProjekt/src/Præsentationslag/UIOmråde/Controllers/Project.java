package Præsentationslag.UIOmråde.Controllers;

import javafx.beans.property.SimpleStringProperty;

public class Project {
	private String projectName;
	
	public Project(String projectName) {
		this.projectName = projectName;

	}
	
	public String getProjectName() {
        return projectName;
    }
	
	public void setProjectName(String name) {
		this.projectName = name;
	}
}
