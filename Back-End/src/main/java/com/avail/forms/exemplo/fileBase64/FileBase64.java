package com.avail.forms.exemplo.fileBase64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.avail.forms.exemplo.utils.BaseEntity;

@Entity
@Table(name = "arquivo_base_64")
public class FileBase64 extends BaseEntity<Integer>{
	
	private static final long serialVersionUID = 1L;
	
	Integer filesize;
	String filetype;	
	String filename;
	@Column(columnDefinition="LONGBLOB")
    private byte[] base64;
    
	public Integer getFilesize() {
		return filesize;
	}
	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getBase64() {
		return base64;
	}
	public void setBase64(byte[] base64) {
		this.base64 = base64;
	}
}
