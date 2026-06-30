pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    environment {
        MAVEN_OPTS = '-Xms64m -Xmx384m -XX:MaxMetaspaceSize=192m -Djava.awt.headless=true'
    }

    parameters {
        choice(name: 'SUITE_XML', choices: ['cucumber.xml', 'testng.xml'], description: 'Suite file')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser')
        choice(name: 'EXECUTION', choices: ['local', 'remote'], description: 'Execution mode')
        string(name: 'PROJECT_NAME', defaultValue: 'first project', description: 'Project folder name')
        string(name: 'PROJECT_PACKAGE', defaultValue: 'firstproject', description: 'Optional Java package folder')
        string(name: 'BASE_URL', defaultValue: 'https://rahulshettyacademy.com/seleniumPractise/#/', description: 'UI application base URL')
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
                    def projectPackage = params.PROJECT_PACKAGE?.trim()
                    if (!projectPackage) {
                        projectPackage = params.PROJECT_NAME.replaceAll('[^A-Za-z0-9_]', '').toLowerCase()
                    }

                    def mavenProperties = [
                            "-DsuiteXmlFile=src/test/resources/${params.SUITE_XML}",
                            "-Dbrowser=${params.BROWSER}",
                            "-Dexecution=${params.EXECUTION}",
                            "-Dproject.name=${params.PROJECT_NAME}",
                            "-Dbase.url=${params.BASE_URL}",
                            "-Dapi.base.url=${params.API_BASE_URL}",
                            "-Dgrid.url=${params.GRID_URL}",
                            "-Dcucumber.features=src/test/resources/features/${projectPackage}",
                            "-Dheadless=${params.HEADLESS}"
                    ]

                    mavenProperties.add("-Dproject.package=${projectPackage}")
                    if (params.SUITE_XML == 'testng.xml' && params.GROUPS?.trim()) {
                        mavenProperties.add("-Dgroups=${params.GROUPS.trim()}")
                    }
                    if (params.CUCUMBER_TAGS?.trim()) {
                        mavenProperties.add("-Dcucumber.filter.tags=${params.CUCUMBER_TAGS.trim()}")
                    }

                    if (isUnix()) {
                        def args = mavenProperties.collect { "'${it.replace("'", "'\"'\"'")}'" }.join(' ')
                        sh "./mvnw -B -ntp clean test ${args}"
                    } else {
                        def args = mavenProperties.collect { "\"${it.replace('"', '""')}\"" }.join(' ')
                        bat "mvnw.cmd -B -ntp clean test ${args}"
                    }
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'logs/**/*.log,target/allure-results/**,target/surefire-reports/**'
        }
    }
}
