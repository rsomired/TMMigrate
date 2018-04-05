package com.tek.muleautomator.mvn;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.tek.muleautomator.util.MuleAutomatorConstants;
import com.tek.muleautomator.util.MuleAutomatorUtil;

public class MuleProjectSetup {

	public void createMuleProject(String tibcoProjectLocationRootFolder, String directory, String projectName)
			throws IOException {
		String sourceFile = MuleAutomatorConstants.generateSourcePath(projectName);
		String destinationFile = directory + File.separator + projectName;
		String muleResourcesPath = MuleAutomatorConstants.generateMuleResourcesPath(directory, projectName);
		String testclassFilesPath = MuleAutomatorConstants.generateMuleTestClassFilesPath(directory, projectName);
		String defaultVarsPath = MuleAutomatorConstants.generateDefaultVarsPath(tibcoProjectLocationRootFolder);
		String cmd = MuleAutomatorConstants.generateMavenCommand(projectName);
		try {
			String outlist[] = createMuleProjectByMaven(cmd);
			for (int i = 0; i < outlist.length; i++) {
				System.out.println(outlist[i]);
			}
			moveMuleProjectToSpecifiedDirectory(sourceFile, destinationFile);
			deleteDefaultTestFiles(new File(testclassFilesPath));
			MuleAutomatorConstants.loadGlobalVars(tibcoProjectLocationRootFolder);
			List<File> tibcoFiles = new ArrayList<>();
			MuleAutomatorUtil.fileFinder(new File(tibcoProjectLocationRootFolder), tibcoFiles, new String[] { "wsdl", "xsd", "xsl", "substvar"});
			moveTibcoFilesToMuleProject(tibcoFiles, muleResourcesPath);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void moveTibcoFilesToMuleProject(List<File> tibcoFiles, String muleResourcesPath) {
		File muleRootDirectory = new File(muleResourcesPath);
		for (File f : tibcoFiles) {
			copyFile(f, new File(muleRootDirectory.getAbsolutePath() + File.separator + f.getName()));
		}
	}

	private void copyFile(File source, File destination) {
		if (!destination.exists())
			destination.mkdirs();
		try {
			Files.copy(Paths.get(source.getPath()), Paths.get(destination.getPath()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	private void moveMuleProjectToSpecifiedDirectory(String sourceFile, String destinationFile) {
		File srcFile = new File(sourceFile);
		File destFile = new File(destinationFile);
		destFile.mkdirs();
		moveFiles(srcFile, destFile);
		deleteDirectory(srcFile);
	}

	private static boolean moveFiles(File sourceFile, File destFile) {
		if (sourceFile.isDirectory()) {
			for (File file : sourceFile.listFiles()) {
				moveFiles(file, new File(destFile.getPath() + File.separator + file.getName()));
			}
		} else {
			try {
				destFile.mkdirs();
				Files.move(Paths.get(sourceFile.getPath()), Paths.get(destFile.getPath()),
						StandardCopyOption.REPLACE_EXISTING);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static void deleteDirectory(File dir) {
		if (dir.listFiles().length == 0) {
			dir.delete();

		} else {
			for (File file : dir.listFiles()) {
				deleteDirectory(file);
			}
		}
		dir.delete();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String[] createMuleProjectByMaven(String cmd) throws IOException {
		ArrayList list = new ArrayList();
		Process proc = Runtime.getRuntime().exec(cmd);
		InputStream istr = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(istr));
		String str;
		while ((str = br.readLine()) != null)
			list.add(str);
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
			System.err.println("Process was interrupted");
		}
		br.close();

		return (String[]) list.toArray(new String[0]);
	}

	private static void deleteDefaultTestFiles(File dir) {
		if (dir.isDirectory()) {
			String[] DirChildren = dir.list();
			for (int i = 0; i < DirChildren.length; i++) {
				deleteDefaultTestFiles(new File(dir, DirChildren[i]));
			}
		}
		dir.delete();
	}

}
