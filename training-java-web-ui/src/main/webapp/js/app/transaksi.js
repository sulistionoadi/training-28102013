
function createDatagridDetailTransaksi() {

    $('#gridDetail').datagrid({
        style: 'width:700px; height:200px',
        method: 'get',
        url: 'detail/list',
        idField: 'id',
        fitColumns: 'true',
        nowrap: false,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        pageSize: 10,
        queryParams: {
            'page.page':1,
            'page.size':10
        },
        columns: [[
        {
            field: 'barang',
            title: 'Barang',
            width: 110,
            formatter: function(value, row, index) {
                if (row.barang) {
                    return row.barang.deskripsi;
                } else {
                    return value;
                }
            }
        },
        {
            field: 'qty',
            title: 'Qty',
            width: 110
        },
        {
            field: 'harga',
            title: 'Harga',
            width: 110
        },
        {
            field: 'total',
            title: 'Total',
            width: 110
        }
        ]],
        onLoadSuccess:function(row,data){
            var pager = $('#gridDetail').datagrid('getPager');
            pager.pagination({
                onSelectPage: function(pageNumber, pageSize){
                    $.ajax({
                        type:"GET",
                        url: 'detail/list',
                        data: {
                            'page.page':pageNumber,
                            'page.size':pageSize
                        },
                        dataType:'json',
                        beforeSend: function(jqXHR, settings){
                            $('#gridDetail').datagrid('loading');
                        },
                        success: function(data){
                            $('#gridDetail').datagrid('loadData',data);
                        },
                        complete: function(jqXHR, textStatus){
                            $('#gridDetail').datagrid('loaded');
                        }
                    });
                }
            });
        },
        onLoadError: function(error) {
            console.log("error loading grid data " + error);
        }
    });
    
    $("#qty").keypress(function(event){
        hitungHarga();
    });
}

var urlCategory = "detail/list";
var methodCategory = "POST";
function newDetail(){
    $('#dlgFormTransaksiDetail').dialog('open').dialog('setTitle','New Detail');  
    $('#formTransaksiDetail').form('clear');  
    urlCategory = "detail/list";
    methodCategory = "POST";
}

var barang = null;
function findBarang(record){
    barang = record;
}

function hitungHarga(){
    var qty = $("#qty").val();
    var harga = barang.harga;
    $("#harga").numberbox('setValue', harga);
    var total = qty*harga;
    $("#total").numberbox('setValue', total);
    console.log(total);
}

function saveDetail(){
    var data = {};
    data.barang = barang;
    delete data.barang.idBarang;
    data.qty = $("#qty").numberbox('getValue');
    data.harga = $("#harga").numberbox('getValue');
    data.total = $("#total").numberbox('getValue');
    console.log("data =>", data)
    $.ajax({
        type: methodCategory,
        url: urlCategory,
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: 'json'
    });
    $('#dlgFormTransaksiDetail').dialog('close');
    createDatagridDetailTransaksi();
}

var tanggal = new Date();
function getTanggal(date){
    tanggal = date;
}

function saveTransaksi(){
    var data = {};
    data.tanggal = tanggal;
    $.ajax({
        type: 'POST',
        url: 'save',
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: 'json'
    });
}

function removeDetail(){
    var row = $('#gridDetail').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to delete this detail?',function(r){
            if (r){
                $.ajax({
                    type: 'DELETE',
                    url: 'detail/list/' + row.barang.idBarang,
                    success: function(data){
                        createDatagridDetailTransaksi();
                    },
                    error: function(errorResp){
                        $.messager.show({
                            title: 'Delete Error',
                            msg: errorResp.responseText
                        });
                    }
                });
            }
        });
    }
}
