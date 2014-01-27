![screenshot](https://raw.github.com/ics-software-engineering/play-bootstrap-template/master/doc/play-bootstrap-template-home.png)

This is a sample Play application with the following features:

  * Modifications to the "default" application generated by the Play "new" command to eliminate
    checkstyle errors. See [play-new-passcheckstyle](http://ics-software-engineering.github.io/play-new-passcheckstyle/)
    for details.
  * Modifications to the [main.scala.html](https://github.com/ics-software-engineering/play-bootstrap-template/blob/master/app/views/main.scala.html) template to load [Twitter Bootstrap 3](http://getbootstrap.com/) files using CDN sites.
  * Modifications to illustrate simple page navigation through changes to the [controller](https://github.com/ics-software-engineering/play-bootstrap-template/blob/master/app/controllers/Application.java), 
    the [routes](https://github.com/ics-software-engineering/play-bootstrap-template/blob/master/conf/routes), and
    the [view files](https://github.com/ics-software-engineering/play-bootstrap-template/tree/master/app/views).
  * Note that the [navbar](https://github.com/ics-software-engineering/play-bootstrap-template/blob/master/app/views/main.scala.html) highlights the current active page.
  * Note that the [main.css](https://github.com/ics-software-engineering/play-bootstrap-template/blob/master/public/stylesheets/main.css) has a 
    slight adjustment to the body padding due to the use of the fixed-top navbar.  
  * Modifications to create very simple [tests](https://github.com/ics-software-engineering/play-bootstrap-template/tree/master/test/test).
  


