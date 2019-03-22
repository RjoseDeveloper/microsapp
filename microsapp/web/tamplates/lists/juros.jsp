<%-- 
    Document   : juros
    Created on : Mar 22, 2019, 3:36:46 PM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Just another jTable example</title>
        <link href="~/favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" 

              rel="stylesheet" type="text/css" />
        <link href="http://www.codeproject.com/jtable.2.3.0/themes/metro/blue/jtable.min.css" rel="stylesheet" 

              type="text/css" /> 
        <script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
        <script src="http://www.codeproject.com/jtable.2.3.0/jquery.jtable.min.js" 

        type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#OrdersTableContainer').jtable({
                    title: 'Orders list',
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    defaultSorting: 'Order Date DESC', //Set default sorting
                    actions: {
                        listAction: '/Actions/OrdersList',
                        createAction: '/Actions/CreateOrder',
                        updateAction: '/Actions/UpdateOrder',
                        deleteAction: '/Actions/DeleteOrder'
                    },
                    fields: {
                        'Order Id': {
                            key: true,
                            list: false
                        },
                        //CHILD TABLE DEFINITION FOR "DETAILS"
                        'Details': {
                            title: '',
                            width: '5%',
                            sorting: false,
                            edit: false,
                            create: false,
                            display: function (OrderData) {
                                //Create an image that will be used to open child table
                                var $img = $('<img src="http://www.codeproject.com/Content/Images/list_metro.png" ' +
                                        'title="Edit order details" />');
                                //Open child table when user clicks the image
                                $img.click(function () {
                                    $('#OrdersTableContainer').jtable('openChildTable',
                                            $img.closest('tr'),
                                            {
                                                title: OrderData.record['Ship Name'] +
                                                        ' - Order Details',
                                                actions: {
                                                    listAction:
                                                            '/Actions/ChildTable/DetailsList?OrderId='
                                                            + OrderData.record['Order Id'],
                                                    deleteAction: '/Actions/ChildTable/DeleteDetail',
                                                    updateAction: '/Actions/ChildTable/UpdateDetail',
                                                    createAction: '/Actions/ChildTable/CreateDetail'
                                                },
                                                fields: {
                                                    'Order Id': {
                                                        type: 'hidden',
                                                        defaultValue: OrderData.record['Order Id']
                                                    },
                                                    'Detail Id': {
                                                        key: true,
                                                        create: false,
                                                        edit: false,
                                                        list: false
                                                    },
                                                    'Product Name': {
                                                        title: 'Product',
                                                        width: '50%'
                                                    },
                                                    'Unit Price': {
                                                        title: 'Unit Price',
                                                        width: '25%'
                                                    },
                                                    'Quantity': {
                                                        title: 'Quantity',
                                                        width: '25%'
                                                    }
                                                }
                                            }, function (data) {
                                        data.childTable.jtable('load');
                                    });
                                });
                                //Return image to show on the order row
                                return $img;
                            }
                        },
                        'Ship Name': {
                            title: 'Firm',
                            width: '40%'
                        },
                        'Ship Country': {
                            title: 'Country',
                            width: '20%'
                        },
                        'Order Date': {
                            title: 'Order',
                            width: '20%',
                            type: 'date'
                        },
                        'Shipped': {
                            title: 'Shipped',
                            width: '20%',
                            type: 'checkbox',
                            values: {'false': 'False', 'true': 'True'}
                        }
                    }
                });
                $('#OrdersTableContainer').jtable('load');
            });
        </script>

    </head>
    <body>
        <div id="OrdersTableContainer"></div>
    </body>
</html>
</body>
</html>