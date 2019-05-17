function myFunction(xml) {
                var i;
                
                var xmlDoc = xml.responseXML;
                var x = xmlDoc.getElementsByTagName("renes");
                
                for (i = 0; i < x.length; i++) {
                    var row = document.getElementById("tb").insertRow();
                    var c = row.insertCell();
                    c.innerHTML = x[i].getElementsByTagName("nome")[0].childNodes[0].nodeValue;
                    c = row.insertCell();
                    c.innerHTML = x[i].getElementsByTagName("especialidade")[0].childNodes[0].nodeValue;
                    
                }
            }
            
            
            function pesquisa() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    clearTable();
                    if (this.readyState === 4 && this.status === 200) {
                       myFunction(this);
                      
                    }
                };
                var url = "PesquisarNascimento?id=" + encodeURI(document.getElementById("complete-field").value);
                xhttp.open("POST", url, true);
                xhttp.send();
                 
            }
            
            function clearTable() {
                var t = document.getElementById('tb');
                if (t.getElementsByTagName("tr").length > 0) {
                    // t.style.display = 'none';
                    for (loop = t.childNodes.length - 1; loop >= 0; loop--) {
                        t.removeChild(t.childNodes[loop]);
                    }
                }
            }
            
    function doCompletion() {
            var nome, aux, tbl, tr, td, index;
            
            nome = document.getElementById("complete-field");
            aux = nome.value.toUpperCase();
            
            tbl = document.getElementById("customers");
            tr = tbl.getElementsByTagName("tr");
  
            for (index = 0; index < tr.length; index++) {
                td = tr[index].getElementsByTagName("td")[0];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(aux) > -1) {
                        tr[index].style.display = "";
                } else {
                    tr[index].style.display = "none";
                }
                }       
            }
        }

