package ml.weather.entity;

public final class Sun extends Planet {

    public Sun() {
	super(0, 0);
    }

    @Override
    public int getDistance() {
	return 0;
    }

    @Override
    public int getAngularVelocity() {
	return 0;
    }

}
