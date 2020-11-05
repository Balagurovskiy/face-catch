# face-catch
A variant of the project for determining valid images. This configuration used a small database with three faces that will be searched for in the input images.
# opencv
Unfortunately opencv does not compile correctly with maven dependency and have to be added manually to the build path
( <a target="_blank" href="https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse">How to add opencv</a> )
# example
For example was used MySQL database (<a href="src/main/resources/create_db.sql">create script</a>)
<img src="README/bd.png">
<p>Input 1:</p>
<img src="test_resources/test1/input.jpg">
<img src="test_resources/test1/output.jpg">
<p>Input 2:</p>
<img src="test_resources/test2/input.jpg">
<img src="test_resources/test2/output.jpg">

