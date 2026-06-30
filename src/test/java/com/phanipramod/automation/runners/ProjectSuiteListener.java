package com.phanipramod.automation.runners;

import java.util.List;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class ProjectSuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        String projectName = System.getProperty("project.name", "first project").trim();
        String projectPackage = System.getProperty("project.package", toPackageSegment(projectName)).trim();
        String packageName = "com.phanipramod.automation.projects." + toPackageSegment(projectPackage) + ".tests";

        for (XmlSuite suite : suites) {
            suite.setName("Selenium Projects");
            for (XmlTest test : suite.getTests()) {
                test.setName(toDisplayName(projectName) + " Tests");
                test.setXmlPackages(List.of(new XmlPackage(packageName)));
            }
        }
    }

    private String toPackageSegment(String projectName) {
        String packageSegment = projectName.replaceAll("[^A-Za-z0-9_]", "").toLowerCase();
        if (packageSegment.isBlank()) {
            throw new IllegalArgumentException("Project name must contain at least one package-safe character.");
        }
        return packageSegment;
    }

    private String toDisplayName(String projectName) {
        String displayName = projectName.replaceAll("[^A-Za-z0-9]+", " ").trim();
        if (displayName.isBlank()) {
            return "Project";
        }
        StringBuilder title = new StringBuilder();
        for (String word : displayName.split(" ")) {
            if (word.isBlank()) {
                continue;
            }
            if (!title.isEmpty()) {
                title.append(' ');
            }
            title.append(Character.toUpperCase(word.charAt(0)));
            if (word.length() > 1) {
                title.append(word.substring(1).toLowerCase());
            }
        }
        return title.toString();
    }
}
