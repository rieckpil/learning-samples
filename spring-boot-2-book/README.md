# How to install spring boot service as Unix service

1. Configure spring-boot-maven-plugin to build a executable .jar
2. `sudo ln -s /var/myapp/helloworld.jar /etc/init.d/helloworld`