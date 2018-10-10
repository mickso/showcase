package travelingsalesman;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		Map map = new Map();
		Salesman salesman = new Salesman(map.getCities());
		salesman.searchPath(1);
		map.setRoute(salesman.getRoute());
		m.createFrame(map);
	}
	private void createFrame(Map map) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(map.getWidth(), map.getHeight()));
		frame.add(map);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
