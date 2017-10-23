package com.chc.other;

public enum Topics {
    ABC(0, "ABC"),
    BCD(1,"BCD"),
    CDE(2,"CDE"),
    DEF(3, "DEF"),
    EFG(4, "EFG");

	public int id;
	public String name;

    private Topics (int id, String name){
	    	this.id = id;    
	    	this.name = name;
    }
}