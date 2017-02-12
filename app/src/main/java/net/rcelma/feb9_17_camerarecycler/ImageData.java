package net.rcelma.feb9_17_camerarecycler;

public class ImageData {
	//Creation date of the image
	private String dateCreated;
	//Name of the image
	private String name;
	//Path of the image
	private String url;

	public ImageData(String dateCreated, String name, String url) {

		this.dateCreated = dateCreated;
		this.name = name;
		this.url = url;
	}

	public ImageData() {

	}

	public String getDateCreated() {

		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {

		this.dateCreated = dateCreated;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}
}