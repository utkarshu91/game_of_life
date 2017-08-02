FROM maven
# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# Install app dependencies
COPY . /usr/src/app/
RUN mvn clean install
#RUN mvn clean install
# Bundle app source
COPY . /usr/src/app
EXPOSE 8083
CMD [ 'mvn', 'package' ]
