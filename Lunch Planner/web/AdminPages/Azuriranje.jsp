<%-- 
    Document   : Azuriranje
    Created on : Jan 3, 2012, 2:19:15 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <script src="/accordion-js.js"></script>
    <link rel="stylesheet" type="text/css" href="/tabs-accordion.css"></link>
    
    <head>
        <title>Ажурирање на корисник</title>
    </head>
    <body>
        <div id="AccordionContainer" class="AccordionContainer">

            <div onclick="runAccordion(1);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Accordion 1
                </div>
            </div>
            <div id="Accordion1Content" class="AccordionContent">
                I Am Accordion 1.
            </div>

            <div onclick="runAccordion(2);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Accordion 2
                </div>
            </div>
            <div id="Accordion2Content" class="AccordionContent">
                I Am Accordion 2.
            </div>

            <div onclick="runAccordion(3);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Accordion 3
                </div>
            </div>
            <div id="Accordion3Content" class="AccordionContent">
                I Am Accordion 3.
            </div>

            <div onclick="runAccordion(4);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Accordion 4
                </div>
            </div>
            <div id="Accordion4Content" class="AccordionContent">
                I Am Accordion 4.
            </div>

            <div onclick="runAccordion(5);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Accordion 5
                </div>
            </div>
            <div id="Accordion5Content" class="AccordionContent">
                I Am Accordion 5.
            </div>

        </div>
    </body>
    <h1></h1>
</body>
</html>
