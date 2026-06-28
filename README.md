# SeleniumProjects

## Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Cucumber
- Page Object Model
- Selenium Grid
- Allure Report
- Log4j2
- Jackson JSON test data
- Rest Assured API testing
- Jenkins pipeline

## Requirements

- JDK 17

## Execution

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd clean test '-Dproject.name=template' '-Dgroups=ui'
.\mvnw.cmd clean test '-Dproject.name=template' '-Dgroups=api'
.\mvnw.cmd clean test '-Dproject.name=template' '-Dgroups=smoke'
.\mvnw.cmd test '-Dproject.name=first project' '-Dgroups=ui'
```

`project.name` selects the resource folder under `src/test/resources/projects`.
The Java package defaults to the same name with spaces and symbols removed.
For a custom package folder, add `'-Dproject.package=<package-folder>'`.

```powershell
.\mvnw.cmd clean test '-Dbrowser=chrome' '-Dexecution=local' '-Dheadless=true'
.\mvnw.cmd clean test '-Dbrowser=chrome' '-Dexecution=remote' '-Dgrid.url=http://localhost:4444/wd/hub'
```

```powershell
.\mvnw.cmd clean test '-DsuiteXmlFile=src/test/resources/cucumber.xml' '-Dcucumber.features=src/test/resources/features/template'
```

## Reporting

```powershell
.\mvnw.cmd allure:serve
```

## Jenkins Parameters

- `SUITE_XML`
- `PROJECT_NAME`
- `BROWSER`
- `EXECUTION`
- `BASE_URL`
- `API_BASE_URL`
- `GRID_URL`
- `GROUPS`
- `CUCUMBER_TAGS`
- `HEADLESS`

## Project Layout

```text
src/test/java/com/phanipramod/automation/projects/<project>/pages
src/test/java/com/phanipramod/automation/projects/<project>/tests
src/test/java/com/phanipramod/automation/projects/<project>/steps
src/test/resources/projects/<project>
src/test/resources/features/<project>
```
