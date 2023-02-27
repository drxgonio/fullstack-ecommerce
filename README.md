# fullstack-ecommerce
Using Java 17, Spring boot, Angular, MySql

//disable cache
//@Cacheable


## Dump data mySQL from docker

Step 1: Get the name or ID of the container running MySQL by running the command:

```sh
docker ps
```

Step 2: Use the docker exec command to execute the mysqldump command inside the container:

```sh
docker exec <container_name_or_id> mysqldump --databases <database_name> -u <username> -p <password> > <output_file_name>.sql
```

Step 3: Exit bash (have space in firt string):

```sh
 exit
```

Step 4: Copy file dump to local machine. You need type: ls on terminal to check path first, after that you copy file from docker container to local:

```sh
docker cp <container-id>:<output-file>.sql .
```

## Deploy FE to GCP App Engine

Step 1: Need login with gcloud

```sh
gcloud auth login
```

Step 2: Set projectId 

```sh
gcloud config set project drg-ecomerce
```


Step 3: Get list project 

```sh
gcloud projects list
```

Step 4: Gcloud init

```sh
gcloud init
```

Step 5: Get app deploy 

```sh
gcloud app deploy 
```

Step 6: Verify on browser

```sh
https://PROJECT_ID.REGION_ID.r.appspot.com
```

## Deploy BE to GCP App Engine (1 project have only 1 application on App Engine so need create new project)

Step 1: Need login with gcloud

```sh
gcloud auth login
```

Step 2: Set projectId 

```sh
gcloud config set project ecommerce-5c0af
```


Step 3: Get list project 

```sh
gcloud projects list
```

Step 4: Gcloud init

```sh
gcloud init
```

Step 5: Build project 

```sh
mvn clean package 
```

Step 6: Get app deploy 

```sh
gcloud app deploy 
```

Step 7: Verify on browser

```sh
https://PROJECT_ID.REGION_ID.r.appspot.com
```

