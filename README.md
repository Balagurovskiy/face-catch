# face-catch
A variant of the project for determining valid images. This configuration used a small database with three faces that will be searched for in the input images.
# opencv
Unfortunately opencv does not compile correctly with maven dependency and have to be added manually to the build path
( <a target="_blank" href="https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse">How to add opencv</a> )
# example
<p>For example was used MySQL database (<a href="src/main/resources/create_db.sql">create script</a>)</p>
<center>
<img src="README/bd.png" height="500" width="800">
<p>Results:</p>
<img src="test_resources/test1/output.jpg" height="500" width="800">
<p></p>
<img src="test_resources/test2/output.jpg" height="500" width="800">
</center>

