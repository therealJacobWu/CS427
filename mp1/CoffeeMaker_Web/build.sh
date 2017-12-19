#!/bin/bash
mkdir -p target/CoffeeMaker_Web/WEB-INF/classes/edu/ncsu/csc326/coffeemaker/exceptions/
cd src/main/java
javac edu/ncsu/csc326/coffeemaker/exceptions/*.java edu/ncsu/csc326/coffeemaker/*.java
for f in edu/ncsu/csc326/coffeemaker/*.class
do
	cp -v "$f" ../../../target/CoffeeMaker_Web/WEB-INF/classes/edu/ncsu/csc326/coffeemaker/
done

for f in edu/ncsu/csc326/coffeemaker/exceptions/*.class
do
	cp -v "$f" ../../../target/CoffeeMaker_Web/WEB-INF/classes/edu/ncsu/csc326/coffeemaker/exceptions/
done

cd ../webapp
for f in *.jsp
do
	cp -v "$f" ../../../target/CoffeeMaker_Web
done
cp WEB-INF/web.xml ../../../target/CoffeeMaker_Web/WEB-INF

cd ../../../target/CoffeeMaker_Web
jar -cvf ../CoffeeMaker_Web.war *
