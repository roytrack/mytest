package com.roytrack.svn;
import java.lang.reflect.Field;
import java.util.*;
public class SVN {
	String CommitMessage;
	Date Date; 
	String kind;
	String name;
	String repositoryRoot;
	long Revision;
	long size;
	String url;
	String Author;
	String State;
	public String toStrings() throws Exception{
		String result="";
		Field[] aa=SVN.class.getDeclaredFields();
		for(int i =0;i<aa.length;i++){
			result+=aa[i].getName()+"@@"+aa[i].get(this)+"\r\n";
		}
		return result;
	}
	public String getCommitMessage() {
		return CommitMessage;
	}
	public void setCommitMessage(String commitMessage) {
		CommitMessage = commitMessage;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRepositoryRoot() {
		return repositoryRoot;
	}
	public void setRepositoryRoot(String repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}
	public long getRevision() {
		return Revision;
	}
	public void setRevision(long revision) {
		Revision = revision;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	} 
	
	
}
