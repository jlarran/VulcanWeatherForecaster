package ml.weather.entity;

public abstract class Planet {

    public int angle;

    public int x;

    public int y;

    public Planet(int angle, int distance) {
	this.angle = angle;
	calculatePosition(distance);
    }

    public abstract int getDistance();

    public abstract int getAngularVelocity();

    public void rotate(int days) {
	// Rotate to the relative angle
	int rotationAngle = (getAngularVelocity() * days) % 360;
	angle += rotationAngle;
	angle %= 360;

	if (angle < 0) {
	    angle += 360;
	}

	// Calculate x and y
	calculatePosition(getDistance());
    }

    public void rotate() {
	rotate(1);
    }

    private void calculatePosition(int distance) {
	// Calculate the position with the angle and the distance (radius)
	x = (int) (distance * Math.cos(Math.toRadians(angle)));
	y = (int) (distance * Math.sin(Math.toRadians(angle)));
    }
    
    public int getDays(int yearsFromStart, int daysFromStart) {
	return yearsFromStart * 360 / Math.abs(getAngularVelocity()) + daysFromStart;
    }

    @Override
    public String toString() {
	StringBuilder toString = new StringBuilder("{ angle: ");
	toString.append(angle);
	toString.append(", x: ").append(x);
	toString.append(", y: ").append(y);
	toString.append(" }");

	return toString.toString();
    }
}
