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

Step 3: Copy file dump to local machine. You need type: ls on terminal to check path first, after that you copy file from docker container to local:

```sh
docker cp <container-id>:<output-file>.sql .
```
