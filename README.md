# face-catch
The main idea of the project is to create a system that will validate the specified visual sources with the available source of images. For the first version, a database with three entries (blob and additional data) and several images for comparison (two photos of 12 faces each) with an established data source were used. To identify faces, an OpenCV framefork (cascade model) was used.
# opencv
Unfortunately opencv does not work correctly with maven dependency and have to be added manually to the build path
( <a target="_blank" href="https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse">How to add opencv</a> )
# version 1.0
<p><b>Image detection models:</b></p>
<p>cascade model - ready</p>
<p>deeplearn(caffe) model - not implemented</p>
<p><b>Services:</b></p>
<p>image based service - ready</p>
<p>camera based service - not implemented</p>
<p><b>Image comparison:</b></p>
<p>matrix comparison - ready</p>
<p>other comparators - not implemented</p>
<p><b>Image source:</b></p>
<p>Hibernate ORM</p>
<p><b>Unit tests:</b></p>
<p>Personnel DAO</p>
<p>Matrix Comparator</p>

# properties
Inport/export files; files for image detection - <a href="src/main/resources/settings.properties">settings.properties</a>.
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


