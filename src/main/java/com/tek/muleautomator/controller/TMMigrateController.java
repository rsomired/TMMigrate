package com.tek.muleautomator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tek.muleautomator.core.MuleAutomateManager;
import com.tek.muleautomator.model.TMMigrateModel;



@RestController
@RequestMapping("/")
public class TMMigrateController {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@RequestMapping(value = "/migrate", method = RequestMethod.POST)
	public void migrateTibcoToMule(@RequestBody TMMigrateModel tmMigrateModel) {
		try {
			MuleAutomateManager.migrateTibcoToMule(tmMigrateModel);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
