pipeline {
    agent any
    tools{
        maven 'maven-3.9.9'
    }
    environment{
        dockerImage = ''
        registry = 'prakashkbehera/docker-jenkins-integration'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/pkbgit/docker-jenkins-integration']]])
                bat 'mvn clean install'
            }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    dockerImage = docker.build registry
                }
            }
        }
        stage('Push Docker Image to Docker Hub'){
            steps{
                script{
                    // docker.withRegistry('', 'docker-uid-w-pwd'){
                    //     dockerImage.push()
                    // }
                    withDockerRegistry(credentialsId: 'dockerhub_id') {
                        dockerImage.push()
                    }
                }
            }
        }
        // stage('Cleaning up') {
        //     steps{sh "docker rmi $registry:$BUILD_NUMBER"
        // }
        // stage('Deploy to Kubernetes'){
        //     steps{
        //         script{
        //             kubernetesDeploy (configs: 'kubernetes-deploy-service.yaml', kubeconfigId: 'k8s-config-pwd-4')
        //             }
        //         }
        //     }
        
    }
}
