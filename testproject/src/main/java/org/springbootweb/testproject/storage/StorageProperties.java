package org.springbootweb.testproject.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
	
	// FOLDER LOCATION FOR STORING FILES
	
	private String location = "upload-dir";

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
