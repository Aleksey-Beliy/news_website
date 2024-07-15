package edu.web.news.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String category;
	private String content;
	private String imgPath;

	public News() {
	}
	
	public News(String title, String category, String content, String imgPath) {
		super();
		this.title = title;
		this.category = category;
		this.content = content;
		this.imgPath = imgPath;
	}
	
	public News(String title, String category, String content) {
		super();
		this.title = title;
		this.category = category;
		this.content = content;
	}

	public News(int id, String title, String category, String content, String imgPath) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
		this.imgPath = imgPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, content, id, imgPath, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(category, other.category) && Objects.equals(content, other.content) && id == other.id
				&& Objects.equals(imgPath, other.imgPath) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", category=" + category + ", content=" + content + ", imgPath="
				+ imgPath + "]";
	}

}
