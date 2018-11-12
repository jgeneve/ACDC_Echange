package com.acdc.cnoyel;

public class Git {
	
	private String username;
	private String password;
	private String githubRepo;
	private String localRepo;
	
	public Git() {}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGithubRepo() {
		return this.githubRepo;
	}

	public void setGithubRepo(String githubRepo) {
		this.githubRepo = githubRepo;
	}

	public String getLocalRepo() {
		return this.localRepo;
	}

	public void setLocalRepo(String localRepo) {
		this.localRepo = localRepo;
	}
	
	/**
	 * Method used to save user's configuration into a local text file
	 * <b>Not implemented</b>
	 */
	public void saveGitConfig() {
		// TODO ?
	}
	
	/**
	 * Method used to load the user configuration from a local text file
	 * <b>Not implemented</b>
	 * @return a Git object stored on the user computer
	 */
	public Git loadGitConfig() {
		// TODO ?
		return null;
	}	
}
