package ml.weather.entity;

public class Ferengi extends Planet {

    public Ferengi(int angle) {
	super(angle, 500);
    }

    @Override
    public int getDistance() {
	return 500;
    }

    @Override
    public int getAngularVelocity() {
	return 1;
    }

}
