package game.components;

public class ModuleWrapper {
	String name;
	int moduleNumber;
	Module mod;
	
	public ModuleWrapper(Module m, String n) {
		this.name = n;
		this.mod = m;
		this.moduleNumber = 0;
	}
	public ModuleWrapper(Module m, String n, int num) {
		this.name = n;
		this.mod = m;
		this.moduleNumber = num;
	}
	
	public void decrementNum() {
		this.moduleNumber -= 1;
	}
	
	public Integer getNumber() {
		return this.moduleNumber;
	}
	
	
}