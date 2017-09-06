/**
 * The Planet class creates a planet object.
 */
public class Planet {
	public double xxPos; 		// current x position
	public double yyPos; 		// current y position
	public double xxVel; 		// current velocity in the x direction
	public double yyVel; 		// current velocity in the y direction
	public double mass;  		// mass
	public String imgFileName;  // filename for planet image

	/**
	 * The Default constructor initializes the Planet's current position, velocity, mass, and image filename
	 */
	public Planet(double xP, double yP, double xV,double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}	

	/**
	 * This constructor take in a Planet object and initialize an identical Planet object.
	 */
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/**
	 * Calculates the distance between two Planets
	 */
	public double calcDistance(Planet p) {
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Calculates the force exerted on this planet by the given planet
	 */
	public double calcForceExertedBy(Planet p) {
		return 6.67e-11 * p.mass * mass / (calcDistance(p) * calcDistance(p));
	}

	/**
	 * Calculates the force exerted in the X direction
	 */
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - xxPos;
		return calcForceExertedBy(p) * dx / calcDistance(p);

	}

	/**
	 * Calculates the force exerted in the Y direction
	 */
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - yyPos;
		return calcForceExertedBy(p) * dy / calcDistance(p);
	}

	

	/**
	 * Takes in an array of Planets and calculate the net X force exerted 
	 * by all planets in that array upon the current Planet
	 */
	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		for (int i = 0; i < planets.length; i++) {
			if( !(this.equals(planets[i])) ) {
				netForceX+=calcForceExertedByX(planets[i]);
			}
		}
			
		return netForceX;
	}

	/**
	 * Takes in an array of Planets and calculate the net Y force exerted 
	 * by all planets in that array upon the current Planet
	 */
	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		for (int i = 0; i < planets.length; i++) {
			if( !(this.equals(planets[i])) ) {
				netForceY+=calcForceExertedByY(planets[i]);
			}	
		}		
		return netForceY;
	}

	/**
	 * Calculates planet's new position and velocity
	 */
	public void update(double dt, double fX, double fY) {
		// Calculate Acceleration
		double aX = fX / mass;
		double aY = fY / mass;

		// Calculate new velocity
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		
		// Calculate new position
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	/**
	 * Draws current planet 
	 */
	public void draw() {
		String imageToDraw = "./images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos , imageToDraw);

	}
}
