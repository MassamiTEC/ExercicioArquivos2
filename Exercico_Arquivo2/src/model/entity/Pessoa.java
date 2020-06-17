package model.entity;

public class Pessoa {
	private int cod;
	private String name;
	private String email;

	public Pessoa() {
		this.cod = 0;
		this.name = "";
		this.email = "";
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
