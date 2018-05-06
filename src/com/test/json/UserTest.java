package com.test.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		File jsonFile = new File("user.json");

		// User ObjectMapper

		ObjectMapper objectMapper = new ObjectMapper();

		User user = objectMapper.readValue(jsonFile, User.class);

		System.out.println(user.getGender());
		System.out.println(user.getName().getFirst());
		System.out.println(user.getName().getLast());

		System.out.println(new String(user.getUserImage()));

		User user2 = new User();

		Name name = new Name();
		name.setFirst("Mohammad");
		name.setLast("Zaman");

		user2.setName(name);
		user2.setGender("male");
		user2.setVerified(true);

		File javaToJson = new File("javaToJson.json");
		objectMapper.writeValue(javaToJson, user2);

		User jsonToJava = objectMapper.readValue(javaToJson, User.class);

		System.out.println("\nGender: " + jsonToJava.getGender());
		System.out.println("First name: " + jsonToJava.getName().getFirst());
		System.out.println("Last name: " + jsonToJava.getName().getLast());

		// Json Array Object to Java List
		String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

		List<Car> cars = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>() {});
		System.out.println("CarList" + cars.size());
		
		cars.forEach(car -> System.out.println(car.getType() + " " + car.getColor()));
		
		
		//JsonMapObject to Java Map
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Map<String, Object> carMap = objectMapper.readValue(json, new TypeReference<Map<String,Object>>() {});
		
		System.out.println("Car Map: " + carMap.size());
		
		carMap.forEach((color,type)-> System.out.println(color +" : "+ type));

		
		Map<String, String> carMaps = new HashMap<String, String>();
		carMaps.put("name", "bmw");
		carMaps.put("color", "green");
		
		objectMapper.writeValue(new File("carMap.json"), carMaps);
		
		Map<Object, Object> m = objectMapper.readValue(new File("carMap.json"), new TypeReference<Map<Object, Object>>() {});
		m.forEach((k,v)->System.out.println("From Map file: " + k + " : " +v));
		
		
		List<Car> list = new ArrayList<Car>();
		Car lexus = new Car();
		lexus.setColor("red");
		lexus.setType("GS 350");
		
		Car benz = new Car();
		benz.setType("S 550");
		benz.setColor("Black");
		
		Car rangeRover = new Car();
		rangeRover.setColor("Blue");
		rangeRover.setType("Sport HSE");
		
		list.add(lexus);
		list.add(benz);
		list.add(rangeRover);
		
		File javaToJsonArray = new File("javaToJsonList.json");
		objectMapper.writeValue(javaToJsonArray, list);
		
		//reading form JsonList
		List<Car> alm = objectMapper.readValue(javaToJsonArray, new TypeReference<List<Car>>(){});
		System.out.println("alm car inventory : " + alm.size() );
		System.out.println("TYPE \t\t COLOR");
		alm.forEach(car -> System.out.println(car.getType() +"------>\t"+ car.getColor()));
		
		
		
		
		
		
	}

	private static class Car {
		private String color;
		private String type;
		
		public String getColor() {
			return color;
		}
		
		public void setColor(String color) {
			this.color = color;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		
	}

}
