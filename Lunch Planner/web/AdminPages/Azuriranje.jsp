<%-- 
    Document   : Azuriranje
    Created on : Jan 3, 2012, 2:19:15 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <script src="/accordion-js.js"></script>
    <link rel="stylesheet" type="text/css" href="/noBorders.css"></link>
    <link rel="stylesheet" type="text/css" href="/tabs-accordion.css"></link>

    <head>
        <script>
            $(function() { 
                
                $("#accordion").tabs("#accordion div.pane", {tabs: 'h2', effect: 'slide', initialIndex: null});
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ажурирање на податоци</title>
    </head>
    <body>
    <body> 	

        <!-- accordion -->
        <div id="accordion">

            <h2 class="current">First pane</h2>

            <div class="pane" style="display: block; ">

                <img src="http://static.flowplayer.org/img/title/javascript24.png" alt="JavaScript tools" style="float:left; margin:0 15px 15px 0">

                <h3>Lorem ipsum dolor</h3>

                <p>
                    <strong>Fusce semper, nisi nec pellentesque sollicitudin.</strong>
                </p>

                <p style="clear:both">
                    Consectetur adipiscing elit. Praesent bibendum eros ac nulla. Integer vel lacus ac neque viverra ornare. Nulla id massa nec erat laoreet elementum. Vivamus tristique auctor odio. Integer mi neque.
                </p>


            </div>

            <h2 class="">Second pane</h2>

            <div class="pane" style="display: none; ">
                <h3>Vestibulum ante ipsum</h3>

                <p>
                    Cras diam. Donec dolor lacus, vestibulum at, varius in, mollis id, dolor. Aliquam erat volutpat. Praesent pretium tristique est. Maecenas nunc lorem, blandit nec, accumsan nec, facilisis quis, pede. Aliquam erat volutpat. Donec sit amet urna quis nisi elementum fermentum.
                </p>
            </div>

            <h2 class="">Third pane</h2>

            <div class="pane" style="display: none; ">
                <h3>Curabitur vel dolor</h3>

                <p>
                    Non lectus lacinia egestas. Nulla hendrerit, felis quis elementum viverra, purus felis egestas magna, non vulputate libero justo nec sem. Nullam arcu. Donec pellentesque vestibulum urna. In mauris odio, fringilla commodo, commodo ac, dignissim ac, augue.
                </p>
            </div>	

        </div>

        <!-- activate tabs with JavaScript -->
        <script>
            $(function() { 

                $("#accordion").tabs("#accordion div.pane", {tabs: 'h2', effect: 'slide', initialIndex: null});
            });
        </script>




    </body>
    <h1></h1>
</body>
</html>
