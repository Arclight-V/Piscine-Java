javac -d target/ -cp lib/\*  src/java/edu/school21/printer/app/Main.java  src/java/edu/school21/printer/logic/*.java &&
cp src/resources/it.bmp target/resources &&
cd target && jar xf ../lib/jcommander-1.81.jar com && jar xf ../lib/JCDP-4.0.2.jar com && cd .. &&
rm -f ./target/images-to-chars-printer.jar &&
jar cvfm target/images-to-chars-printer.jar  src/manifest.txt -C target . &&
java -jar ./target/images-to-chars-printer.jar --white=GREEN --black=RED