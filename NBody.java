/**
 * The Nbody class simulates a the universe.
 */
public class NBody {

	/**
	 * Return a double corresponding to the radius of the universe in that file
	 */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readLine();
		return Double.parseDouble(in.readLine());
		
	}

	/**
	 * Return an array of Planets corresponding to the planets in the file
	 */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[planetNum];

		// Cycles through lines
		for (int i = 0; i < planetNum; i++) {

			// Get planet values
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			// Create planet
			Planet newPlanet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

			// Assign Planet to array
			planets[i] = newPlanet;
		}
		
		return planets;
	}

	public static void main(String[] args) {
		double T; 			   	   // Time to run simulation
		double dt; 				   // Time interval
		String filename;  		   // Universe filename
		Planet[] universePlanets;  // univers planets 
		double universeRadius = 0; // universe radius

		// Get values from command line
		 T = Double.parseDouble(args[0]);
		 dt = Double.parseDouble(args[1]);
		 filename = args[2];
		 universePlanets = readPlanets(filename);
		 universeRadius = readRadius(filename);

		// Draw universe
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-universeRadius, universeRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);

		// Draw planets
		for (int i = 0; i < universePlanets.length; i++) {
			universePlanets[i].draw();
		}

		// Start Audio
		StdAudio.play("./audio/2001.mid");

		// Animate planets
		for (double time = 0; time <= T; time += dt) {
			
			// Store Net forces
			Double[] xForces = new Double[universePlanets.length];
			Double[] yForces = new Double[universePlanets.length];

			// Calc Net forces for each planet
			for (int i = 0; i < universePlanets.length; i++) {
				xForces[i] = universePlanets[i].calcNetForceExertedByX(universePlanets);
				yForces[i] = universePlanets[i].calcNetForceExertedByY(universePlanets);;
			}

			// Update each planet 
			for (int i = 0; i < universePlanets.length; i++) {
				universePlanets[i].update(dt, xForces[i], yForces[i]);
			}

			// Draw universe
			StdDraw.picture(0, 0, imageToDraw);

			// Draw planets
			for (int i = 0; i < universePlanets.length; i++) {
				universePlanets[i].draw();
			}

			// Pause animation
			StdDraw.show(10);
			
		}


		// Print the Universe
		StdOut.printf("%d\n", universePlanets.length);
		StdOut.printf("%.2e\n", universeRadius);
		for (int i = 0; i < universePlanets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		universePlanets[i].xxPos, universePlanets[i].yyPos, universePlanets[i].xxVel, universePlanets[i].yyVel, universePlanets[i].mass, universePlanets[i].imgFileName);	
		}	
	}
}
