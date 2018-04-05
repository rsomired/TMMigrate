/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tek.muleautomator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.tek.muleautomator.config.Connection;

/**
 *
 * @author asgupta
 */
public class MuleAutomatorConstants {
	public static String TIBCO_PROJECT_ROOT_FOLDER;
    private static String seperator = File.separator;
    public static String muleConfigTemplate="";
    public static String muleConfigTemplatePath="..\\src\\main\\resources\\config-template.xml";
	public static boolean removeExistingProject=true;
	public static String muleResourcesPath="";
	public static String tibcoProcessPath="";

	/**
	 * Declaration of Dynamic Values needed for functioning of Automator
	 */
	
	public static VarsResolver tibcoVarsResolver;
	public static HashMap<String, String> tibcoLocalVariables=new HashMap<>();
	public static HashMap<String, Connection> connectionConfigs=new HashMap<>();
	public static List<File> allTibcoProcessFiles=new ArrayList<>();
	public static List<File> filteredTibcoProcessFiles = new ArrayList<>();
	public static HashSet<String> generatedFlows=new HashSet<>();
	public static HashMap<String, String> specifiedSchema = new HashMap<>();
    public static HashSet<String> configuredConnections=new HashSet<>();
	
	
	/**
	 * Caches Mule Config Template into String at first reference of MuleAutomatorConstant Class. 
	 */
    
    static {
    	
    	// Load Mule Config Template
    	File file=new File(muleConfigTemplatePath);
    	
    	try(FileReader reader = new FileReader(file);) {
			char[] buff=new char[4096];
			int bfLength=0;
    		while((bfLength=reader.read(buff))>0){
    			muleConfigTemplate+=new String(buff,0,bfLength);
    		}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
    
    
    /**
     * Load all the Global Variables from specified location into VarResolver Object
     * @param location
     */
    
    public static void loadGlobalVars(String location) {
    	tibcoVarsResolver=new VarsResolver(location);
    }
    
    
    
    
    public static String generateMavenCommand(String projectName){
        return "cmd /c mvn archetype:generate -B -DarchetypeGroupId=org.mule.tools.maven -DarchetypeArtifactId=maven-achetype-mule-app -DarchetypeVersion=1.0 -DgroupId=org.mycompany.app -DartifactId="
				+ projectName + " -Dversion=1.0-SNAPSHOT -DmuleVersion=3.8.1 -Dpackage=org.mycompany.app";
    }
    
    public static String generateMuleConfigPath(String directory,String projectName, String configName){
        return directory + seperator + projectName + seperator + "src" + seperator + "main" + seperator
				+ "app" + seperator + configName + ".xml";
    }
    
    public static String generateSourcePath(String projectName){
        return System.getProperty("user.dir") + seperator + projectName;
    }
    
    public static String generateMuleResourcesPath(String directory, String projectName){
        return directory + seperator + projectName + seperator + "src" + seperator + "main"+ seperator + "resources";
    }
    
    public static String generateMuleTestClassFilesPath(String directory, String projectName){
        return directory + seperator + projectName + seperator + "src" + seperator + "test"
				+ seperator + "java" + seperator + "org";
    }

	public static String generateDefaultVarsPath(String tibcoProjectLocationRootFolder) {
		return tibcoProjectLocationRootFolder+"/";
	}
    
    
    
}
