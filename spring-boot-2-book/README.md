# How to install spring boot service as Unix service

1. Configure spring-boot-maven-plugin to build a executable .jar
2. `sudo ln -s /var/myapp/helloworld.jar /etc/init.d/helloworld`
3. `chown bootapp:bootapp helloworld.jar`
4. `chmod 500 bootapp:bootapp`
5. `sudo chattr +i helloworld.jar`