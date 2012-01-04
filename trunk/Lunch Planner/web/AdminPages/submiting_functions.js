/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function submitUserUpdate()
{
    var forma = document.getElementById("ViewState");
    forma.elements["selectedAccordion"].value="2";
        forma= document.getElementById("usrUpdateForm");
    forma.submit();
}

function submitUserDelete()
{
    var forma = document.getElementById("ViewState");
    forma.elements["selectedAccordion"].value="3";
        forma== document.getElementById("usrDeleteForm");
    forma.submit();
}
