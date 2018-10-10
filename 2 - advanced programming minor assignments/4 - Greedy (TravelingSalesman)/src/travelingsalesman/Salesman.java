package travelingsalesman;

import java.util.ArrayList;


public class Salesman {
	private ArrayList<City> cities, route;
	
	public Salesman(ArrayList<City> cities) {
		this.cities = cities;
	}
	public void searchPath(int startIndex) {
		if(startIndex >= cities.size()) {
			return;
		}
		route = new ArrayList<City>();
		City startCity = cities.get(startIndex);
		route.add(startCity);
		searchNextCity(startCity);
	}
	public void searchNextCity(City departureCity) {
		City nearest = null;
		double shortestDistance = -1;
		for(City city : cities) {
			if(route.indexOf(city) == -1) {
				double distance = getDistance(departureCity, city);
				if(shortestDistance == -1 || distance < shortestDistance) {
					shortestDistance = distance;
					nearest = city;
				}
			}
		}
		if(nearest == null) {
			return;
		}
		route.add(nearest);
		searchNextCity(nearest);
	}
	private double getDistance(City departure, City destination) {
		int deltaX = departure.getX() - destination.getX(),
			deltaY = departure.getY() - destination.getY();
		return Math.sqrt((square(deltaX) + square(deltaY)));
	}
	private double square(int number) {
		return number * number;
	}
	public ArrayList<City> getRoute() {
		return route;
	}
}
