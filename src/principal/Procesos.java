package principal;

public class Procesos {
	
	private int id;
	private String name;
	private String status;
	private int space;
	
	public Procesos(int id,String name,String status,int space) {
		this.id=id;
		this.name=name;
		this.status=status;
		this.setSpace(space);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	

}
