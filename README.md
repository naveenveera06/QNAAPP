Run the below commands in the root directory of the project

Change the working directory to the Server folder : 
cd Server

Build the SpringBoot project with command : 
mvn clean install -Dmaven.test.skip=true

Build the SpringBoot docker using below command : 
docker build -t spring_app .

Deletes docker bridge network if already existing : 
docker network rm qna_network

Below command creates a new docker bridge network : 
docker network create --subnet 172.25.0.0/16 qna_network

Stop the mysql instance if already running : 
docker stop qna_mysql

Remove the mysql container if already exist : 
docker rm qna_mysql

Start the mysql instance which will host the database in network: 
docker run --name qna_mysql -e MYSQL_ROOT_PASSWORD=password-1 -e MYSQL_DATABASE=car_xplore --network=qna_network --ip=172.25.1.3 -d mysql:5.7.24

Stop the SpringBoot container if already running : 
docker stop qna_springboot

Remove the SpringBoot container if already exists : 
docker rm qna_springboot

Start the SpringBoot docker using the below command. This will host the springboot server in the port 8080: 
docker run --name qna_springboot -p 8080:8080 --network=qna_network -d spring_app

Go one directory up : cd ..

Install node dependencies : 
npm install

Run production build : 
npm run build

Build a docker image for the react app : 
docker build -t qna_react .

Stop the react app if already running : 
docker stop qnaapp_react

Remove the react app container if already exists : 
docker rm qnaapp_react

Finally run the react app docker. This exposes the React app in the port 3000 : 
docker run --name qnaapp_react -p 3000:3000 -d qna_react

Now the you can host the project with below link
Open the link http://localhost:3000 in the browser to view the UI.