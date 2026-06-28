pipeline {
    agent any

    parameters {
        choice(name: 'SUITE_XML', choices: ['testng.xml', 'cucumber.xml'], description: 'Suite file')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser')
        choice(name: 'EXECUTION', choices: ['local', 'remote'], description: 'Execution mode')
        string(name: 'PROJECT_NAME', defaultValue: 'template', description: 'Project folder name')
        string(name: 'BASE_URL', defaultValue: 'https://example.com', description: 'UI application base URL')
        string(name: 'API_BASE_URL', defaultValue: 'https://jsonplaceholder.typicode.com', description: 'API base URL')
        string(name: 'GRID_URL', defaultValue: 'http://localhost:4444/wd/hub', description: 'Selenium Grid URL')
        string(name: 'GROUPS', defaultValue: '', description: 'Optional TestNG groups such as ui,api,smoke')
        string(name: 'CUCUMBER_TAGS', defaultValue: '', description: 'Optional Cucumber tag expression')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Headless browser execution')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Automation') {
            steps {
                script {
                    if (isUnix()) {
                        sh """
                        ./mvnw clean test \
                          "-DsuiteXmlFile=src/test/resources/${params.SUITE_XML}" \
                          "-Dbrowser=${params.BROWSER}" \
                          "-Dexecution=${params.EXECUTION}" \
                          "-Dproject.name=${params.PROJECT_NAME}" \
                          "-Dbase.url=${params.BASE_URL}" \
                          "-Dapi.base.url=${params.API_BASE_URL}" \
                          "-Dgrid.url=${params.GRID_URL}" \
                          "-Dgroups=${params.GROUPS}" \
                          "-Dcucumber.features=src/test/resources/features/${params.PROJECT_NAME}" \
                          "-Dcucumber.filter.tags=${params.CUCUMBER_TAGS}" \
                          "-Dheadless=${params.HEADLESS}"
                        """
                    } else {
                        bat """
                        mvnw.cmd clean test ^
                          "-DsuiteXmlFile=src/test/resources/${params.SUITE_XML}" ^
                          "-Dbrowser=${params.BROWSER}" ^
                          "-Dexecution=${params.EXECUTION}" ^
                          "-Dproject.name=${params.PROJECT_NAME}" ^
                          "-Dbase.url=${params.BASE_URL}" ^
                          "-Dapi.base.url=${params.API_BASE_URL}" ^
                          "-Dgrid.url=${params.GRID_URL}" ^
                          "-Dgroups=${params.GROUPS}" ^
                          "-Dcucumber.features=src/test/resources/features/${params.PROJECT_NAME}" ^
                          "-Dcucumber.filter.tags=${params.CUCUMBER_TAGS}" ^
                          "-Dheadless=${params.HEADLESS}"
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            archiveArtifacts allowEmptyArchive: true, artifacts: 'logs/**/*.log,target/surefire-reports/**'
        }
    }
}
