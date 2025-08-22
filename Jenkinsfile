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
        stage('SonarQube Analysis') {            
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'sonarqube_id_1') {
                        bat 'mvn clean verify sonar:sonar'
                    }
                }
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
                    withDockerRegistry(credentialsId: 'dockerhub_id') {
                        dockerImage.push()
                    }
                }
            }
        }
        // stage('Cleaning up') {
        //     steps{sh "docker rmi $registry:$BUILD_NUMBER"
        // }
        stage('Deploy to Kubernetes'){
            steps{
                script{
                    //kubernetesDeploy (configs: 'kubernetes-deploy-service.yaml', kubeconfigId: 'k8s-config-pwd-4')
                    withKubeCredentials(kubectlCredentials: [[ credentialsId: 'k8s-secret']]) {
                        //bat 'kubectl apply -f kubernetes-deploy-service.yaml' 
                        bat '''
                        echo --- Cluster Info ---
                        kubectl cluster-info

                        echo --- Applying Deployment ---
                        kubectl apply -f kubernetes-deploy-service.yaml

                        echo --- Checking Deployments ---
                        kubectl get deploy

                        echo --- Checking Pods ---
                        kubectl get pods -o wide
                        '''
                    }
                }
            }
        }
        /*stage('Commit & Push') {
		    steps {
			  git 'https://your-repo.git'
			  
			  def manifestFile = 'manifest.yml'
		      def content = readFile(manifestFile)
		
		      // Replace image line (regex)
		      def newContent = content.replaceAll(/image:\s+${IMAGE_NAME}:., "image: ${IMAGE_NAME}:${BUILD_NUMBER}")
		
		      // Write the updated content back
		      writeFile(file: manifestFile, text: newContent)
							
		      bat 'git config user.name "Jenkins"'
		      bat 'git config user.email "jenkins@local"'
		      bat 'git add manifest.yml'
		      bat "git commit -m \"Update image version to ${BUILD_NUMBER}\" || echo No changes"
		      bat 'git push origin HEAD:main'
		    }
		}*/
        
    }
}
