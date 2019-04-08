# Java 9 Jigsaw module system

```shell
javac -d build\firstmodule\ src\firstmodule\module-info.java src\firstmodule\com\rieckpil\HelloJigsaw.java
java --module-path build -m firstmodule/com.rieckpil.HelloJigsaw

javac -d build\services\ src\services\*.java src\services\com\services\*.java
javac -d build\jigsawapp\ -p build\ src\jigsawapp\*.java src\jigsawapp\com\rieckpil\*.java

# Multi-Module Build - for PowerShell
javac -d build --module-source-path src $(dir src -r -i '*.java')

# Multi-Module Build - for Mac/Linux
javac -d build --module-source-path src $(find src -name '*.java')

java -p build -m jigsawapp/com.rieckpil.MessageExample

jar --create --file lib/jigsawapp.jar -C build/jigsawapp .
jar --create --file lib/services.jar --module-version 1.0 -C build/services .

java -p lib\ -m jigsawapp/com.rieckpil.MessageExample

jar --create --file lib/jigsawapp.jar --main-class com.rieckpil.MessageExample -C build/jigsawapp .
java -p lib -m jigsawapp
```