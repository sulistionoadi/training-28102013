
function createDatagridDetailTransaksi() {

    $('#gridDetail').datagrid({
        style: 'width:700px; height:400px',
        method: 'get',
        url: 'detail/list',
        idField: 'id',
        fitColumns: 'true',
        nowrap: false,
        singleSelect: true,
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
        onLoadError: function(error) {
            console.log("error loading grid data " + error);
        }
    });
}