package Object;

@SuppressWarnings("serial")
public class SolarBuilding extends Building {
	
	private int powerGenerated;
	

	public SolarBuilding(int powerGenerated) {
		super();
		this.powerGenerated = powerGenerated;
	}
	
	public SolarBuilding() {
		
	}

	public int getPowerGenerated() {
		return powerGenerated;
	}

	public void setPowerGenerated(int powerGenerated) {
		this.powerGenerated = powerGenerated;
	}

	@Override
	public String getType() {
		return "Solar Building";
	}
	
	@Override
	public String toString() {
		return "t:SolarBuilding [powerGenerated=" + powerGenerated + ", getxPosition()=" + getXPosition() + ", getyPosition()=" + getYPosition() + ", getName()="
				+ getName() + "]";
	}
	
	
	

	

}
