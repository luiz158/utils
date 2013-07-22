Class used to generate ddl from an jpa - hibernate configuration.
This is not a permanent configuration because it needs to have the main generator class in the source folder of the project.
- Copy and paste the package with the class in the java source folder of the project
- Configure the <plugin> section in the pom.xml (see comments)
- Copy and paste the <plugin> section in the build section of the project
- Call the maven command : process-classes
- Enjoy the ddl in the output folder !

Source : http://doingenterprise.blogspot.ch/2012/05/schema-generation-with-hibernate-4-jpa.html
