# face-catch
A variant of the project for determining valid images. This configuration used a small database with three faces that will be searched for in the input images. For face detection was used OpenCV lib with xml cascades.
# opencv
Unfortunately opencv does not compile correctly with maven dependency and have to be added manually to the build path
( <a target="_blank" href="https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse">How to add opencv</a> )
# properties
To configure inport/export files and files for image detection was used <a href="src/main/resources/settings.properties">settings.properties</a>.
# structure
<img src="README/struc_scheme.png" height="500" width="800">
# configurations
<br>
# example
<p>For example was used MySQL database (<a href="src/main/resources/create_db.sql">create script</a>)</p>
<p>Hibernate configuration <a href="src/main/resources/face_catch.cfg.xml">face_catch.cfg.xml</a>)</p>

<img src="README/bd.png" height="500" width="800">
<p>Results:</p>
<img src="test_resources/test1/output.jpg" height="500" width="800">
<p></p>
<img src="test_resources/test2/output.jpg" height="500" width="800">


