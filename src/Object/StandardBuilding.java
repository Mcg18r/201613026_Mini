package Object;

public class StandardBuilding extends Building {
	
	private int powerUsed;
	private String type = "SolarBuilding";
	
	public StandardBuilding(int powerUsed) {
		super();
		this.powerUsed = powerUsed;
	}
	
	public StandardBuilding() {
		
	}

	public int getPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(int powerUsed) {
		this.powerUsed = powerUsed;
	}
	
	@Override
	public String getType() {
		return "Standard Building";
	}

	@Override
	public String toString() {
		return "StandardBuilding [powerUsed=" + powerUsed + ", getxPosition()=" + getXPosition() + ", getyPosition()="
				+ getYPosition() + ", getName()=" + getName() + "]";
	}
	

}
