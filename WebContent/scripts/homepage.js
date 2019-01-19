function loadTables() {
    $(document).ready(function(){
        var tables = null;
        var xhttp = new XMLHttpRequest();


        xhttp.onreadystatechange = function () {
            if(xhttp.readyState == 4 && xhttp.status == 200) {

                $.ajax({
                    cache: false,
                    dataType: "json",
                    error: function(){
                    	console.log("json not found");
                    },
                    success: function (tables) {
                        var toAppend = "";
                        var i = 0;
                        
                        
                        while(i < tables.length )
                        {

                            var button = '<button type="button" class="btn btn-primary table" id="table_id">table_text</button>';
                            button = button.replace("table_id", tables[i].numeroTavolo);
                            if(!tables[i].flagOccupato){
                                button = button.replace("table_text", "Tavolo #: " + tables[i].numeroTavolo + " | Libero");

                            }else{
                            	button = button.replace("table_text", "Tavolo #: " + tables[i].numeroTavolo + " | Prenotazione #: " + tables[i].numeroOrdinazione + " | Occupato");
                            }
                            toAppend+=button;
                            
                            i++;
                        }

                        $("#tables").append().html(toAppend);
                    },
                    url: 'jsonfiles/listaTavoli.json',
                });
            }
        }
        xhttp.open("GET", "ControllerTavolo", true);
        console.log("tutto ok");

        // xhttp.open("GET, "ControllerTavolo?op=" + 1, true);
        xhttp.send();
    });
}