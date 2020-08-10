#!/bin/bash

echo "################################################################"
echo "	INSTALL DOCKER "
echo "################################################################"

#yum install -y wget
#yum install -y yum-utils device-mapper-persistent-data lvm2
#wget https://download.docker.com/linux/centos/7/x86_64/stable/Packages/docker-ce-17.09.1.ce-1.el7.centos.x86_64.rpm
#yum install -y https://download.docker.com/linux/centos/7/x86_64/stable/Packages/docker-ce-17.09.1.ce-1.el7.centos.x86_64.rpm
#systemctl enable docker && systemctl start docker

echo "################################################################"
echo "	INSTALL JAVA "
echo "################################################################"

#yum install -y java-1.8.0-openjdk-devel
#java -version


WORKSPACE=$(pwd)
SOURCEPATH="${WORKSPACE}/sources"
IACPATH="${WORKSPACE}/iac"

rm -rf $SOURCEPATH
rm -rf $IACPATH
rm -rf apache-maven-3.5.4

mkdir -p $SOURCEPATH
mkdir -p $IACPATH


echo "################################################################"
echo "	INSTALL MAVEN "
echo "################################################################"



wget http://www-us.apache.org/dist/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz
tar -xvf apache-maven-3.5.4-bin.tar.gz 
rm -rf apache-maven-3.5.4-bin.tar.gz

apache-maven-3.5.4/bin/mvn --version


echo "################################################################"
echo "	CREATE Dockerfile "
echo "################################################################"


cat << EOF > $IACPATH/Dockerfile
FROM openjdk:8-jdk-alpine 
ARG JAR_FILE
WORKDIR /microservice
COPY \${JAR_FILE} /microservice/app.jar
CMD ["java","-jar","/microservice/app.jar"]"
EOF


echo "################################################################"
echo "	CREATE SH TO INSTALL MS "
echo "################################################################"

cat << EOF > $IACPATH/docker-install.sh
#!/bin/bash
WORKSPACE=$(pwd)
SOURCEPATH="${WORKSPACE}/sources"
IACPATH="${WORKSPACE}/iac"

BUILD_REPOSITORY_NAME="ms-demo"
cd \$SOURCEPATH

rm -rf \$BUILD_REPOSITORY_NAME
git clone https://github.com/amadorZamora/ms-demo.git
cd \$BUILD_REPOSITORY_NAME

$WORKSPACE/apache-maven-3.5.4/bin/mvn clean package -DskipTests=true

FILENAME=\$(ls target | grep '.jar' | grep -v original)
cp target/\$FILENAME \$IACPATH

cd $IACPATH
docker build --no-cache --build-arg JAR_FILE=\$FILENAME -t microservices:v1 .
docker rm -f ms-demo 
docker run -d -p 8080:8080 --name ms-demo microservices:v1
EOF


echo "################################################################"
echo "	INSTALL MS "
echo "################################################################"

chmod +x $IACPATH/docker-install.sh
sh $IACPATH/docker-install.sh
docker logs -f ms-demo