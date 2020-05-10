package Object;

import java.io.Serializable;


/**
 * @author 201613026
 * building class
 */
@SuppressWarnings("serial")
public class Building implements Comparable<Building>, Serializable {

	private int xPosition;
	private int yPosition;
	private String type = "other";
	private String name;

	/**
	 * function to getXPosition
	 * @return
	 */
	public int getXPosition() {
		return xPosition;
	}

	/**
	 * function to setXPositon
	 * @param xPosition
	 */
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * function to getYPostion
	 * @return
	 */
	public int getYPosition() {
		return yPosition;
	}

	/**
	 * function to setYPosition
	 * @param yPostion
	 */
	public void setYPosition(int yPostion) {
		this.yPosition = yPostion;
	}
	
	/**
	 * function to getName
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * function to getType
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 *function to return a string for class
	 */
	@Override
	public String toString() {
		return "Building [xPosition=" + xPosition + ", yPosition=" + yPosition + ", name=" + name + "]";
	}

	/**
	 * function to setName
	 * @param name
	 */
	public void setName(String name) {
		this.name=name;
	}

	/**
	 *function for hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + xPosition;
		result = prime * result + yPosition;
		return result;
	}

	/**
	 *function to check if building is equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Building other = (Building) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (xPosition != other.xPosition)
			return false;
		if (yPosition != other.yPosition)
			return false;
		return true;
	}

	/**
	 *function to compare two building
	 */
	@Override
	public int compareTo(Building arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
