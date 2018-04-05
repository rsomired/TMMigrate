package com.tek.muleautomator.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tek.muleautomator.config.FTPConnection;
import com.tek.muleautomator.config.HTTPConnection;
import com.tek.muleautomator.config.JDBCConnection;
import com.tek.muleautomator.config.JMSConnection;
import com.tek.muleautomator.config.WSDLConnection;
import com.tek.muleautomator.model.TMMigrateModel;
import com.tek.muleautomator.mvn.MuleProjectSetup;
import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleAutomatorUtil;
import com.tek.muleautomator.util.MuleConfigConnection;
import com.tek.muleautomator.util.MuleFlowTools;

public class MuleAutomateManager {

	/**
	 * Method to execute mule automator application.
	 * 
	 * @param tibco
	 *            project root folder.
	 * @param tibco
	 *            process location.
	 */

	public static void migrateTibcoToMule(TMMigrateModel tmMigrateModel) {
		Element flowElement = null;
		try {
			String tibcoProjectLocationRootFolder = tmMigrateModel.getTibcoProjectLocation();
			String workspace = tmMigrateModel.getMuleWorkspaceLocation();
			System.out.println(tibcoProjectLocationRootFolder+"========"+workspace);
			MuleAutomatorUtil.fileFinder(new File(tibcoProjectLocationRootFolder),
					MuleAutomatorConstants.allTibcoProcessFiles, new String[] { "process" });
			// System.out.println("All:
			// "+MuleAutomatorConstants.tibcoProcessFiles);
			// Loads all the Global Variables into
			// MuleAutomatorConstants.globalResolver Object
			MuleAutomatorConstants.TIBCO_PROJECT_ROOT_FOLDER = tibcoProjectLocationRootFolder;
			String projectName = getProjectName(tibcoProjectLocationRootFolder);

			// Remove Sub Process files
			MuleFlowTools.filterTibcoFiles();
			List<File> processFiles = MuleAutomatorConstants.filteredTibcoProcessFiles;
			Set<String> filesCreated = new HashSet<>();
			createMuleProject(tibcoProjectLocationRootFolder, projectName, workspace);
			MuleAutomatorUtil.includeLibraries(tibcoProjectLocationRootFolder, workspace + "/" + projectName);

			String muleConfigPath = MuleAutomatorConstants.generateMuleConfigPath(workspace, projectName,
					"mule-config");

			int i = 1;
			int skips = 0;
			for (File currProcess : processFiles) {
				MuleAutomatorConstants.tibcoProcessPath = currProcess.getCanonicalPath();
				String currFileName = currProcess.getName().substring(0, currProcess.getName().indexOf("."));
				int temp = 1;
				String fileName_temp = new String(currFileName);
				while (filesCreated.contains(currFileName)) {
					currFileName = new String(fileName_temp);
					currFileName += "" + temp++;
				}
				filesCreated.add(currFileName);

				System.out.println("\n");
				int printLen = ("***   Process " + i + ": " + currFileName + "    ***").length();
				while (printLen-- > 0)
					System.out.print("*");
				System.out.println();
				System.out.println("***   Process " + i + ": " + currFileName + "    ***");
				printLen = ("***   Process " + i + ": " + currFileName + "    ***").length();
				while (printLen-- > 0)
					System.out.print("*");
				System.out.println();

				if (i == 1) {
					MuleAutomatorUtil.renameFile(muleConfigPath,
							MuleAutomatorConstants.generateMuleConfigPath(workspace, projectName, currFileName));
					muleConfigPath = MuleAutomatorConstants.generateMuleConfigPath(workspace, projectName,
							currFileName);
					MuleConfigConnection.updateConfigDom(muleConfigPath);
					MuleFlowTools.removeDefaultFlow(muleConfigPath, projectName);

				} else {
					muleConfigPath = MuleAutomatorConstants.generateMuleConfigPath(workspace, projectName,
							currFileName);

					File file = new File(muleConfigPath);
					file.createNewFile();
					MuleAutomatorUtil.writeToFile(file.getCanonicalPath(), MuleAutomatorConstants.muleConfigTemplate);
					MuleConfigConnection.updateConfigDom(muleConfigPath);
				}
				i++;
				currFileName = currFileName.replaceAll(" ", "_");
				if (new File(muleConfigPath).exists()) {
					flowElement = MuleFlowTools.createMuleFlow(muleConfigPath, currFileName);
				}
				try {
					flowElement = MuleFlowTools.generateMuleFlowFromTibcoProcessOrderByTransitionsWithChoice(
							currProcess.getCanonicalPath(), muleConfigPath, flowElement);
					Document doc = MuleConfigConnection.getDomObj(muleConfigPath);
					doc.getFirstChild().appendChild(flowElement);
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(muleConfigPath);
					transformer.transform(source, result);
				} catch (Exception E) {
					skips++;
					System.out.println(E.getMessage() + " =  " + E.toString());
					System.out.println("**** >>> ERROR PARSING !!! SKIPPING THIS FILE <<< ****");
				}

			}

			System.out.println("\n\n******************* ALL COMPLETE! *******************");
			System.out.println("***** SKIPPED: " + skips + " files *****");

			// System.out.println("Vars: "+
			// MuleAutomatorConstants.tibcoVariables);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fetchAllConnections(String tibcoProjectLocationRootFolder) {
		List<File> connFiles = new ArrayList<>();
		MuleAutomatorUtil.fileFinder(new File(tibcoProjectLocationRootFolder), connFiles,
				new String[] { "sharedjdbc", "sharedhttp", "sharedftp", "sharedpartner", "sharedjmscon" });
		for (File file : connFiles) {
			if (file.getPath().contains("jdbc")) {
				JDBCConnection jdbcConnection = new JDBCConnection(file);
				MuleAutomatorConstants.connectionConfigs.put(jdbcConnection.CONNECTION_NAME, jdbcConnection);

			} else if (file.getPath().contains("http")) {
				HTTPConnection httpCon = new HTTPConnection(file);
				String fileName = "";
				try {
					fileName = file.getCanonicalPath()
							.substring(file.getCanonicalPath().lastIndexOf(File.separator) + 1);
					fileName = fileName.substring(0, fileName.indexOf("."));
				} catch (IOException e) {
					fileName = "HTTP Connection";
					e.printStackTrace();
				}
				httpCon.CONNECTION_NAME = fileName;
				MuleAutomatorConstants.connectionConfigs.put(httpCon.CONNECTION_NAME, httpCon);

			} else if (file.getPath().contains("ftp")) {
				FTPConnection ftpCon = new FTPConnection(file);
				String fileName = "";
				try {
					fileName = file.getCanonicalPath()
							.substring(file.getCanonicalPath().lastIndexOf(File.separator) + 1);
					fileName = fileName.substring(0, fileName.indexOf("."));
				} catch (IOException e) {
					fileName = "HTTP Connection";
					e.printStackTrace();
				}
				ftpCon.CONNECTION_NAME = fileName;
				MuleAutomatorConstants.connectionConfigs.put(ftpCon.CONNECTION_NAME, ftpCon);
			} else if (file.getPath().contains("partner")) {
				String fileName;
				WSDLConnection wsdlCon = new WSDLConnection(file);
				try {
					fileName = file.getCanonicalPath()
							.substring(file.getCanonicalPath().lastIndexOf(File.separator) + 1);
					fileName = fileName.substring(0, fileName.indexOf("."));
				} catch (IOException e) {
					fileName = "Partner";
					e.printStackTrace();
				}
				wsdlCon.CONNECTION_NAME = fileName;
				MuleAutomatorConstants.connectionConfigs.put(wsdlCon.CONNECTION_NAME, wsdlCon);
			} else if (file.getPath().contains("jmscon")) {
				String fileName;
				JMSConnection jmsCon = new JMSConnection(file);
				try {
					fileName = file.getCanonicalPath()
							.substring(file.getCanonicalPath().lastIndexOf(File.separator) + 1);
					fileName = fileName.substring(0, fileName.indexOf("."));
				} catch (IOException E) {
					fileName = "JMS Conncetion";
					E.printStackTrace();
				}
				jmsCon.CONNECTION_NAME = fileName;
				MuleAutomatorConstants.connectionConfigs.put(jmsCon.CONNECTION_NAME, jmsCon);
			}
		}
	}

	/**
	 * Method to create mule project.
	 * 
	 * @param tibco
	 *            project root folder.
	 * @param mule
	 *            project name.
	 * @param mule
	 *            project workspace.
	 */

	private static void createMuleProject(String tibcoProjectLocationRootFolder, String projectName, String directory)
			throws IOException {

		if (MuleAutomatorConstants.removeExistingProject)
			renameExistingFiles(directory + "//" + projectName);
		MuleProjectSetup muleProjectSetup = new MuleProjectSetup();
		System.out.println("* * Creating Mule Project using Maven...");
		muleProjectSetup.createMuleProject(tibcoProjectLocationRootFolder, directory, projectName);
		MuleAutomatorConstants.muleResourcesPath = directory + "\\" + projectName + "\\src\\main\\resources\\";
		fetchAllConnections(tibcoProjectLocationRootFolder);
	}

	private static void renameExistingFiles(String directory) {
		File dir = new File(directory);
		if (!dir.exists())
			return;
		if (dir.listFiles().length > 0) {
			System.out.print("* * Removing existing project...");
			MuleAutomatorUtil.deleteDirectory(dir);
			System.out.println(" Complete\n");
		}
	}

	/**
	 * Method to get mule project name.
	 * 
	 * @param tibco
	 *            process path.
	 * @return mule project name.
	 */

	private static String getProjectName(String path) {
		return new File(path).getName();

	}

}
