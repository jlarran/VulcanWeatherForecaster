package ml.weather.entity;

public class Betasoid extends Planet {

    public Betasoid(int angle) {
	super(angle, 2000);
    }

    @Override
    public int getDistance() {
	return 2000;
    }

    @Override
    public int getAngularVelocity() {
	return 3;
    }

}
