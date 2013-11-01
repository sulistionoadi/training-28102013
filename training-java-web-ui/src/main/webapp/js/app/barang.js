
function createGridBarang() {

    $('#gridBarang').datagrid({
        style: 'width:700px; height:200px',
        method: 'get',
        url: 'json',
        idField: 'idBarang',
        fitColumns: 'true',
        nowrap: false,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        pageSize: 10,
        queryParams: {
            'page.page':1,
            'page.size':10
//            'startDate':new Date() //untuk kirim parameter ke server
        },
        columns: [[
        {
            field: 'idBarang',
            title: 'ID Barang',
            width: 110
        },
        {
            field: 'deskripsi',
            title: 'Nama Barang',
            width: 110
        },
        {
            field: 'harga',
            title: 'Harga',
            width: 110
        },
        {
            field: 'tanggal',
            title: 'Tanggal',
            width: 110
        }
        ]],
        onLoadSuccess:function(row,data){
            var pager = $('#gridBarang').datagrid('getPager');
            pager.pagination({
                onSelectPage: function(pageNumber, pageSize){
                    $.ajax({
                        type:"GET",
                        url: 'json',
                        data: {
                            'page.page':pageNumber,
                            'page.size':pageSize
                            //'startDate':new Date() //untuk kirim parameter ke server
                        },
                        dataType:'json',
                        beforeSend: function(jqXHR, settings){
                            $('#gridBarang').datagrid('loading');
                        },
                        success: function(data){
                            $('#gridBarang').datagrid('loadData',data);
                        },
                        complete: function(jqXHR, textStatus){
                            $('#gridBarang').datagrid('loaded');
                        }
                    });
                }
            });
        },
        onLoadError: function(error) {
            console.log("error loading grid data " + error);
        }
    });
    
}

