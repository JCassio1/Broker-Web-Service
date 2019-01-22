<%-- 
    Document   : index
    Created on : 20-Jan-2019, 16:28:55
    Author     : JCassio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
            <meta charset="utf-8">
    <meta name="author" content="Joselson Dias">
    <meta name="description" content="Shares Broker Service">
    <meta name="keywords" content="HTML,CSS,XML,JavaScript">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Place favicon.ico in the root directory -->
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
    <link rel="shortcut icon" type="image/ico" href="images/favicon.ico" />
    
    <!-- Plugin-CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <!-- Main-Stylesheets -->
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>

    <!--[if lt IE 9]>
        <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>
    <body data-spy="scroll" data-target="#primary-menu">
        
            <div class="mainmenu-area" data-spy="affix" data-offset-top="100">
        <div class="container">
            <!--Logo-->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#primary-menu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand logo">
                    <h2>Broker</h2>
                </a>
            </div>
            
                        <!--Logo/-->
            <nav class="collapse navbar-collapse" id="primary-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#home-page">Home</a></li>
                    <li><a href="#query-page">Make a query</a></li>
                    <li><a href="#service-page">Features</a></li>
                    <li><a href="#price-page">Web Service</a></li>
                    <li><a href="#faq-page">FAQ</a></li>
                    <li><a href="#contact-page">Contact</a></li>
                </ul>
            </nav>
        </div>
    </div>
        
        <!--Header-area-->
    <header class="header-area overlay full-height relative v-center" id="home-page">
        <div class="absolute anlge-bg"></div>
        <div class="container">
            <div class="row v-center">
                <div class="col-xs-12 col-md-7 header-text">
                    <h2>Best Shares Brokering Service</h2>
                    <p>You can use it in the following ways:</p>
                    
                    <!--Table-with-list-of-commands-->
                    <style type="text/css">
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;border:none;border-color:#ccc;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
            .tg .tg-s6z2{text-align:center}
        </style>
                <table class="tg">
                  <tr>
                    <th class="tg-s6z2">Field 1</th>
                    <th class="tg-s6z2">Field 2</th>
                    <th class="tg-s6z2">Result</th>
                  </tr>
                  <tr>
                    <td class="tg-s6z2">list</td>
                    <td class="tg-s6z2">shares</td>
                    <td class="tg-s6z2">list all shares</td>
                  </tr>
                  <tr>
                    <td class="tg-s6z2">show</td>
                    <td class="tg-s6z2">"company Name"</td>
                    <td class="tg-s6z2">show company details</td>
                  </tr>
                  <tr>
                    <td class="tg-s6z2">currency</td>
                    <td class="tg-s6z2">"the Currency"</td>
                    <td class="tg-s6z2">company with currency</td>
                  </tr>
                  <tr>
                    <td class="tg-s6z2">compare</td>
                    <td class="tg-s6z2">highest</td>
                    <td class="tg-s6z2">show highest shares</td>
                  </tr>
                  <tr>
                    <td class="tg-s6z2">compare</td>
                    <td class="tg-s6z2">lowest</td>
                    <td class="tg-s6z2">show lowest shares</td>
                  </tr>
                </table>
                    
                    </div>-->
            </div>
        </div>
    </header>
        
    <section class="footer-area relative sky-bg" id="query-page"> 
       
        <div ><center><h2>Machine Query</h2></center></div>

        
            <div class="container">                   
                                
                        
                   <form action="index.jsp" method="get">
                      First Command:<br>
                      <input type="text" name="firstCommand" value="" placeholder="enter first command" onfocus="if(this.value==this.defaultValue){this.value='';}" onblur="if(this.value==''){this.value=this.defaultValue;}">
                      <br>
                      Second Command:<br>
                      <input type="text" name="secondCommand" value="" placeholder="enter second command">
                      <br><br>
                      <input type="submit" class="button white" value="Submit" >
                    </form>
                                                <%-- start Broker Web Service Invocation --%><hr/>
                    <%
                    try {
                        String formFirstCommand = request.getParameter("firstCommand"); //Obtain value from firstCommand
                        String formSecondCommand = request.getParameter("secondCommand"); //Obtain value from form secondCommand
                        
                        org.me.calculator.BrokerWS_Service service = new org.me.calculator.BrokerWS_Service();
                        org.me.calculator.BrokerWS port = service.getBrokerWSPort();
                         // TODO initialize WS operation arguments here
                        java.lang.String firstQuery = formFirstCommand;
                        java.lang.String secondQuery = formSecondCommand;
                        // TODO process result here
                        java.lang.String result = port.userInput(firstQuery, secondQuery);
                        out.println("Result = " + result);
                    } catch (Exception ex) {
                        java.lang.String result = "You can begin by making a query to me";
                        out.println(result);
                    }
                    %>
                    <%-- end web service invocation --%><hr/>      
                       
            </div>           
                                
                            
                    
    </section>
        
            <!--Commands-Area-->
    <section class="gray-bg section-padding" id="service-page">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <div class="box">
                        <div class="box-icon">
                            <img src="images/service-icon-1.png" alt="">
                        </div>
                        <h4>EASY TO USE</h4>
                        <p>In order to obtain the results for you query you only have to input the commands displayed on the table and it will output the results in milliseconds.</p>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <div class="box">
                        <div class="box-icon">
                            <img src="images/service-icon-2.png" alt="">
                        </div>
                        <h4>SIMPLE DESIGN</h4>
                        <p>The Broker Service contains a very simple UI Design so you can go directly into obtaining your results.</p>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <div class="box">
                        <div class="box-icon">
                            <img src="images/service-icon-3.png" alt="">
                        </div>
                        <h4>Lightspeed Results</h4>
                        <p>The output are obtained within milliseconds.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--Feature-area/-->
    

    <section class="price-area overlay section-padding" id="price-page">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 text-center">
                    <div class="page-title">
                        <h2>Affordable Service</h2>
                        <p>We can provide web services for your website!</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <div class="price-table">
                        <h3 class="text-uppercase price-title">Basic</h3>
                        <hr>
                        <ul class="list-unstyled">
                            <li><strong class="amount">£ <span class="big">20</span></strong>/Month</li>
                            <li>100 MB Disk Space</li>
                            <li>2 Subdomains</li>
                            <li>5 Email Accounts</li>
                            <li>Webmail Support</li>
                            <li>Customer Support 24/7</li>
                        </ul>
                        <hr>
                        <a href="#" class="button">Purchase</a>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <div class="price-table active">
                        <span class="price-info"><span class="ti-crown"></span></span>
                        <h3 class="text-uppercase price-title">STANDARD</h3>
                        <hr>
                        <ul class="list-unstyled">
                            <li><strong class="amount">£ <span class="big">39</span></strong>/Month</li>
                            <li>100 MB Disk Space</li>
                            <li>2 Subdomains</li>
                            <li>5 Email Accounts</li>
                            <li>Webmail Support</li>
                            <li>Customer Support 24/7</li>
                        </ul>
                        <hr>
                        <a href="#" class="button">Purchase</a>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <div class="price-table">
                        <h3 class="text-uppercase price-title">UNLIMITED</h3>
                        <hr>
                        <ul class="list-unstyled">
                            <li><strong class="amount">£ <span class="big">59</span></strong>/Month</li>
                            <li>100 MB Disk Space</li>
                            <li>2 Subdomains</li>
                            <li>5 Email Accounts</li>
                            <li>Webmail Support</li>
                            <li>Customer Support 24/7</li>
                        </ul>
                        <hr>
                        <a href="#" class="button">Purchase</a>
                    </div>
                </div>
            </div>
        </div>
    </section>




    <section class="testimonial-area section-padding gray-bg overlay">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 text-center">
                    <div class="page-title">
                        <h2>Client says</h2>
                        <p>Read what our clients had to say about the brokering service!</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <div class="testimonials">
                        <div class="testimonial">
                            <div class="testimonial-photo">
                                <img src="images/avatar-small-1.jpg" alt="">
                            </div>
                            <h3>Jeff Bezos</h3>
                            <p>Love it! It is an amazing and fast service, Amazon values this.</p>
                        </div>
 
                        <div class="testimonial">
                            <div class="testimonial-photo">
                                <img src="images/avatar-small-6.jpg" alt="">
                            </div>
                            <h3>Steve Jobs</h3>
                            <p>It increase the revenue of Apple and now it is a trillion dollar company. Excellent!!!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>






    <section class="gray-bg section-padding" id="faq-page">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 text-center">
                    <div class="page-title">
                        <h2>Frequently Asked Questions</h2>
                        <p>Please find here frequently asked questions made by our customers!</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <div class="panel-group" id="accordion">
                        <div class="panel">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="true">How to use the shares brokering service?</a>
                            </h4>
                            <div id="collapse1" class="panel-collapse collapse in">
                                <p>Use the table in the main page with commands that the system recongnises and is able to retrieve.</p>
                            </div>
                        </div>
                        <div class="panel">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="true">How long does it take to process commands?</a>
                            </h4>
                            <div id="collapse2" class="panel-collapse collapse">
                                <p>The program is processed in real-time in milliseconds.</p>
                            </div>
                        </div>
                        <div class="panel">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="true">How is the currency converted?</a>
                            </h4>
                            <div id="collapse3" class="panel-collapse collapse">
                                <p>The currency utilises a third party service in order to convert currency values accurately.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <footer class="footer-area relative sky-bg" id="contact-page">
        <div class="absolute footer-bg"></div>
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-sm-offset-3 text-center">
                        <div class="page-title">
                            <h2>Contact Us</h2>
                            <p>If you have any questions regarding shares or the brokering service please talk to us!</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <address class="side-icon-boxes">
                            <div class="side-icon-box">
                                <div class="side-icon">
                                    <img src="images/location-arrow.png" alt="">
                                </div>
                                <p><strong>Address: </strong> 150, Nottingham Trent University <br />United Kingdom</p>
                            </div>
                            <div class="side-icon-box">
                                <div class="side-icon">
                                    <img src="images/phone-arrow.png" alt="">
                                </div>
                                <p><strong>Telephone: </strong>
                                    <a href="callto:8801812726495">+4401812726495</a> <br />
                                    <a href="callto:8801687420471">+4401687420471</a>
                                </p>
                            </div>
                            <div class="side-icon-box">
                                <div class="side-icon">
                                    <img src="images/mail-arrow.png" alt="">
                                </div>
                                <p><strong>E-mail: </strong>
                                    <a href="mailto:brokering@example.com">brokering@example.co.uk</a> <br />
                                    <a href="mailto:brokering@example.com">brokering@mail.co.uk</a>
                                </p>
                            </div>
                        </address>
                    </div>
                    <div class="col-xs-12 col-md-8">
                        <form action="process.php" id="contact-form" method="post" class="contact-form">
                            <div class="form-double">
                                <input type="text" id="form-name" name="form-name" placeholder="Your name" class="form-control" required="required">
                                <input type="email" id="form-email" name="form-email" class="form-control" placeholder="E-mail address" required="required">
                            </div>
                            <input type="text" id="form-subject" name="form-subject" class="form-control" placeholder="Message topic">
                            <textarea name="message" id="form-message" name="form-message" rows="5" class="form-control" placeholder="Your message" required="required"></textarea>
                            <button type="submit" class="button">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-middle">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 pull-right">
                        <ul class="social-menu text-right x-left">
                            <li><a href="#"><i class="ti-facebook"></i></a></li>
                            <li><a href="#"><i class="ti-twitter"></i></a></li>
                            <li><a href="#"><i class="ti-google"></i></a></li>
                            <li><a href="#"><i class="ti-linkedin"></i></a></li>
                            <li><a href="#"><i class="ti-github"></i></a></li>
                        </ul>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <p>Follow us on social media.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 text-center">
                        <p>&copy;Copyright 2019 All rights reserved. Shares Brokering Service</p>
                    </div>
                </div>
            </div>
        </div>
    </footer>





    <!--Vendor-JS-->
    <script src="js/vendor/jquery-1.12.4.min.js"></script>
    <script src="js/vendor/bootstrap.min.js"></script>
    <!--Plugin-JS-->
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/contact-form.js"></script>
    <script src="js/jquery.parallax-1.1.3.js"></script>
    <script src="js/scrollUp.min.js"></script>
    <script src="js/magnific-popup.min.js"></script>
    <script src="js/wow.min.js"></script>
    <!--Main-active-JS-->
    <script src="js/main.js"></script>


        
    </body>
</html>
