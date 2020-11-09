# face-catch
---
# version 1.0
  <p>Image detection models:</p>
  <p>cascade model - ready</p>
  <p>deeplearn(caffe) model - not implemented</p>
  <p>Services:</p>
  <p>image based service - ready</p>
  <p>camera based service - not implemented</p>
  <p>Image comparison:</p>
  <p>matrix comparison - ready</p>
  <p>other comparators - not implemented</p>
  <p>Image source:</p>
  <p>Hibernate ORM</p>
 
# opencv
Unfortunately opencv does not compile correctly with maven dependency and have to be added manually to the build path
( <a target="_blank" href="https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse">How to add opencv</a> )
# properties
To configure inport/export files and files for image detection was used <a href="src/main/resources/settings.properties">settings.properties</a>.
# structure
<img src="README/struc_scheme.png"></img>
# configurations
<img src="README/config2.png"></img>
# uml
<img src="README/uml.png"></img>
# data-source-example
For example was used MySQL database (<a href="src/main/resources/create_db.sql">create script</a>).
Hibernate configuration <a href="src/main/resources/face_catch.cfg.xml">face_catch.cfg.xml</a>
<img src="README/db.png"></img>
# example-results
<img src="src/main/resources/test1/output.jpg"></img>
<p></p>
<img src="src/main/resources/test2/output.jpg"></img>


