package com.dch.other;

public enum Modes {
    MODE1(0, "M1"),
    MODE2(1,"M2"),
    MODE3(2,"M3"),
    MODE4(3,"M4"),
    MODE5(4,"M5"),
    MODE6(5,"M6");

	public int id;
	public String name;

    private Modes (int id, String name){
	    	this.id = id;    
	    	this.name = name;
    }
}
