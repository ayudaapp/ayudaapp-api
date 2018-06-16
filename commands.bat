D:\DevTools\mysql-5.7.19-winx64\bin\mysqld
mvn clean package
java -jar -Dspring.profiles.active=mysql target/ayuda-api-0.4.0.war


git add src

git commit -m "second commit"

git push -u origin master

git show --name-only