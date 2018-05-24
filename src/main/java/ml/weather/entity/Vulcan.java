package ml.weather.entity;

public class Vulcan extends Planet {

    public Vulcan(int angle) {
	super(angle, 1000);
    }

    @Override
    public int getDistance() {
	return 1000;
    }

    @Override
    public int getAngularVelocity() {
	return -5;
    }

}
