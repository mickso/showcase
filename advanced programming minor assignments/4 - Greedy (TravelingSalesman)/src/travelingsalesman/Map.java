package travelingsalesman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Map extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char c = 1;
	private ArrayList<City> cities = new ArrayList<City>(), route;
	private String[] names = {"Amsterdam", "Heerlen", "Arnhem", "Groningen", "Haarlem", "Rotterdam", "Nijmegen", "Enschede"};
	private int scale = 40;
	/*private char[][] map = {
			{ 0,0,0,0,0,0,0,0,0,0,0,0,0,c },
			{ 0,c,0,0,0,0,0,0,c,0,0,0,0,0 },
			{ 0,0,0,0,c,0,0,0,0,0,0,0,0,0 },
			{ 0,0,0,0,0,0,0,0,0,0,0,c,0,0 },
			{ 0,0,c,0,0,0,0,0,c,0,0,0,0,0 },
			{ 0,0,0,0,c,0,0,0,0,0,0,0,0,0 },
		};*/
	private char[][] map = {
			{ c,0,0,0,0 },
			{ 0,0,0,0,c },
			{ 0,0,c,0,0 },
			{ 0,0,0,0,0 },
			{ c,0,0,c,0 },
	};
	public Map() {
		for(int y = 0; y < map.length;y++) {
			for(int x = 0; x < map[0].length;x++) {
				if(map[y][x] == c) {
					int cityX = x + 1,
						cityY = y + 1;
					cities.add(new City(cityX * scale, cityY * scale, names[cities.size()]));
				}
			}
		}
	}
	public ArrayList<City> getCities() {
		return cities;
	}
	public void paint(Graphics g) {
		for(City city: cities) {
			g.drawString(city.getName(), city.getX(), city.getY());
		}
		if(route != null) {
			g.setColor(Color.red);
			City currentCity = route.get(0);
			for(int i = 1; i < route.size(); i++) {
				City nextCity = route.get(i);
				g.drawLine(currentCity.getX(), currentCity.getY(), nextCity.getX(), nextCity.getY());
				currentCity = nextCity;
			}
		}
	}
	public void setRoute(ArrayList<City> route) {
		this.route = route;
	}
	public int getHeight() {
		return (map.length + 2) * scale;
	}
	public int getWidth() {
		return (map[0].length + 2) * scale;
	}
}
