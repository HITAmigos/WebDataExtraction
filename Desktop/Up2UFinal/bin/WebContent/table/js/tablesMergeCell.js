    function table_rowspan(table_id,table_colnum)  
    {  
        if(table_colnum=="even"){  
        table_colnum = "2n";  
        }  
        else if(table_colnum=="odd"){  
        table_colnum = "2n+1";  
        }  
        else{  
        table_colnum = ""+table_colnum;  
        }  
        var cols=[];  
        var all_row_num = $(table_id+" tr td:nth-child(1)").length;  
        var all_col_num = $(table_id+" tr:nth-child(1)").children().length;  
        if(table_colnum.indexOf("n")==-1){  
        cols[0] = table_colnum;  
        }  
        else{  
        var n = 0;  
        var a = table_colnum.substring(0,table_colnum.indexOf("n") );  
        var b= table_colnum.substring(table_colnum.indexOf("n")+1);  
        a = a?parseInt(a):1;  
        b = b?parseInt(b):0;  
        while(a*n+b<=all_col_num){  
            cols[n] = a*n+b;  
            n++;  
        }  
        }  
        var table_minrow = arguments[2]?arguments[2]:0;  
        var table_maxrow = arguments[3]?arguments[3]:all_row_num+1;      
        var table_firsttd = "";    
        var table_currenttd = "";    
        var table_SpanNum = 0;  
        for(var j=0;j<cols.length;j++){  
            $(table_id + " tr td:nth-child(" + cols[j] + ")").slice(table_minrow, table_maxrow).each(function (i) {  
                var table_col_obj = $(this);  
                if (table_col_obj.html() != " ") {  
                    if (i == 0) {  
                        table_firsttd = $(this);  
                        table_SpanNum = 1;  
                    }  
                    else {  
                        table_currenttd = $(this);  
                        if (table_firsttd.text() == table_currenttd.text()) {  
                            table_SpanNum++;  
                            table_currenttd.hide();    
                            table_firsttd.attr("rowSpan", table_SpanNum);  
                        } else {  
                            table_firsttd = $(this);  
                            table_SpanNum = 1;  
                        }  
                    }  
                }  
            });  
        }  
    }  
   function table_colspan(table_id,table_rownum){    
        var table_mincolnum = arguments[2]?arguments[2]:0;  
        var table_maxcolnum;  
        var table_firsttd = "";    
        var table_currenttd = "";    
        var table_SpanNum = 0;    
        $(table_id + " tr:nth-child(" + table_rownum + ")").each(function(i){    
            table_row_obj = $(this).children();  
            table_maxcolnum = arguments[3]?arguments[3]:table_row_obj.length;    
            table_row_obj.slice(table_mincolnum,table_maxcolnum).each(function(i){    
                if(i==0){    
                    table_firsttd = $(this);    
                    table_SpanNum = 1;    
                }else if((table_maxcolnum>0)&&(i>table_maxcolnum)){    
                    return "";    
                }else{    
                    table_currenttd = $(this);    
                    if(table_firsttd.text()==table_currenttd.text()){    
                        table_SpanNum++;   
                        if(table_currenttd.is(":visible")){  
                            table_firsttd.width(parseInt(table_firsttd.width())+ parseInt(table_currenttd.width()));  
                                }     
                        table_currenttd.hide(); //remove();    
                        table_firsttd.attr("colSpan",table_SpanNum);  
                    }else{    
                        table_firsttd = $(this);    
                        table_SpanNum = 1;    
                    }    
                }    
            });    
        });    
    }  