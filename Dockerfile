FROM openjdk
WORKDIR /usr/src/myapp
COPY . /usr/src/myapp
CMD ["java","-jar","khetexpert.jar"]
EXPOSE 5000
# docker build -t "container name" .