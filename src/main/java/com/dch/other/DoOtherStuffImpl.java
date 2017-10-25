package com.dch.other;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dch.model.Topics;
import com.dch.model.Types;
import com.dch.service.TopicsService;
import com.dch.service.TypesService;

@SpringBootApplication(scanBasePackages={"com.dch.other"})
public class DoOtherStuffImpl implements DoOtherStuff {

	@Autowired
    private TopicsService topicsService;

	@Autowired
    private TypesService typesService;
	
	private int  n;
	
	Random rand = new Random();
	Topics atopic;
	Types atype;

	// create these dynamically as needed in your app
	@Override
	public void doStuff() {
		int i=0;
		do {
			n = rand.nextInt(40) + 1;    atopic = new Topics();   atopic.setName("ABC"+n); 

			boolean isUnique = true;
			for (Topics top : topicsService.findAll()) {
				if (top.getName().equals(atopic) ) isUnique = false;
			}
			if (isUnique) {
		        	topicsService.save(atopic);
		        	i++;
			}
		} while (i<2);
			
		i=0;
		do {
			n = rand.nextInt(40) + 1;    atype = new Types();     atype.setName("XYZ"+n);
			boolean isUnique = true;
			for (Types typ : typesService.findAll()) {
				if (typ.getName().equals(atype) ) isUnique = false;
			}
			if (isUnique) {
		        	typesService.save(atype);
		        	i++;
			}
		} while (i<2);

	}
	
}
