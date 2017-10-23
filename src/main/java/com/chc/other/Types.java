package com.chc.other;

public enum Types {
    XYZ(0, "XYZ"),
    XZY(1,"XZY"),
    YZX(2,"YZX");

	public int id;
	public String name;

    private Types (int id, String name){
	    	this.id = id;    
	    	this.name = name;
    }
}
