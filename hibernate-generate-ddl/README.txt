Class used to generate ddl from an jpa - hibernate configuration.
This is not a permanent configuration because it needs to have the main generator class in the source
folder of the project.
1 - Copy and paste the package with the class in the java source folder of the project
2 - Configure the <plugin> section in the pom.xml (see comments)
3 - Copy and paste the <plugin> section in the build section of the project
4 - Call the maven command : process-classes
5 - Enjoy the ddl in the output folder !