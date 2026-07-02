# SeleniumProjects

## Stack

- Java 17
- Maven
- Selenium WebDriver
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
.\mvnw.cmd clean test '-Dproject.name=first project' '-Dcucumber.filter.tags=@greenkart'
```

`project.name` selects the resource folder under `src/test/resources/projects`.

```powershell
.\mvnw.cmd clean test '-Dbrowser=chrome' '-Dexecution=local' '-Dheadless=true'
.\mvnw.cmd clean test '-Dbrowser=chrome' '-Dexecution=remote' '-Dgrid.url=http://localhost:4444/wd/hub'
```

```powershell
.\mvnw.cmd clean test '-Dcucumber.filter.tags=@greenkart'
```

## Reporting

```powershell
.\mvnw.cmd allure:serve
```

## Cucumber Hooks

TestNG listener code is not used for the Cucumber flow.

- `src/test/java/com/phanipramod/automation/runners/CucumberTest.java` starts Cucumber with JUnit Platform and the Allure Cucumber plugin.
- `src/test/java/com/phanipramod/automation/projects/firstproject/steps/GreenKartHooks.java` uses Cucumber `@Before` and `@After` hooks for browser setup, browser quit, and failure screenshots.
- `src/test/resources/cucumber.properties` is included for IntelliJ feature/scenario runs, so the IDE can find the glue package and reporting plugin without manual run configuration.

## Jenkins Parameters

- `PROJECT_NAME`
- `BROWSER`
- `EXECUTION`
- `BASE_URL`
- `API_BASE_URL`
- `GRID_URL`
- `CUCUMBER_TAGS`
- `HEADLESS`

## Project Layout

```text
src/test/java/com/phanipramod/automation/projects/<project>/pages
src/test/java/com/phanipramod/automation/projects/<project>/steps
src/test/resources/projects/<project>
src/test/resources/features/<projectpackage>
```
